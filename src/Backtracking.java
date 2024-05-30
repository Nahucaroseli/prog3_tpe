import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Backtracking {

    private List<Tarea> tareas;
    private List<Procesador> procesadores;
    private List<Procesador> mejorSolucion;
    private int estadosGenerados;


    public Backtracking(String pathProcesadores, String pathTareas){
        this.estadosGenerados = 0;
        this.procesadores =new ArrayList<>();
        this.tareas = new ArrayList<>();
        this.mejorSolucion = new ArrayList<>();
        CSVReader reader = new CSVReader();
        reader.readTasks(pathTareas,new HashMap(),new TreeWithNode(),new SimpleLinkedList<>(),tareas);
        reader.readProcessors(pathProcesadores,procesadores);
    }


    public void backtracking() {
        back(new ArrayList<>(procesadores), 0);
        if(mejorSolucion!=null){
            mostrarSolucion(mejorSolucion);
        }

    }

    private void back(List<Procesador> solucion, int index) {
        if (index == tareas.size()) {
            if (mejorSolucion.isEmpty() || tiempoMaximo(solucion) < tiempoMaximo(mejorSolucion)) {
                mejorSolucion.clear();
                for (Procesador p : solucion) {
                    mejorSolucion.add(new Procesador(p));
                }
            }
        } else {
            this.estadosGenerados++;
            Tarea t = tareas.get(index);
            for (Procesador p : solucion) {
                if(esValido(p)){
                    p.asignarTarea(t);
                    back(solucion, index + 1);
                    p.removeTarea(t); // Retroceder el estado del procesador
                }
            }
        }
    }

    private boolean esValido(Procesador p){
        int cont = 0;
        for(Tarea a: p.getTareas()){
            if(a.isEs_critica()){
                cont++;
            }
        }

        if(cont>=2){
            return false;
        }
        return true;
    }


    private int tiempoMaximo(List<Procesador> solucion){
        int cont = 0;

        for(Procesador p:solucion){;
            cont = Math.max(cont, p.getTiempoEjecucionMaximo());
        }

        return cont;
    }


    private void mostrarSolucion(List<Procesador> lista){
        for(int i = 0; i<lista.size();i++){

            System.out.println(lista.get(i).getId()+"{"+lista.get(i).getTareas()+"}");
        }
        System.out.println("Estados generados: "+this.estadosGenerados);
    }
}
