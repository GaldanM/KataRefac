public class PignonMapper {
  public static PignonMoteurEntity PignonMoteurToPignonMoteurEntity(PignonMoteur pignonMoteur) {
    return new PignonMoteurEntity(pignonMoteur.getIsWorking());
  }

  public static PignonMoteur PignonMoteurEntityToPignonMoteur(PignonMoteurEntity pignonMoteurEntity) {
    return new PignonMoteur(pignonMoteurEntity.getIsWorking());
  }
}
