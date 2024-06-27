import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {


    private TreeWithNode tree;
    private HashMap<String,Tarea> hashMap;
    private SimpleLinkedList lista;



    /*
     * Expresar la complejidad temporal del constructor.
     *La complejidad de este metodo es O(n*h), ya que en el metodo readTasks(), por cada tarea a insertar se recorre (O(n)), y al insertar
     * los valores en el arbol, se puede llegar a un extremo de este(O(h)).
     *
     */
    public Servicios(String pathProcesadores, String pathTareas)
    {   lista = new SimpleLinkedList();
        tree = new TreeWithNode();
        hashMap = new HashMap<>();
        CSVReader reader = new CSVReader();
        reader.readProcessors(pathProcesadores,new ArrayList<>());
        reader.readTasks(pathTareas,this.hashMap,this.tree,lista,new ArrayList<>());
    }

    /*
     * Expresar la complejidad temporal del servicio 1.
     * Este metodo tiene una complejidad O(1), ya que las tareas se insertan en un hashmap, el cual un identificador
     * de la tarea y la tarea, y al hacer get va directo al valor con la misma ID.
     *
     */
    public Tarea servicio1(String ID) {
        return hashMap.get(ID);

    }

    /*
     * Expresar la complejidad temporal del servicio 2.
     * Este metodo tiene una complejidad O(1), ya que las tareas se insertan en una lista vinculada, la cual tiene dos
     * listas, una por cada opcion, cuando se agrega una tarea se filtran por estado critico, entonces cuando se hace un get de estas, solamente tiene que devolver una copia de la
     * lista ya creada.
     *
     */
    public List<Tarea> servicio2(boolean esCritica) {
        if(esCritica){
            return lista.getTareasCriticas();
        }
         return lista.getTareasNoCriticas();
        }

    /*
     * Expresar la complejidad temporal del servicio 3.
     *
     * Este metodo tiene una complejidad O(n+m), ya que se fija por donde ir dependiendo de los valores dados por el usuario, pero
     * si se encuentra con el valor deseado, agrega a la lista vinculada resultante el valor (O(1)) y ademas agrega las tareas
     * con prioridades repetidas, que se almacenan dentro del nodo (addAll O(m)), donde n es la cantidad de nodos total de arbol y m son la cantidad
     * de tareas con la misma prioridad. Despues seguira recorriendo a traves de sus dos hijos.
     *
     */
    public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
        return tree.getTareasPorPrioridad(prioridadInferior,prioridadSuperior);
    }

}