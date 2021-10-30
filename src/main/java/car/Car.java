package car;

import engine.Engine;
import engine.Sprocket;

import exception.CarCouldNotStartException;

public class Car {
  private Long id;
  private String color;
  private Line type;
  private final Long numberOfKm;
  private final Engine engine;

  public Car(Long id, String color, Line type, Long numberOfKm, Integer oilLevel, Sprocket sprocket) {
    this.id = id;
    this.color = color;
    this.type = type;
    this.numberOfKm = numberOfKm;
    this.engine = new Engine(sprocket, oilLevel);
  }

  public Boolean start() throws CarCouldNotStartException {
    try {
      return this.engine.start();
    } catch (Exception exception) {
      throw new CarCouldNotStartException();
    }
  }

  public String displayLigne() {
    return "Cette voiture est une " + this.type;
  }

  public Boolean hasSunRoof() {
    return this.type.hasSunRoof();
  }

  public static Float getNumberOfSeats(Line type) {
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
    return this.engine.getOilLevel();
  }

  public Sprocket getEngineSprocket() {
    return this.engine.getSprocket();
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