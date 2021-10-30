package car;

import engine.SprocketEntity;
import engine.SprocketMapper;
import engine.SprocketRepository;

import java.util.List;

public class CarService {
  CarRepository carRepository;
  SprocketRepository sprocketRepository;

  public CarService(CarRepository carRepository, SprocketRepository sprocketRepository) {
    this.carRepository = carRepository;
    this.sprocketRepository = sprocketRepository;
  }

  public Car driveAKm(Long carId) {
    CarEntity carEntity = this.carRepository.incrementNumberOfKm(carId);

    return CarMapper.CarEntityToCar(carEntity, this.sprocketRepository);
  }

  public List<Car> getAll() {
    return this.carRepository
        .findAll()
        .stream().map(carEntity -> CarMapper.CarEntityToCar(carEntity, this.sprocketRepository))
        .toList();
  }

  public void save(Car car) {
    if (car.getId() == null) {
      car.setId((long) (this.carRepository.findAll().size() + 1));
      SprocketEntity sprocketEntity = SprocketMapper.EngineSprocketToEngineSprocketEntity(car.getEngineSprocket());
      this.sprocketRepository.add(car.getId(), sprocketEntity);
      this.carRepository.add(CarMapper.CarToCarEntity(car));
    } else {
      this.carRepository.update(CarMapper.CarToCarEntity(car));
    }
  }

  public Car get(Long id) {
    CarEntity carEntity = this.carRepository.findById(id);

    return CarMapper.CarEntityToCar(carEntity, this.sprocketRepository);
  }
}
