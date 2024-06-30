import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SolucionBacktracking {

    private int mejorTiempoMaximo;
    private int estadosGenerados;
    private List<Procesador> solucion;


    public SolucionBacktracking(int mejorTiempoMaximo, int estadosGenerados) {
        this.mejorTiempoMaximo = mejorTiempoMaximo;
        this.estadosGenerados = estadosGenerados;
        this.solucion = new LinkedList<>();
    }


    public int getMejorTiempoMaximo() {
        return mejorTiempoMaximo;
    }

    public int getEstadosGenerados() {
        return estadosGenerados;
    }




    public void actualizarMejorTiempoMaximo(int mejorTiempoMaximo){
        this.mejorTiempoMaximo = mejorTiempoMaximo;
    }


    public void actualizarEstadosGenerados(){
        this.estadosGenerados++;
    }



    public void actualizarSolucion(List<Procesador> solucion){
        this.solucion.clear();
        for (Procesador p : solucion) {
            this.solucion.add(new Procesador(p));
        }
    }


    public List<Procesador> getSolucion(){
        return this.solucion;
    }

    public void mostrarSolucion(){
        System.out.println("Backtracking: ");
        if(solucion!=null && getMejorTiempoMaximo()!=0){

            for(int i = 0;i< solucion.size();i++){
                System.out.println(solucion.get(i).getId()+"{"+solucion.get(i).getTareas()+"}");
            }
            System.out.println("Estados Generados: ");
            System.out.println(this.getEstadosGenerados());
            System.out.println("Tiempo Maximo: ");
            System.out.println(this.getMejorTiempoMaximo());
        }else{
            System.out.println("No hay Solucion");

        }
    }




}
