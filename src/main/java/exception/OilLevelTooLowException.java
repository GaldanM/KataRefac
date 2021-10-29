package exception;

public class OilLevelTooLowException extends Exception {
  public OilLevelTooLowException() {
    super("Voiture impossible a démarrer, niveau d'huile trop faible");
  }
}
