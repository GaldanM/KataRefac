import java.util.HashMap;

public class EngineSprocketRepositoryInMemory implements EngineSprocketRepository {
  private final HashMap<Long, EngineSprocketEntity> sprocketsByCarId;

  public EngineSprocketRepositoryInMemory(String myConnectionString) {
    this.sprocketsByCarId = new HashMap<>();
  }

  public EngineSprocketEntity findByCarId(Long carId) {
    return this.sprocketsByCarId.get(carId);
  }

  public void add(Long carId, EngineSprocketEntity engineSprocketEntity) {
    this.sprocketsByCarId.put(carId, engineSprocketEntity);
  }
}
