import java.util.List;

public class Main {

    public static void main(String args[]) {


        Backtracking backtracking = new Backtracking("src/datasets/Procesadores.csv","src/datasets/Tareas.csv");
        SolucionBacktracking resultBack = backtracking.backtracking(200);
        resultBack.mostrarSolucion();


        System.out.println(" ");

        Greedy greedy = new Greedy("src/datasets/Procesadores.csv","src/datasets/Tareas.csv");
        SolucionGreedy resultGreedy = greedy.greedy(200);
        resultGreedy.mostrarSolucion();
    }

}
