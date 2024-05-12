public class Node<Tarea>  {


    private Tarea info;
    private Node<Tarea> next;



    public Node(Tarea info, Node<Tarea> next) {
        this.info = info;
        this.next = next;
    }



    public Tarea getInfo() {
        return info;
    }



    public void setInfo(Tarea info) {
        this.info = info;
    }



    public Node<Tarea> getNext() {
        return next;
    }



    public void setNext(Node<Tarea> next) {
        this.next = next;
    }





}
