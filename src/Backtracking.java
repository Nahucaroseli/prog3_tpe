import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Backtracking {

    private List<Tarea> tareas;
    private List<Procesador> procesadores;
    private List<Procesador> mejorSolucion;
    private int mejorTiempoMaximo;
    private int estadosGenerados;


    public Backtracking(String pathProcesadores, String pathTareas){
        this.estadosGenerados = 0;
        this.mejorTiempoMaximo = 0;
        this.procesadores =new ArrayList<>();
        this.tareas = new ArrayList<>();
        this.mejorSolucion = new ArrayList<>();
        CSVReader reader = new CSVReader();
        reader.readTasks(pathTareas,new HashMap(),new TreeWithNode(),new SimpleLinkedList<>(),tareas);
        reader.readProcessors(pathProcesadores,procesadores);
    }


/*

        En el backtracking, en cada estado que generamos, se decide asignar la tarea, a todos los procesadores.
        Primero se valida si se cumplen las restricciones del enunciado, y despues se calcula el tiempo maximo de
        ejecucion del estado actual, y se llama a backtracking con la tarea asignada
        y el nuevo tiempo maximo. Si se terminaron de asignar todas las tareas, se verifica si el nuevo tiempo maximo
        de ejecucion es menor al mejor tiempo maximo ya almacenado. Si esto se cumple se reemplaza la solucion.

    */

    public List<Procesador> backtracking(int tiempoX) {

        back(new ArrayList<>(procesadores), 0,0,tiempoX);
        return this.mejorSolucion;
    }

    private void back(List<Procesador> solucion, int index,int tiempoMaximo,int tiempoX) {
        this.estadosGenerados++;
        if (index == tareas.size()) {
            if (mejorSolucion.isEmpty() && mejorTiempoMaximo == 0 || tiempoMaximo < mejorTiempoMaximo) {
                mejorSolucion.clear();
                for (Procesador p : solucion) {
                    mejorSolucion.add(new Procesador(p));
                }
                mejorTiempoMaximo = tiempoMaximo;
            }
        } else {
            Tarea t = tareas.get(index);
            for (Procesador p : solucion) {
                if(esValido(p,t,tiempoX)){
                    p.asignarTarea(t);
                    int nuevoTiempoMaximo = tiempoMaximo(tiempoMaximo,p);
                    if(mejorTiempoMaximo==0 || nuevoTiempoMaximo < this.mejorTiempoMaximo){
                        back(solucion, index + 1,nuevoTiempoMaximo,tiempoX);
                    }
                    p.quitarTarea(t);
                }
            }
        }
    }

    private boolean esValido(Procesador p,Tarea t,int tiempoX){
        if(p.getCantTareasCriticas()==2 && t.isEs_critica()){
            return false;
        }


        if (!p.getRefrigerado() && p.getTiempoMaximoEjecucion() + t.getTiempo_ejecucion() > tiempoX) {
            return false;
        }
        return true;
    }


    private int tiempoMaximo(int tiempoMaximo, Procesador p){
        int tiempoEjecucion = p.getTiempoMaximoEjecucion();
        if(tiempoEjecucion > tiempoMaximo){
            return tiempoEjecucion;
        }
        return tiempoMaximo;
    }


    public int getMejorTiempoMaximo() {
        return mejorTiempoMaximo;
    }

    public int getEstadosGenerados() {
        return estadosGenerados;
    }
}
