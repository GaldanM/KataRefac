import java.util.HashMap;
import java.util.List;

public class DbContextInMemory implements DbContext {
  private final HashMap<Long, VoitureEntity> voituresById;

  public DbContextInMemory(String myConnectionString) {
    this.voituresById = new HashMap<>();
  }

  public VoitureEntity findById(Long idToFind) {
    return this.voituresById.values().stream()
        .filter(voiture -> voiture.id.equals(idToFind))
        .findFirst().orElse(null);
  }

  public List<VoitureEntity> findAll() {
    return this.voituresById.values().stream().toList();
  }

  public void add(VoitureEntity voitureEntity) {
    this.voituresById.put(voitureEntity.id, voitureEntity);
  }

  public void update(Long id, String couleur, Voiture.Ligne type) {
    VoitureEntity voitureEntityToUpdate = this.voituresById.get(id);
    Voiture updatedVoiture = new Voiture(
        id, couleur, type, voitureEntityToUpdate.nombreDeKm,
        this, null, 100
    );
    this.voituresById.put(id, new VoitureEntity(updatedVoiture));
  }
}
