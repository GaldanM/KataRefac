public class EngineSprocket {
  private final Boolean isWorking;

  public EngineSprocket(Boolean isWorking) {
    this.isWorking = isWorking;
  }

  public void Gear() {}

  public Boolean Engage() {
    return this.isWorking;
  }

  public Boolean getIsWorking() {
    return this.isWorking;
  }
}
