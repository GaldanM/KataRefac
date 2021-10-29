public interface PignonMoteurRepository {
  PignonMoteurEntity findByVoitureId(Long idToFind);

  void add(Long voitureId, PignonMoteurEntity pignonMoteurEntity);
}
