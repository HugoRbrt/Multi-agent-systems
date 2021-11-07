import java.util.ArrayList;
import java.awt.Color;

public abstract class Event{
//ATTRIBUTS
  private long date;
  private int pasDeTemps;
//constructeur
  public Event(long Date){
    if(Date>0){//ON POURRAIT AJOUTER UNE EXCEPTION
      date = Date;
    }
    }
//METHODES
  public long getDate(){
    return date;
    }
  public int getPas(){
    return pasDeTemps;
  }
  public void SetPasDeTemps(int Pas){
    try{
      if(Pas<1){throw new PasIncorrectException("Le pas dopit être strictement positif !");}
      pasDeTemps = Pas;
    }catch(PasIncorrectException e){
      System.out.println(e);
      System.exit(1);
    }

  }
  public abstract Event execute();
  public abstract void affichage(Color couleur);
  @Override
  public abstract String toString();
}

class PasIncorrectException extends Exception {
    public PasIncorrectException(String message) {
        super(message);
    }
}
