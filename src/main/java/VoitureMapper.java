public class VoitureMapper {
  public static VoitureEntity VoitureToVoitureEntity(Voiture voiture) {
    return new VoitureEntity(voiture.getId(), voiture.getCouleur(), voiture.getType(), voiture.getNombreDeKm(), voiture.getHuileLevelMoteur());
  }

  public static Voiture VoitureEntityToVoiture(VoitureEntity voitureEntity, PignonMoteurRepository pignonMoteurRepository) {
    PignonMoteurEntity pignonMoteurEntity = pignonMoteurRepository.findByVoitureId(voitureEntity.getId());
    PignonMoteur pignonMoteur = PignonMapper.PignonMoteurEntityToPignonMoteur(pignonMoteurEntity);

    return new Voiture(
        voitureEntity.getId(), voitureEntity.getCouleur(), voitureEntity.getType(),
        voitureEntity.getNombreDeKm(), voitureEntity.getHuileLevelMoteur(),
        pignonMoteur
    );
  }
}
