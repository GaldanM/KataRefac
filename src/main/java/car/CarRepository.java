package car;

import java.util.List;

public interface CarRepository {
  CarEntity findById(Long idToFind);

  List<CarEntity> findAll();

  void add(CarEntity carEntity);

  void update(CarEntity carEntityToUpdate);

  CarEntity incrementNumberOfKm(Long carId);
}
