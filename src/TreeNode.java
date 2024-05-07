public class TreeNode {

    protected Tarea value;
    protected TreeNode left;
    protected TreeNode right;



    public void setLeft(TreeNode left) {
        this.left = left;
    }



    public void setRight(TreeNode right) {
        this.right = right;
    }



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



    public void setValue(Tarea value) {
        this.value = value;
    }

}