import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreeWithNode {

    private TreeNode root;



    public TreeWithNode() {
        this.root = null;
    }




    public List<Tarea> getTareasPorPrioridad(int p1,int p2){
        List<Tarea> resultado = new LinkedList<>();

        if(root!=null){
            this.getTareasEntrePrioridades(root,p1,p2,resultado);
        }

        return resultado;
    }





    private void getTareasEntrePrioridades(TreeNode tree, int p1, int p2, List<Tarea> resultado){
         if(tree == null){
             return;
         }
         if(tree.getValue() >= p1 && tree.getValue() <= p2){
             resultado.addAll(tree.getTareasConMismaPrioridad());
             getTareasEntrePrioridades(tree.getLeft(),p1,p2,resultado);
             getTareasEntrePrioridades(tree.getRight(),p1,p2,resultado);
         }
         else if (tree.getValue() <= p1){
             getTareasEntrePrioridades(tree.getRight(),p1,p2,resultado);
         }
         else if (tree.getValue() >= p2){
            getTareasEntrePrioridades(tree.getLeft(),p1,p2,resultado);
        }

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