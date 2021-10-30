package engine;

public class Sprocket {
  private final Boolean isWorking;

  public Sprocket(Boolean isWorking) {
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
