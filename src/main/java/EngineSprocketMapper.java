public class EngineSprocketMapper {
  public static EngineSprocketEntity EngineSprocketToEngineSprocketEntity(EngineSprocket engineSprocket) {
    return new EngineSprocketEntity(engineSprocket.getIsWorking());
  }

  public static EngineSprocket EngineSprocketEntityToEngineSprocket(EngineSprocketEntity engineSprocketEntity) {
    return new EngineSprocket(engineSprocketEntity.getIsWorking());
  }
}
