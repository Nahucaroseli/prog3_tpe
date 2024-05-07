public class TreeWithNode {

    protected TreeNode root;



    public TreeWithNode() {
        this.root = null;
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