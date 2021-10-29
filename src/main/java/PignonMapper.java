public class PignonMapper {
  public static PignonMoteurEntity PignonMoteurToPignonMoteurEntity(PignonMoteur pignonMoteur) {
    return new PignonMoteurEntity(pignonMoteur.isWorking);
  }

  public static PignonMoteur PignonMoteurEntityToPignonMoteur(PignonMoteurEntity pignonMoteurEntity) {
    return new PignonMoteur(pignonMoteurEntity.isWorking);
  }
}
