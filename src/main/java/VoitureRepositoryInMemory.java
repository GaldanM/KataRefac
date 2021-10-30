import java.util.HashMap;
import java.util.List;

public class VoitureRepositoryInMemory implements VoitureRepository {
  private final HashMap<Long, VoitureEntity> voituresById;

  public VoitureRepositoryInMemory(String myConnectionString) {
    this.voituresById = new HashMap<>();
  }

  public VoitureEntity findById(Long idToFind) {
    return this.voituresById.values().stream()
        .filter(voiture -> voiture.getId().equals(idToFind))
        .findFirst().orElse(null);
  }

  public List<VoitureEntity> findAll() {
    return this.voituresById.values().stream().toList();
  }

  public void add(VoitureEntity voitureEntity) {
    this.voituresById.put(voitureEntity.getId(), voitureEntity);
  }

  public void update(VoitureEntity voitureEntityToUpdate) {
    this.voituresById.put(voitureEntityToUpdate.getId(), voitureEntityToUpdate);
  }

  public VoitureEntity incrementNombreDeKm(Long voitureId) {
    VoitureEntity voitureEntityToUpdate = this.voituresById.get(voitureId);
    voitureEntityToUpdate.setNombreDeKm(voitureEntityToUpdate.getNombreDeKm() + 1);
    this.voituresById.put(voitureId, voitureEntityToUpdate);
    return voitureEntityToUpdate;
  }
}
