import java.util.ArrayList;

public abstract class Event{
//ATTRIBUTS
  private long date;
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
  public abstract void execute();
}
