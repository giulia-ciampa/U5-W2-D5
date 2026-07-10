package giuliaciampa.U5_W2_D5.exceptions;

public class FileException extends RuntimeException {
    public FileException(String message) {
        super("errore durante il caricamento del file");
    }
}
