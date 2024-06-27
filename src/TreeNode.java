import java.util.LinkedList;
import java.util.List;

public class TreeNode {

    private Tarea value;
    private TreeNode left;
    private TreeNode right;
    private List<Tarea> tareasConMismaPrioridad;





    public TreeNode(Tarea value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.tareasConMismaPrioridad = new LinkedList<>();
    }



    public Tarea getValue() {
        return value;
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
            if(n.getNivel_prioridad()<this.value.getNivel_prioridad()) {
                if(this.left == null) {
                    this.left = new TreeNode(n);
                }else {
                    this.left.add(n);
                }
            }else if (n.getNivel_prioridad() > this.value.getNivel_prioridad()){
                if(this.right == null) {
                    this.right = new TreeNode(n);
                }else {
                    this.right.add(n);
                }
            }else{
                this.tareasConMismaPrioridad.add(n);
            }

    }




}