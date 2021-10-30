package engine;

public interface SprocketRepository {
  SprocketEntity findByCarId(Long idToFind);

  void add(Long carId, SprocketEntity sprocketEntity);
}
