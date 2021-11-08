import java.util.ArrayList;
import java.awt.Color;

public abstract class Event{
//ATTRIBUTS
  private long date;
  private int pasDeTemps;
//constructeur
  public Event(long Date){//constructeur : création d'un Event à la date Date
    try{
      if(Date<0){throw new DateIncorrectException("La date doit être positive !");}
      date = Date;
    }catch(DateIncorrectException e){
      System.out.println(e);
      System.exit(1);
    }
    }
//METHODES
  public long getDate(){//renvoie la date de l'évenement
    return date;
    }
  public int getPas(){//renvoie le pas de temps de l'évenement
    return pasDeTemps;
  }
  public void SetPasDeTemps(int Pas){//modifie le pas de temps de l'évenement
    try{
      if(Pas<1){throw new PasIncorrectException("Le pas doit être strictement positif !");}
      pasDeTemps = Pas;
    }catch(PasIncorrectException e){
      System.out.println(e);
      System.exit(1);
    }

  }
  public abstract Event execute();//exécute l'évenement
  public abstract void affichage(Color couleur);//affichage des voids associés à l'évenement
  @Override
  public abstract String toString();
}


//EXCEPTIONS
class PasIncorrectException extends Exception {
    public PasIncorrectException(String message) {
        super(message);
    }
}
class DateIncorrectException extends Exception {
    public DateIncorrectException(String message) {
        super(message);
    }
}
