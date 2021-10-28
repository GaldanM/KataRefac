import java.util.HashMap;
import java.util.List;

public class DbContext {
  private final HashMap<Long, VoitureEntity> voituresById;

  public DbContext(String myConnectionString) {
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
    Voiture updatedVoiture = new Voiture(id, couleur, type, voitureEntityToUpdate.nombreDeKm);
    this.voituresById.put(id, new VoitureEntity(updatedVoiture));
  }
}
