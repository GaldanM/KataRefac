package car;

public class CarEntity {
  private final Long id;
  private final String color;
  private final Car.Line type;
  private final Integer engineOilLevel;
  private final Boolean isDeleted;

  private Long numberOfKm;

  public CarEntity(Long id, String color, Car.Line type, Long numberOfKm, Integer engineOilLevel) {
    this.id = id;
    this.color = color;
    this.type = type;
    this.numberOfKm = numberOfKm;
    this.engineOilLevel = engineOilLevel;
    this.isDeleted = false;
  }

  public Long getId() {
    return id;
  }

  public String getColor() {
    return color;
  }

  public Car.Line getType() {
    return type;
  }

  public Long getNumberOfKm() {
    return numberOfKm;
  }

  public Integer getEngineOilLevel() {
    return engineOilLevel;
  }

  public Boolean getIsDeleted() {
    return isDeleted;
  }

  public void setNumberOfKm(Long numberOfKm) {
    this.numberOfKm = numberOfKm;
  }
}
