public class VoitureMapper {
  public static VoitureEntity VoitureToVoitureEntity(Voiture voiture) {
    return new VoitureEntity(voiture.id, voiture.couleur, voiture.type, voiture.nombreDeKm, voiture.huileLevelMoteur);
  }

  public static Voiture VoitureEntityToVoiture(VoitureEntity voitureEntity, PignonMoteurRepository pignonMoteurRepository) {
    PignonMoteurEntity pignonMoteurEntity = pignonMoteurRepository.findByVoitureId(voitureEntity.id);
    PignonMoteur pignonMoteur = PignonMapper.PignonMoteurEntityToPignonMoteur(pignonMoteurEntity);

    return new Voiture(
        voitureEntity.id, voitureEntity.couleur, voitureEntity.type,
        voitureEntity.nombreDeKm, voitureEntity.huileLevelMoteur,
        pignonMoteur
    );
  }
}
