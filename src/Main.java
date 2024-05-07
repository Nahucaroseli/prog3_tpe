public class Main {

    public static void main(String args[]) {
        Servicios servicios = new Servicios("src/datasets/Procesadores.csv", "src/datasets/Tareas.csv");
        CSVReader c = new CSVReader();

        TreeWithNode a = c.readTasks("src/datasets/Tareas.csv");


       System.out.println(a.getTareasPorPrioridad(80,95));


    }
}
