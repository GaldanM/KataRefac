public class EngineSprocket {
  private final Boolean isWorking;

  public EngineSprocket(Boolean isWorking) {
    this.isWorking = isWorking;
  }

  public void gear() {}

  public Boolean engage() {
    return this.isWorking;
  }

  public Boolean getIsWorking() {
    return this.isWorking;
  }
}
