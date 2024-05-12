import java.util.ArrayList;
import java.util.List;

public class SimpleLinkedList<Tarea>{


    private Node<Tarea> first;
    private Node<Tarea> last;
    private List<Tarea> tareasCriticas;
    private List<Tarea> tareasNoCriticas;
    private int size;




    public Node<Tarea> getFirst() {
        return first;
    }



    public int getSize() {
        return size;
    }

    public Node<Tarea> getLast(){
        return last;
    }



    public SimpleLinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
        this.tareasNoCriticas = new ArrayList<>();
        this.tareasCriticas = new ArrayList<>();
    }

    public void insertFront(Tarea info,boolean critica) {
        Node<Tarea> tmp = null;
        Node<Tarea> actual = new Node<Tarea>(info,null);
        tmp = first;
        this.last = first;
        first = actual;
        actual.setNext(tmp);
        if(critica){
            tareasCriticas.add(info);
        }else{
            tareasNoCriticas.add(info);
        }
    }


    public List<Tarea> getTareasCriticas() {
        return new ArrayList<>(tareasCriticas);
    }

    public List<Tarea> getTareasNoCriticas() {
        return new ArrayList<>(tareasNoCriticas);
    }

    @Override
    public String toString() {
        return "MySimpleLinkedList [first=" + first + ", last=" + last + "]";
    }











}
