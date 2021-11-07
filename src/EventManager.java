import java.util.TreeSet;
import java.util.Iterator;
import java.util.Comparator;
import gui.* ;
/*
cette classe possède une liste ordonnée d'événement dans l'ordre croissant de date d'évenement,
currentDate définit la date à laquelle nous sommes,
en invoquant next, on réalise tous les événements <= à currentDate
*/
public class EventManager{
//ATTRIBUTS
  private long currentDate;
  TreeSet<Event> listEvent;
  TreeSet<Event> listEventErased;
//CONSTRUCTEUR
  public EventManager(){
    currentDate = 0;
    listEvent = new TreeSet<Event>(new Comparateur());
    listEventErased = new TreeSet<Event>(new Comparateur());
    }
//METHODES
  public void addEvent(Event e){
    listEvent.add(e);
    }
  public void next(){
    currentDate++;
    Event e = listEvent.pollFirst();//premier evenement à faire pas encore fait
    while(e!=null && e.getDate()<=currentDate){//tant qu'il y a des evenements et que sa date et est <= a currentDate
      e.execute();//on exécute l'évenement
      listEventErased.add(e);//on ajoute l'évenements parmis ceux déja fait donc effacé
      e = listEvent.pollFirst();//on passe à l'évenement suivant
    }
    if(e!=null){listEvent.add(e);}//on ajoute l'élément testé plus grand que currentDate si il n'est pas nul}
  }
  public boolean isFinished(){
    return listEvent.isEmpty();
  }
  public void restart(){
    listEvent.addAll(listEventErased);
    currentDate =0;
  }
}
