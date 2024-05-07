
public class Tarea {

    private String ID;
    private String nombre_tarea;
    private Integer tiempo_ejecucion;
    private Boolean es_critica;
    private Integer nivel_prioridad;

    public Tarea(){

    }


    public Tarea(String nombre_tarea, String ID, Integer tiempo_ejecucion, Boolean es_critica, Integer nivel_prioridad) {
        this.nombre_tarea = nombre_tarea;
        this.ID = ID;
        this.tiempo_ejecucion = tiempo_ejecucion;
        this.es_critica = es_critica;
        this.nivel_prioridad = nivel_prioridad;
    }


    public String getNombre_tarea() {
        return nombre_tarea;
    }

    public Integer getTiempo_ejecucion() {
        return tiempo_ejecucion;
    }

    public Boolean isEs_critica() {
        return es_critica;
    }

    public Integer getNivel_prioridad() {
        return nivel_prioridad;
    }

    public String getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "ID='" + ID + '\'' +
                ", nombre_tarea='" + nombre_tarea + '\'' +
                ", tiempo_ejecucion=" + tiempo_ejecucion +
                ", es_critica=" + es_critica +
                ", nivel_prioridad=" + nivel_prioridad +
                '}';
    }
}
