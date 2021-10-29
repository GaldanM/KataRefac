import java.util.List;

public class VoitureService {
  VoitureRepository voitureRepository;
  PignonMoteurRepository pignonMoteurRepository;

  public VoitureService(VoitureRepository voitureRepository, PignonMoteurRepository pignonMoteurRepository) {
    this.voitureRepository = voitureRepository;
    this.pignonMoteurRepository = pignonMoteurRepository;
  }

  public Voiture GetVoitureAndUpdateKms(Long voitureId) {
    VoitureEntity voitureEntity = this.voitureRepository.incrementNombreDeKm(voitureId);

    return VoitureMapper.VoitureEntityToVoiture(voitureEntity, this.pignonMoteurRepository);
  }

  public List<Voiture> GetAll() {
    return this.voitureRepository
        .findAll()
        .stream().map(voitureEntity -> VoitureMapper.VoitureEntityToVoiture(voitureEntity, this.pignonMoteurRepository))
        .toList();
  }

  public void Save(Voiture voiture) {
    if (voiture.id == null) {
      voiture.id = (long) (this.voitureRepository.findAll().size() + 1);
      PignonMoteurEntity pignonMoteurEntity = PignonMapper.PignonMoteurToPignonMoteurEntity(voiture.pignonMoteur);
      this.pignonMoteurRepository.add(voiture.id, pignonMoteurEntity);
      this.voitureRepository.add(VoitureMapper.VoitureToVoitureEntity(voiture));
    } else {
      this.voitureRepository.update(VoitureMapper.VoitureToVoitureEntity(voiture));
    }
  }

  public Voiture Get(Long id) {
    VoitureEntity voitureEntity = this.voitureRepository.findById(id);

    return VoitureMapper.VoitureEntityToVoiture(voitureEntity, this.pignonMoteurRepository);
  }
}
