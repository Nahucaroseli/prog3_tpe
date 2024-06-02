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

    public void backtracking(int tiempoX) {
        back(new ArrayList<>(procesadores), 0,0,tiempoX);
        if(mejorSolucion!=null){
            mostrarSolucion(mejorSolucion);
        }

    }

    private void back(List<Procesador> solucion, int index,int tiempoMaximo,int tiempoX) {
        if (index == tareas.size()) {
            if (mejorSolucion.isEmpty() && mejorTiempoMaximo == 0 || tiempoMaximo < mejorTiempoMaximo) {
                mejorSolucion.clear();
                for (Procesador p : solucion) {
                    mejorSolucion.add(new Procesador(p));
                }
                mejorTiempoMaximo = tiempoMaximo;
            }
        } else {
            this.estadosGenerados++;
            Tarea t = tareas.get(index);
            for (Procesador p : solucion) {
                if(esValido(p,t,tiempoX)){
                    p.asignarTarea(t);
                    int nuevoTiempoMaximo = tiempoMaximo(tiempoMaximo,p);
                    if(mejorTiempoMaximo==0 || nuevoTiempoMaximo < mejorTiempoMaximo){
                        back(solucion, index + 1,nuevoTiempoMaximo,tiempoX);
                    }
                    p.quitarTarea(t);
                }
            }
        }
    }

    private boolean esValido(Procesador p,Tarea t,int tiempoX){
        int cont = 0;
        for(Tarea a: p.getTareas()){
            if(a.isEs_critica()){
                cont++;
            }
        }

        if(cont>=2 && t.isEs_critica()){
            return false;
        }
        if (!p.getRefrigerado() && p.getTiempoEjecucionMaximo() + t.getTiempo_ejecucion() > tiempoX) {
            return false;
        }
        return true;
    }


    private int tiempoMaximo(int tiempoMaximo, Procesador p){
        int tiempoEjecucion = p.getTiempoEjecucionMaximo();
        return Math.max(tiempoMaximo,tiempoEjecucion);
    }


    private void mostrarSolucion(List<Procesador> lista){
        for(int i = 0; i<lista.size();i++){

            System.out.println(lista.get(i).getId()+"{"+lista.get(i).getTareas()+"}");
        }
        System.out.println("Tiempo Maximo de Ejecucion: "+this.mejorTiempoMaximo);
        System.out.println("Estados generados: "+this.estadosGenerados);
    }
}
