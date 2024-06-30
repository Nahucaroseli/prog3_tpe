import java.util.*;

public class Greedy {


    private List<Tarea> tareas;
    private List<Procesador> procesadores;
    private SolucionGreedy solucion;



    public Greedy(String pathProcesadores, String pathTareas){
        this.procesadores =new LinkedList<>();
        this.tareas = new LinkedList<>();
        this.solucion = new SolucionGreedy(0,0);
        CSVReader reader = new CSVReader();
        reader.readTasks(pathTareas,new HashMap(),new TreeWithNode(),new SimpleLinkedList<>(),tareas);
        reader.readProcessors(pathProcesadores,procesadores);
    }



    /*
        Primero se ordena de mayor a menor las tareas segun su tiempo de ejecucion,a partir de ahi,
        En cada iteracion de while,se verifican las dos restricciones del enunciado y se elige el procesador que menos carga tiene en ese momento,luego se guarda
        el procesador en una variable auxiliar, el cual va a servir despues para sacar el index de la lista solucion
        y reemplazarlo por el procesador con la tarea asignada en la clase SolucionGreedy. Ademas, cuando la lista
        de tareas quede vacia, se actualiza el tiempo global y se retorna la solucion. La clase SolucionGreedy se encarga
        de almacenar y actualizar la cantidad de tareas asignadas, los candidatos del algoritmo y la solucion resultante. Por ultimo
        se muestran los datos resultantes del algoritmo al usuario.
     */


    public SolucionGreedy greedy(int tiempoX){
        this.solucion = greedy(new ArrayList<>(procesadores),new ArrayList<>(tareas),tiempoX);
        return this.solucion;
    }

    private SolucionGreedy greedy(List<Procesador> solucion, List<Tarea> tareas,int tiempoX){
        ordenarTareas(tareas);//Ordenamos las tareas de Mayor a Menor segun el tiempo de ejecucion
        int tiempoMaximoGlobal = 0;
        int menorTiempoMaximoProcesador;
        while(!tareas.isEmpty()){
            Tarea t = tareas.remove(0);

            menorTiempoMaximoProcesador = Integer.MAX_VALUE;
            Procesador procesadorConMenosCarga = null;
            this.solucion.actualizarCandidatosConsiderados();
            for(Procesador p:solucion){

                if(esValido(p,t,tiempoX)){
                    int tiempoMaximo = p.getTiempoMaximoEjecucion();
                    if(tiempoMaximo < menorTiempoMaximoProcesador){

                        menorTiempoMaximoProcesador = tiempoMaximo;
                        procesadorConMenosCarga = p;
                    }
                }
            }
            if (procesadorConMenosCarga != null) {

                procesadorConMenosCarga.asignarTarea(t);
                this.solucion.actualizarCantTareasAsignadas();
                int index = this.solucion.getSolucion().indexOf(procesadorConMenosCarga);
                if (index != -1) {
                    this.solucion.reemplazarProcesadorEnPosicionEspecifica(index,procesadorConMenosCarga);
                } else {
                    this.solucion.agregarProcesadorASolucion(procesadorConMenosCarga);
                }
                int tiempoEjecucionActual = procesadorConMenosCarga.getTiempoMaximoEjecucion();
                if (tiempoEjecucionActual > tiempoMaximoGlobal) {
                    tiempoMaximoGlobal = tiempoEjecucionActual;
                }
            }
        }
        this.solucion.actualizarTiempoMaximo(tiempoMaximoGlobal);
        return this.solucion;
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


    private <T extends Comparable<T>> void ordenarTareas(List<Tarea> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) < 0) {
                    Tarea temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

}



