import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreeWithNode {

    private TreeNode root;



    public TreeWithNode() {
        this.root = null;
    }




    public List<Tarea> getTareasPorPrioridad(int p1,int p2){
        List<Tarea> resultado = new LinkedList<Tarea>();

        if(root!=null){
            this.getTareasEntrePrioridades(root,p1,p2,resultado);
        }

        return resultado;
    }





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



    @Override
    public String toString() {
        return "TreeWithNode{" +
                "root=" + root +
                '}';
    }
}