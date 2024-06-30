import java.util.LinkedList;
import java.util.List;

public class TreeNode {

    private int prioridad;
    private TreeNode left;
    private TreeNode right;
    private List<Tarea> tareasConMismaPrioridad;





    public TreeNode(Tarea value) {
        this.prioridad = value.getNivel_prioridad();
        this.left = null;
        this.right = null;
        this.tareasConMismaPrioridad = new LinkedList<>();
        this.tareasConMismaPrioridad.add(value);
    }



    public int getValue() {
        return prioridad;
    }



    public TreeNode getLeft() {
        return left;
    }



    public TreeNode getRight() {
        return right;
    }


    public List<Tarea> getTareasConMismaPrioridad (){ return new LinkedList<>(this.tareasConMismaPrioridad);}


    public void insert(Tarea n) {
        this.add(n);
    }



    private void add(Tarea n) {
            if(n.getNivel_prioridad()<this.prioridad) {
                if(this.left == null) {
                    this.left = new TreeNode(n);
                }else {
                    this.left.add(n);
                }
            }else if (n.getNivel_prioridad() > this.prioridad){
                if(this.right == null) {
                    this.right = new TreeNode(n);
                }else {
                    this.right.add(n);
                }
            }else{
                this.tareasConMismaPrioridad.add(n);
            }

    }


    @Override
    public String toString() {
        return "TreeNode{" +
                "prioridad=" + prioridad +
                '}';
    }
}