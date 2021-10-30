package car;

import engine.Sprocket;
import engine.SprocketEntity;
import engine.SprocketMapper;
import engine.SprocketRepository;

public class CarMapper {
  public static CarEntity CarToCarEntity(Car car) {
    return new CarEntity(car.getId(), car.getColor(), car.getType(), car.getNumberOfKm(), car.getEngineOilLevel());
  }

  public static Car CarEntityToCar(CarEntity carEntity, SprocketRepository sprocketRepository) {
    SprocketEntity sprocketEntity = sprocketRepository.findByCarId(carEntity.getId());
    Sprocket sprocket = SprocketMapper.EngineSprocketEntityToEngineSprocket(sprocketEntity);

    return new Car(
        carEntity.getId(), carEntity.getColor(), carEntity.getType(),
        carEntity.getNumberOfKm(), carEntity.getEngineOilLevel(),
        sprocket
    );
  }
}
