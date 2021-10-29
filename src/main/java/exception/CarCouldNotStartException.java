package exception;

public class CarCouldNotStartException extends Exception {
  public CarCouldNotStartException() {
    super("Le moteur n'a pas pu être démarré");
  }
}
