import java.util.LinkedList;
import java.util.List;

public class Procesador {

    private String id;
    private String codigo;
    private Boolean refrigerado;
    private Integer anio;
    private List<Tarea> tareas;
    private int tiempoMaximoEjecucion;
    private int cantTareasCriticas;



    public Procesador(Procesador otro){
        this.tareas = otro.getTareas();
        this.codigo = otro.getCodigo();
        this.id = otro.getId();
        this.refrigerado = otro.getRefrigerado();
        this.anio = otro.getAnio();
        this.cantTareasCriticas = 0;
        this.tiempoMaximoEjecucion = 0;
    }


    public Procesador(String codigo, String id, Boolean refrigerado, Integer anio) {
        tareas = new LinkedList<>();
        this.codigo = codigo;
        this.id = id;
        this.refrigerado = refrigerado;
        this.anio = anio;
    }

    public String getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public Boolean getRefrigerado() {
        return refrigerado;
    }

    public Integer getAnio() {
        return anio;
    }


    public void asignarTarea(Tarea t){
        if(t.isEs_critica() && cantTareasCriticas<2){
            this.tareas.add(t);
            cantTareasCriticas++;
            tiempoMaximoEjecucion+= t.getTiempo_ejecucion();
        }
        else if (!t.isEs_critica()){
            this.tareas.add(t);
            tiempoMaximoEjecucion+= t.getTiempo_ejecucion();
        }
    }

    public List<Tarea> getTareas() {
        return new LinkedList<>(tareas);
    }


    public void quitarTarea(Tarea t){

        if(t.isEs_critica()){
            this.tareas.remove(t);
            cantTareasCriticas--;
            tiempoMaximoEjecucion-= t.getTiempo_ejecucion();
        }
        else if (!t.isEs_critica()){
            this.tareas.remove(t);
            tiempoMaximoEjecucion-= t.getTiempo_ejecucion();
        }
    }


    public int getTiempoMaximoEjecucion() {
        return tiempoMaximoEjecucion;
    }

    public int getCantTareasCriticas() {
        return cantTareasCriticas;
    }


    @Override
    public String toString() {
        return "Procesador{" +
                "cantTareasCriticas=" + cantTareasCriticas +
                ", id='" + id + '\'' +
                ", refrigerado=" + refrigerado +
                ", tareas=" + tareas +
                '}';
    }
}
