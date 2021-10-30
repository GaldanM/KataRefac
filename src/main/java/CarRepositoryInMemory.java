import java.util.HashMap;
import java.util.List;

public class CarRepositoryInMemory implements CarRepository {
  private final HashMap<Long, CarEntity> carsById;

  public CarRepositoryInMemory(String myConnectionString) {
    this.carsById = new HashMap<>();
  }

  public CarEntity findById(Long idToFind) {
    return this.carsById.values().stream()
        .filter(car -> car.getId().equals(idToFind))
        .findFirst().orElse(null);
  }

  public List<CarEntity> findAll() {
    return this.carsById.values().stream()
        .filter(carEntity -> carEntity.getIsDeleted().equals(false))
        .toList();
  }

  public void add(CarEntity carEntity) {
    this.carsById.put(carEntity.getId(), carEntity);
  }

  public void update(CarEntity carEntityToUpdate) {
    this.carsById.put(carEntityToUpdate.getId(), carEntityToUpdate);
  }

  public CarEntity incrementNumberOfKm(Long carId) {
    CarEntity carEntityToUpdate = this.carsById.get(carId);
    carEntityToUpdate.setNumberOfKm(carEntityToUpdate.getNumberOfKm() + 1);
    this.carsById.put(carId, carEntityToUpdate);
    return carEntityToUpdate;
  }
}
