package Modelo;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hora {
    private static Hora instancia;

    private Hora() {
        // Constructor privado
    }

    public static Hora getInstance() {
        if (instancia == null) {
            instancia = new Hora();
        }
        return instancia;
    }

    public String today() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public int year(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(fecha, formatter);
        return dateTime.getYear();
    }
}
