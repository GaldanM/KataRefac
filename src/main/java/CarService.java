import java.util.List;

public class CarService {
  CarRepository carRepository;
  EngineSprocketRepository engineSprocketRepository;

  public CarService(CarRepository carRepository, EngineSprocketRepository engineSprocketRepository) {
    this.carRepository = carRepository;
    this.engineSprocketRepository = engineSprocketRepository;
  }

  public Car driveAKm(Long carId) {
    CarEntity carEntity = this.carRepository.incrementNumberOfKm(carId);

    return CarMapper.CarEntityToCar(carEntity, this.engineSprocketRepository);
  }

  public List<Car> getAll() {
    return this.carRepository
        .findAll()
        .stream().map(carEntity -> CarMapper.CarEntityToCar(carEntity, this.engineSprocketRepository))
        .toList();
  }

  public void save(Car car) {
    if (car.getId() == null) {
      car.setId((long) (this.carRepository.findAll().size() + 1));
      EngineSprocketEntity engineSprocketEntity = EngineSprocketMapper.EngineSprocketToEngineSprocketEntity(car.getEngineSprocket());
      this.engineSprocketRepository.add(car.getId(), engineSprocketEntity);
      this.carRepository.add(CarMapper.CarToCarEntity(car));
    } else {
      this.carRepository.update(CarMapper.CarToCarEntity(car));
    }
  }

  public Car get(Long id) {
    CarEntity carEntity = this.carRepository.findById(id);

    return CarMapper.CarEntityToCar(carEntity, this.engineSprocketRepository);
  }
}
