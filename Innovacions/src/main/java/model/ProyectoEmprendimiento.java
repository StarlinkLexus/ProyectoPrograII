package model;

public class ProyectoEmprendimiento extends Proyecto {

    // Atributos específicos del emprendimiento: monto meta y monto recaudado
    private double montoMeta;
    private double montoRecaudado;

    /**
     * Constructor del proyecto de emprendimiento.
     * Inicializa con una meta de recaudación y establece el monto recaudado en 0.
     */
    public ProyectoEmprendimiento(String nombre, String descripcion, String url, Investigador responsable, double montoMeta) {
        super(nombre, descripcion, url, responsable);
        this.montoMeta = montoMeta;
        this.montoRecaudado = 0.0;
    }

    public double getMontoMeta() {
        return montoMeta;
    }

    public double getMontoRecaudado() {
        return montoRecaudado;
    }

    /**
     * Permite realizar una donación al proyecto.
     * Solo acepta valores positivos y no permite superar la meta.
     */
    public void donar(double cantidad) {
        if (cantidad > 0) {
            double cantidadFaltante = cantidadFaltante(); // Obtener cuánto falta para alcanzar la meta
            if (cantidad > cantidadFaltante) {
                System.out.println("La donación excede la cantidad que falta para alcanzar la meta.");
            } else {
                montoRecaudado += cantidad; // Agregar la donación al monto recaudado
                System.out.println("Donación realizada: $" + cantidad);
            }
        } else {
            System.out.println("La cantidad de la donación debe ser mayor que cero.");
        }
    }

    /**
     * Devuelve cuánto falta para alcanzar la meta de recaudación.
     */
    public double cantidadFaltante() {
        return montoMeta - montoRecaudado;
    }

    /**
     * Indica si ya se alcanzó o superó la meta de recaudación.
     */
    public boolean haAlcanzadoMeta() {
        return montoRecaudado >= montoMeta;
    }
}
