package exception;

public class EngineCouldNotStartException extends Exception {
  public EngineCouldNotStartException() {
    super("Le moteur n'a pas pu être démarré");
  }
}
