import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Backtracking {

    private List<Tarea> tareas;
    private List<Procesador> procesadores;
    private SolucionBacktracking solucionBack;
    private List<Procesador> mejorSolucion;


    public Backtracking(String pathProcesadores, String pathTareas){
        this.procesadores =new LinkedList<>();
        this.mejorSolucion = new LinkedList<>();
        this.solucionBack = new SolucionBacktracking(0,0);
        this.tareas = new LinkedList<>();
        CSVReader reader = new CSVReader();
        reader.readTasks(pathTareas,new HashMap(),new TreeWithNode(),new SimpleLinkedList<>(),tareas);
        reader.readProcessors(pathProcesadores,procesadores);
    }


/*

        En el backtracking, en cada estado que generamos, se decide asignar la tarea a todos los procesadores.
        Primero se valida si se cumplen las restricciones del enunciado, y despues se calcula el tiempo maximo de
        ejecucion del estado actual, y se llama a backtracking con la tarea asignada
        y el nuevo tiempo maximo. Si se terminaron de asignar todas las tareas, se verifica si el nuevo tiempo maximo
        de ejecucion es menor al mejor tiempo maximo ya almacenado en la clase SolucionBacktracking. Si esto se cumple se reemplaza la solucion.
        Por ultimo se retorna la clase SolucionBacktracking, que se encarga de actualizar los datos resultantes y luego los muestra al usuario.

    */

    public SolucionBacktracking backtracking(int tiempoX) {

        back(new ArrayList<>(procesadores), 0,0,tiempoX);
        this.solucionBack.guardarSolucion(this.mejorSolucion);
        return this.solucionBack;
    }

    private void back(List<Procesador> solucion, int index,int tiempoMaximo,int tiempoX) {
        this.solucionBack.actualizarEstadosGenerados();
        if (index == tareas.size()) {
            if (this.mejorSolucion.isEmpty() && this.solucionBack.getMejorTiempoMaximo() == 0 || tiempoMaximo < this.solucionBack.getMejorTiempoMaximo()) {
                this.mejorSolucion.clear();
                for (Procesador p : solucion) {
                    this.mejorSolucion.add(new Procesador(p));
                }
                this.solucionBack.actualizarMejorTiempoMaximo(tiempoMaximo);
            }
        } else {
            Tarea t = tareas.get(index);
            for (Procesador p : solucion) {
                if(esValido(p,t,tiempoX)){
                    p.asignarTarea(t);
                    int nuevoTiempoMaximo = tiempoMaximo(tiempoMaximo,p);
                    if(this.solucionBack.getMejorTiempoMaximo()==0 || nuevoTiempoMaximo < this.solucionBack.getMejorTiempoMaximo()){
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


}
