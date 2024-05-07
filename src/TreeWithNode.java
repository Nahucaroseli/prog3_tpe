import java.util.ArrayList;
import java.util.List;

public class TreeWithNode {

    protected TreeNode root;



    public TreeWithNode() {
        this.root = null;
    }



    public List<Tarea> getTareas(Boolean esCritica){

        List<Tarea> resultado = new ArrayList<Tarea>();

        if(root!=null){
            this.getTareasPorCriterio(root,esCritica,resultado);
        }

        return resultado;
    }



    /*
     * Complejidad Computacional: O(N);
     *Este metodo tiene una complejidad O(N) ya que recorre los nodos del arbol sin excepcion.
     *
     *
     * */
    private void getTareasPorCriterio(TreeNode tree, Boolean esCritica, List<Tarea> resultado){
        if (tree == null) {
            return;
        }

        if (tree.getValue().isEs_critica() == esCritica) {
            resultado.add(tree.getValue());
        }


        this.getTareasPorCriterio(tree.getLeft(), esCritica,resultado);
        this.getTareasPorCriterio(tree.getRight(), esCritica,resultado);

    }


    public List<Tarea> getTareasPorPrioridad(int p1,int p2){
        List<Tarea> resultado = new ArrayList<Tarea>();

        if(root!=null){
            this.getTareasEntrePrioridades(root,p1,p2,resultado);
        }

        return resultado;
    }




    /*
    * Complejidad Computacional: O(Log N);
    * Este metodo tiene una complejidad de O(Log n) porque solo tomamos la mitad de los caminos posibles al estar el
    * arbol ordenado por nivel de prioridad
    *
    * */
    private void getTareasEntrePrioridades(TreeNode tree, int p1, int p2, List<Tarea> resultado){
         if(tree == null){
             return;
         }
         if(tree.getValue().getNivel_prioridad() >= p1 && tree.getValue().getNivel_prioridad() <= p2){
             resultado.add(tree.getValue());
             getTareasEntrePrioridades(tree.getLeft(),p1,p2,resultado);
             getTareasEntrePrioridades(tree.getRight(),p1,p2,resultado);
         }
         else if (tree.getValue().getNivel_prioridad() <= p1){
             getTareasEntrePrioridades(tree.getRight(),p1,p2,resultado);
         }
         else if (tree.getValue().getNivel_prioridad() >= p2){
            getTareasEntrePrioridades(tree.getLeft(),p1,p2,resultado);
        }

    }

    /*
     * Complejidad Computacional: O(N);
     *Este metodo tiene una complejidad O(N) ya que recorre los nodos del arbol sin excepcion.
     *
     *
     * */
    private Tarea get(String id,TreeNode tree){
        if (tree == null) {
            return null;
        }

        if (tree.getValue().getID().equals(id)) {
            return tree.getValue();
        }

        Tarea tareaIzquierda = this.get(id, tree.getLeft());
        if (tareaIzquierda != null) {
            return tareaIzquierda;
        }

        return this.get(id, tree.getRight());
    }


    public Tarea getTarea(String id){
        if(root!=null){
            return this.get(id,this.root);
        }
        return null;
    }

    public void insert(Tarea value) {
        if(this.root == null) {
            this.root = new TreeNode(value);
        }else {
            this.root.insert(value);
        }
    }

    void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.value + " ");
            inorder(root.right);
        }
    }


    @Override
    public String toString() {
        return "TreeWithNode{" +
                "root=" + root +
                '}';
    }
}