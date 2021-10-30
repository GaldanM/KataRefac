package engine;

import java.util.HashMap;

public class SprocketRepositoryInMemory implements SprocketRepository {
  private final HashMap<Long, SprocketEntity> sprocketsByCarId;

  public SprocketRepositoryInMemory(String myConnectionString) {
    this.sprocketsByCarId = new HashMap<>();
  }

  public SprocketEntity findByCarId(Long carId) {
    return this.sprocketsByCarId.get(carId);
  }

  public void add(Long carId, SprocketEntity sprocketEntity) {
    this.sprocketsByCarId.put(carId, sprocketEntity);
  }
}
