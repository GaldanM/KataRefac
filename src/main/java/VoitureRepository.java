import java.util.List;

public interface VoitureRepository {
  VoitureEntity findById(Long idToFind);

  List<VoitureEntity> findAll();

  void add(VoitureEntity voitureEntity);

  void update(VoitureEntity voitureEntityToUpdate);

  VoitureEntity incrementNombreDeKm(Long voitureId);
}
