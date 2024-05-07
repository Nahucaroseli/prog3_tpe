public class TreeWithNode {

    protected TreeNode root;



    public TreeWithNode() {
        this.root = null;
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