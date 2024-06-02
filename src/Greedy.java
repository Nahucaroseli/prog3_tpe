import java.util.*;

public class Greedy {


    private List<Tarea> tareas;
    private List<Procesador> procesadores;
    private int tiempoMaximo;
    private int candidatosConsiderados;



    public Greedy(String pathProcesadores, String pathTareas){
        this.tiempoMaximo = 0;
        this.candidatosConsiderados = 0;
        this.procesadores =new ArrayList<>();
        this.tareas = new ArrayList<>();
        CSVReader reader = new CSVReader();
        reader.readTasks(pathTareas,new HashMap(),new TreeWithNode(),new SimpleLinkedList<>(),tareas);
        reader.readProcessors(pathProcesadores,procesadores);
    }



    /*
        Primero se ordena de mayor a menor las tareas segun su tiempo de ejecucion,a partir de ahi, se verifican
        las dos restricciones del enunciado.
        En cada iteracion de while, se elige el procesador que menos carga tiene en ese momento, se guarda
        el procesador en una variable auxiliar, el cual va a servir despues para sacar el index de la lista original
        y reemplazarlo por el procesador con la tarea asignada.
     */


    public void greedy(int tiempoX){
        List<Procesador> solucion;
        solucion = greedy(new ArrayList<>(procesadores),new ArrayList<>(tareas),tiempoX);
        System.out.println("Greedy: ");
        if(this.tiempoMaximo != 0){
            mostrarSolucion(solucion);
        }else{
            System.out.println("No hay solucion posible");
        }

    }

    private List<Procesador> greedy(List<Procesador> solucion, List<Tarea> tareas,int tiempoX){
        ordenarTareas(tareas);//Ordenamos las tareas de Mayor a Menor segun el tiempo de ejecucion
        Procesador procesadorConMenosCarga = null;
        int tiempoMaximoProcesador = 9999;
        while(!tareas.isEmpty()){
            tiempoMaximoProcesador = 999;
            Tarea t = tareas.remove(0);
            for(Procesador p:solucion){

                if(esValido(p,t,tiempoX)){
                    int tiempoMaximo = p.getTiempoEjecucionMaximo();
                    if(tiempoMaximo < tiempoMaximoProcesador){
                        this.candidatosConsiderados++;
                        tiempoMaximoProcesador = tiempoMaximo;
                        if(tiempoMaximoProcesador> this.tiempoMaximo){
                            this.tiempoMaximo = tiempoMaximoProcesador;
                        }
                        procesadorConMenosCarga = p;
                    }
                }
            }
            if (procesadorConMenosCarga != null) {
                procesadorConMenosCarga.asignarTarea(t);

                // Reemplazamos el procesador modificado en la lista solución
                int index = solucion.indexOf(procesadorConMenosCarga);
                if (index != -1) {
                    solucion.set(index, procesadorConMenosCarga);
                } else {
                    // Si por alguna razón el procesador no está en la lista (lo cual no debería pasar)
                    solucion.add(procesadorConMenosCarga);
                }
            }
        }
        return solucion;
    }

    private boolean esValido(Procesador p,Tarea t,int tiempoX){
        int cont = 0;
        for(Tarea a: p.getTareas()){
            if(a.isEs_critica()){
                cont++;
            }
        }

        if(cont>2 && t.isEs_critica()){
            return false;
        }
        if (!p.getRefrigerado() && p.getTiempoEjecucionMaximo() + t.getTiempo_ejecucion() > tiempoX) {
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


    private void mostrarSolucion(List<Procesador> lista){

        for(int i = 0; i<lista.size();i++){

            System.out.println(lista.get(i).getId()+"{"+lista.get(i).getTareas()+"}");
        }
        System.out.println("Tiempo maximo de Ejecucion: "+this.tiempoMaximo);
        System.out.println("Candidatos Considerados: "+this.candidatosConsiderados);
    }

}



