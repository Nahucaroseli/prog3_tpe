public class Main {

    public static void main(String args[]) {
        Backtracking backtracking = new Backtracking("src/datasets/Procesadores.csv","src/datasets/Tareas.csv");
        backtracking.backtracking(80);
        Greedy greedy = new Greedy("src/datasets/Procesadores.csv","src/datasets/Tareas.csv");
        greedy.greedy(80);
    }

}
