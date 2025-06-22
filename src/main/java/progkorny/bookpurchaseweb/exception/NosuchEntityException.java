package progkorny.bookpurchaseweb.exception;
// Egyedi (custom) kivételosztály, amely akkor dobható, ha egy keresett entitás (pl. Boat, Customer) nem található az adatbázisban.
public class NosuchEntityException extends RuntimeException {
    // Konstruktor: csak egy hibaüzenetet fogad el
    public NosuchEntityException(String message) {
        super(message);
    }
    // Konstruktor: hibaüzenet + másik kivétel (ok) továbbadása
    public NosuchEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}

