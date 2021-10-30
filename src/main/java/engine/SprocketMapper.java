package engine;

public class SprocketMapper {
  public static SprocketEntity EngineSprocketToEngineSprocketEntity(Sprocket sprocket) {
    return new SprocketEntity(sprocket.getIsWorking());
  }

  public static Sprocket EngineSprocketEntityToEngineSprocket(SprocketEntity sprocketEntity) {
    return new Sprocket(sprocketEntity.getIsWorking());
  }
}
