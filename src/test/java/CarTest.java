import car.Car;
import car.CarRepository;
import car.CarRepositoryInMemory;
import car.CarService;
import engine.Sprocket;
import engine.SprocketRepository;
import engine.SprocketRepositoryInMemory;
import exception.CarCouldNotStartException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.*;

public class CarTest {
  final Integer OIL_LEVEL_OK = 100;
  final Integer OIL_LEVEL_TOO_LOW = 10;

  @ParameterizedTest
  @EnumSource(Car.Line.class)
  void displayLigne(Car.Line line) {
    Car car = new Car(null, "Bleue", line, 0L, OIL_LEVEL_OK, null);

    assertThat(car.displayLigne()).isEqualTo("Cette voiture est une " + line);
  }

  @ParameterizedTest
  @EnumSource(Car.Line.class)
  void hasSunroof(Car.Line line) {
    Car car = new Car(null, "Bleue", line, 0L, OIL_LEVEL_OK, null);

    switch (car.getType()) {
      case QashqaiTekna, QashqaiVisia, QashqaiAcenta,
          Peugeot208Ligne1, Peugeot208Ligne2 -> assertThat(car.hasSunRoof()).isEqualTo(true);
      case QashqaiNConnecta, ScenicLigne1, ScenicLigne2 -> assertThat(car.hasSunRoof()).isEqualTo(false);
    }
  }

  @ParameterizedTest
  @EnumSource(Car.Line.class)
  void GetNumberOfSeats(Car.Line line) {
    switch (line) {
      case QashqaiAcenta, QashqaiNConnecta -> assertThat(Car.getNumberOfSeats(line)).isEqualTo(4F);
      case QashqaiTekna -> assertThat(Car.getNumberOfSeats(line)).isEqualTo(5F);
      default -> assertThat(Car.getNumberOfSeats(line)).isEqualTo(0F);
    }
  }

  @Test
  void saveCar() {
    CarRepository carRepository = new CarRepositoryInMemory("");
    SprocketRepository sprocketRepository = new SprocketRepositoryInMemory("");
    CarService carService = new CarService(carRepository, sprocketRepository);

    Sprocket sprocket = new Sprocket(true);
    Car car = new Car(
        null, "Bleue", Car.Line.Peugeot208Ligne1,
        0L, OIL_LEVEL_OK, sprocket
    );
    carService.save(car);

    assertThat(carService.getAll().size()).isEqualTo(1);
  }

  @Test
  void updateCarColor() {
    CarRepository carRepository = new CarRepositoryInMemory("");
    SprocketRepository sprocketRepository = new SprocketRepositoryInMemory("");
    CarService carService = new CarService(carRepository, sprocketRepository);

    Sprocket sprocket = new Sprocket(true);
    Car car = new Car(
        null, "Bleue", Car.Line.Peugeot208Ligne1,
        0L, OIL_LEVEL_OK, sprocket
    );
    carService.save(car);
    Car carToUpdate = carService.getAll().get(0);
    carToUpdate.setColor("Rouge");
    carService.save(carToUpdate);

    assertThat(carService.get(1L).getColor()).isEqualTo("Rouge");
    assertThat(carService.getAll().size()).isEqualTo(1);
  }

  @Test
  void updateCarType() {
    CarRepository carRepository = new CarRepositoryInMemory("");
    SprocketRepository sprocketRepository = new SprocketRepositoryInMemory("");
    CarService carService = new CarService(carRepository, sprocketRepository);

    Sprocket sprocket = new Sprocket(true);
    Car car = new Car(
        null, "Bleue", Car.Line.Peugeot208Ligne1,
        0L, OIL_LEVEL_OK, sprocket
    );
    carService.save(car);
    Car carToUpdate = carService.getAll().get(0);
    carToUpdate.setType(Car.Line.Peugeot208Ligne2);
    carService.save(carToUpdate);

    assertThat(carService.get(1L).getType()).isEqualTo(Car.Line.Peugeot208Ligne2);
    assertThat(carService.getAll().size()).isEqualTo(1);
  }

  @Test
  void shouldStart() {
    Sprocket sprocket = new Sprocket(true);
    Car car = new Car(
        null, "Bleue", Car.Line.Peugeot208Ligne1,
        0L, OIL_LEVEL_OK, sprocket
    );

    try {
      assertThat(car.start()).isTrue();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void shouldNotStartWhenSprocketDoesNotEngage() {
    Sprocket sprocket = new Sprocket(false);
    Car car = new Car(
        null, "Bleue", Car.Line.Peugeot208Ligne1,
        0L, OIL_LEVEL_OK, sprocket
    );

    try {
      assertThat(car.start()).isFalse();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void shouldNotStartWhenOilLevelTooLow() {
    Sprocket sprocket = new Sprocket(true);
    Car car = new Car(
        null, "Bleue", Car.Line.Peugeot208Ligne1,
        0L, OIL_LEVEL_TOO_LOW, sprocket
    );

    assertThatThrownBy(car::start)
        .isInstanceOf(CarCouldNotStartException.class)
        .hasMessage("Le moteur n'a pas pu être démarré");
  }

  @Test
  void DriveAKm() {
    CarRepository carRepository = new CarRepositoryInMemory("");
    SprocketRepository sprocketRepository = new SprocketRepositoryInMemory("");
    CarService carService = new CarService(carRepository, sprocketRepository);

    Sprocket sprocket = new Sprocket(true);
    Car car = new Car(
        null, "Bleue", Car.Line.Peugeot208Ligne1, 0L,
        OIL_LEVEL_OK, sprocket);
    carService.save(car);

    Car sameCar = carService.driveAKm(1L);

    assertThat(sameCar.getId()).isEqualTo(1);
    assertThat(sameCar.getNumberOfKm()).isEqualTo(1);
  }
}
