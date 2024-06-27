import java.util.List;

public class Main {

    public static void main(String args[]) {
        Servicios servicios = new Servicios("src/datasets/Procesadores.csv","src/datasets/Tareas.csv");
        System.out.println(servicios.servicio3(93,100));

        Backtracking backtracking = new Backtracking("src/datasets/Procesadores.csv","src/datasets/Tareas.csv");
        List<Procesador> resultBack = backtracking.backtracking(10);
        System.out.println("Backtracking: ");
        if(resultBack!=null && backtracking.getMejorTiempoMaximo()!=0){
            for(int i = 0;i< resultBack.size();i++){
                System.out.println(resultBack.get(i).getId()+"{"+resultBack.get(i).getTareas()+"}");
            }
            System.out.println("Estados Generados: ");
            System.out.println(backtracking.getEstadosGenerados());
            System.out.println("Tiempo Maximo: ");
            System.out.println(backtracking.getMejorTiempoMaximo());
        }else{
            System.out.println("No hay Solucion");
        }


        System.out.println(" ");

        System.out.println("Greedy: ");
        Greedy greedy = new Greedy("src/datasets/Procesadores.csv","src/datasets/Tareas.csv");
        List<Procesador> resultGreedy = greedy.greedy(10);
        if(resultGreedy!=null){
            for(int i = 0;i< resultGreedy.size();i++){
                System.out.println(resultGreedy.get(i).getId()+"{"+resultGreedy.get(i).getTareas()+"}");
            }
            System.out.println("Candidatos Considerados: ");
            System.out.println(greedy.getCandidatosConsiderados());
            System.out.println("Tiempo Maximo: ");
            System.out.println(greedy.getTiempoMaximo());
        }else{
            System.out.println("No hay Solucion");
        }
    }

}
