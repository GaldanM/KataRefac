public class CarMapper {
  public static CarEntity CarToCarEntity(Car car) {
    return new CarEntity(car.getId(), car.getColor(), car.getType(), car.getNumberOfKm(), car.getEngineOilLevel());
  }

  public static Car CarEntityToCar(CarEntity carEntity, EngineSprocketRepository engineSprocketRepository) {
    EngineSprocketEntity engineSprocketEntity = engineSprocketRepository.findByCarId(carEntity.getId());
    EngineSprocket engineSprocket = EngineSprocketMapper.EngineSprocketEntityToEngineSprocket(engineSprocketEntity);

    return new Car(
        carEntity.getId(), carEntity.getColor(), carEntity.getType(),
        carEntity.getNumberOfKm(), carEntity.getEngineOilLevel(),
        engineSprocket
    );
  }
}
