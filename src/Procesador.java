import java.util.ArrayList;
import java.util.List;

public class Procesador {

    private String id;
    private String codigo;
    private Boolean refrigerado;
    private Integer anio;
    private List<Tarea> tareas;


    public Procesador(){

    }


    public Procesador(Procesador otro){
        this.tareas = otro.getTareas();
        this.codigo = otro.getCodigo();
        this.id = otro.getId();
        this.refrigerado = otro.getRefrigerado();
        this.anio = otro.getAnio();
    }


    public Procesador(String codigo, String id, Boolean refrigerado, Integer anio) {
        tareas = new ArrayList<>();
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

        this.tareas.add(t);
    }

    public List<Tarea> getTareas() {
        return new ArrayList<>(tareas);
    }


    public void quitarTarea(Tarea t){
        tareas.remove(t);
    }

    public int getTiempoEjecucionMaximo(){
        int cont = 0;
        for(int i= 0;i<this.tareas.size();i++){
            cont += tareas.get(i).getTiempo_ejecucion();
        }
        return cont;
    }

    @Override
    public String toString() {
        return "Procesador{" +
                "id='" + id + '\'' +
                '}';
    }


}
