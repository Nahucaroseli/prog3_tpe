import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SolucionGreedy {


    private int tiempoMaximo;
    private int candidatosConsiderados;
    private int cantTareasAsignadas;
    private List<Procesador> solucion;


    public SolucionGreedy(int tiempoMaximo, int candidatosConsiderados) {
        this.tiempoMaximo = tiempoMaximo;
        this.cantTareasAsignadas = 0;
        this.solucion = new ArrayList<>();
        this.candidatosConsiderados = candidatosConsiderados;
    }



    public void actualizarCantTareasAsignadas(){
        this.cantTareasAsignadas++;
    }


    public void actualizarCandidatosConsiderados(){
        this.candidatosConsiderados++;
    }

    public void reemplazarProcesadorEnPosicionEspecifica(int index, Procesador procesadorConMenosCarga){
        solucion.set(index, procesadorConMenosCarga);
    }

    public List<Procesador> getSolucion(){
        return this.solucion;
    }

    public int getCantTareasAsignadas(){
        return this.cantTareasAsignadas;
    }

    public void agregarProcesadorASolucion(Procesador procesadorConMenosCarga){
        solucion.add(procesadorConMenosCarga);
    }

    public void actualizarTiempoMaximo(int nuevoTiempoMaximo){
        this.tiempoMaximo = nuevoTiempoMaximo;
    }


    public int getTiempoMaximo() {
        return tiempoMaximo;
    }

    public int getCandidatosConsiderados() {
        return candidatosConsiderados;
    }

    public void mostrarSolucion() {
        System.out.println("Greedy: ");
        if(this.getCantTareasAsignadas() == this.getCandidatosConsiderados() && this.getTiempoMaximo()!=0){

            for(int i = 0;i< solucion.size();i++){
                System.out.println(solucion.get(i).getId()+"{"+solucion.get(i).getTareas()+"}");
            }
            System.out.println("Candidatos Considerados: ");
            System.out.println(this.getCandidatosConsiderados());
            System.out.println("Tiempo Maximo: ");
            System.out.println(this.getTiempoMaximo());
        }else{
            System.out.println("No hay solucion");
        }
    }




}
