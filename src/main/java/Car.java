import exception.CarCouldNotStartException;
import exception.OilLevelTooLowException;

public class Car {
  private Long id;
  private String color;
  private Line type;
  private final Long numberOfKm;
  private final Integer engineOilLevel;
  private final EngineSprocket engineSprocket;
  private final EngineValve engineValve = new EngineValve();

  public Car(Long id, String color, Line type, Long numberOfKm, Integer engineOilLevel, EngineSprocket engineSprocket) {
    this.id = id;
    this.color = color;
    this.type = type;
    this.numberOfKm = numberOfKm;
    this.engineOilLevel = engineOilLevel;
    this.engineSprocket = engineSprocket;
  }

  public Boolean Start() throws CarCouldNotStartException {
    final Integer OIL_LEVEL_TOO_LOW_THRESHOLD = 20;

    try {
      this.engineSprocket.Gear();
      Boolean lancementOk = this.engineSprocket.Engage();
      if (this.engineOilLevel < OIL_LEVEL_TOO_LOW_THRESHOLD) {
        throw new OilLevelTooLowException();
      }
      return lancementOk;
    } catch (Exception exception) {
      throw new CarCouldNotStartException();
    }
  }

  public String DisplayLigne() {
    return "Cette voiture est une " + this.type;
  }

  public Boolean HasSunRoof() {
    return this.type.hasSunRoof();
  }

  public static Float GetNumberOfSeats(Line type) {
    return type.getNumberOfSeats();
  }

  public Long getId() {
    return this.id;
  }

  public String getColor() {
    return this.color;
  }

  public Line getType() {
    return this.type;
  }

  public Long getNumberOfKm() {
    return this.numberOfKm;
  }

  public Integer getEngineOilLevel() {
    return engineOilLevel;
  }

  public EngineSprocket getEngineSprocket() {
    return engineSprocket;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public void setType(Line type) {
    this.type = type;
  }

  public enum Line {
    QashqaiTekna(true, 5F),
    QashqaiVisia(true, 0F),
    QashqaiNConnecta(false, 4F),
    QashqaiAcenta(true, 4F),
    ScenicLigne1(false, 0F),
    ScenicLigne2(false, 0F),
    Peugeot208Ligne1(true, 0F),
    Peugeot208Ligne2(true, 0F);

    private final Boolean hasSunroof;
    private final Float numberOfSeats;

    Line(Boolean hasSunroof, Float numberOfSeats) {
      this.hasSunroof = hasSunroof;
      this.numberOfSeats = numberOfSeats;
    }

    public Boolean hasSunRoof() {
      return this.hasSunroof;
    }

    public Float getNumberOfSeats() {
      return this.numberOfSeats;
    }
  }
}