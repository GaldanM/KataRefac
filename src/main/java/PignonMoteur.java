public class PignonMoteur {
  private final Boolean isWorking;

  public PignonMoteur(Boolean isWorking) {
    this.isWorking = isWorking;
  }

  public void Engrener() {}

  public Boolean Enclencher() {
    return this.isWorking;
  }

  public Boolean getIsWorking() {
    return this.isWorking;
  }
}
