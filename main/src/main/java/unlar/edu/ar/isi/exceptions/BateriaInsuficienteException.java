package unlar.edu.ar.isi.exceptions;

public class BateriaInsuficienteException extends RuntimeException {
    public BateriaInsuficienteException(String message) {
        super(message);
    }
}