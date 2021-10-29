import java.util.HashMap;

public class PignonMoteurRepositoryInMemory implements PignonMoteurRepository {
  private final HashMap<Long, PignonMoteurEntity> pignonsByVoitureId;

  public PignonMoteurRepositoryInMemory(String myConnectionString) {
    this.pignonsByVoitureId = new HashMap<>();
  }

  public PignonMoteurEntity findByVoitureId(Long voitureId) {
    return this.pignonsByVoitureId.get(voitureId);
  }

  public void add(Long voitureId, PignonMoteurEntity pignonMoteurEntity) {
    this.pignonsByVoitureId.put(voitureId, pignonMoteurEntity);
  }
}
