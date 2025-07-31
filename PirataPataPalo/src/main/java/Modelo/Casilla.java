package Modelo;



//Representa una casilla del tablero en el juego

public class Casilla {
	 EstadoCasilla estado;
	
   // Inicializa la casilla con agua 
    public Casilla() {
        this.estado = EstadoCasilla.AGUA; 
    }

    //metodos 
    public boolean esAgua() {
        return estado == EstadoCasilla.AGUA;
    }

    public boolean tienePuente() {
        return estado == EstadoCasilla.PUENTE;
    }

    public boolean tieneTesoro() {
        return estado == EstadoCasilla.TESORO;
    }

    public boolean tienePirata() {
        return estado == EstadoCasilla.PIRATA;
    }

    public void cambiarEstado(EstadoCasilla nuevoEstado) {
        this.estado = nuevoEstado;
    }
 }

