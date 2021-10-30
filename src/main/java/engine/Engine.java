package engine;

import exception.EngineCouldNotStartException;
import exception.OilLevelTooLowException;

public class Engine {
  private final Integer oilLevel;
  private final Sprocket sprocket;
  private final Valve valve = new Valve();

  public Engine(Sprocket sprocket, Integer oilLevel) {
    this.sprocket = sprocket;
    this.oilLevel = oilLevel;
  }

  public Boolean start() throws EngineCouldNotStartException {
    final Integer OIL_LEVEL_TOO_LOW_THRESHOLD = 20;

    try {
      this.sprocket.gear();
      Boolean lancementOk = this.sprocket.engage();
      if (this.oilLevel < OIL_LEVEL_TOO_LOW_THRESHOLD) {
        throw new OilLevelTooLowException();
      }
      return lancementOk;
    } catch (Exception exception) {
      throw new EngineCouldNotStartException();
    }
  }

  public Integer getOilLevel() {
    return this.oilLevel;
  }

  public Sprocket getSprocket() {
    return this.sprocket;
  }
}
