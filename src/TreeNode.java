public class TreeNode {

    private Tarea value;
    private TreeNode left;
    private TreeNode right;





    public TreeNode(Tarea value) {
        this.value = value;
        this.left = null;
        this.right = null;
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
            }else {
                if(this.right == null) {
                    this.right = new TreeNode(n);
                }else {
                    this.right.add(n);
                }
        }
    }




}