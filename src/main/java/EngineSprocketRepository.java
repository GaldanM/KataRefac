public interface EngineSprocketRepository {
  EngineSprocketEntity findByCarId(Long idToFind);

  void add(Long carId, EngineSprocketEntity engineSprocketEntity);
}
