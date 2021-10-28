import java.util.List;

public interface DbContext {
  VoitureEntity findById(Long idToFind);

  List<VoitureEntity> findAll();

  void add(VoitureEntity voitureEntity);

  void update(Long id, String couleur, Voiture.Ligne type);
}
