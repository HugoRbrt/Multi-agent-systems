import java.util.TreeSet;
import java.util.Iterator;
import java.util.Comparator;
import gui.* ;
import java.awt.Color;
/*
cette classe possède une liste ordonnée d'événement dans l'ordre croissant de date d'évenement,
currentDate définit la date à laquelle nous sommes,
en invoquant next, on réalise tous les événements <= à currentDate
*/
public class EventManager{
//ATTRIBUTS
  private long currentDate;
  TreeSet<Event> listEvent;
  TreeSet<Event> listEventInit;
  TreeSet<Event> listEventErased;
//CONSTRUCTEUR
  public EventManager(){
    currentDate = 0;
    listEvent = new TreeSet<Event>(new Comparateur());
    listEventInit = new TreeSet<Event>(new Comparateur());
    }
//METHODES
  public void addFirstEvent(Event e){
  listEvent.add(e);
  listEventInit.add(e);
  }
  private void addEvent(Event e){
    listEvent.add(e);
    }
  public void next(){
    currentDate++;
    TreeSet<Event> l = new TreeSet<Event>(new Comparateur());
    Event e = listEvent.pollFirst();//premier evenement à faire pas encore fait
    while(e!=null && e.getDate()<=currentDate){//tant qu'il y a des evenements et que sa date et est <= a currentDate
      l.add(e.execute());//on exécute l'évenement
      e = listEvent.pollFirst();//on passe à l'évenement suivant
    }
    listEvent.addAll(l);
    if(e!=null){listEvent.add(e);}//on ajoute l'élément testé plus grand que currentDate si il n'est pas nul}
  }
  public boolean isFinished(){
    return listEvent.isEmpty();
  }
  public void restart(){
    listEvent.clear();
    currentDate =0;
    //on replace les événements initiaux dans listEvent
    Iterator<Event> it = listEventInit.iterator();
    while(it.hasNext()){
      listEvent.add(it.next());
    }
  }
}
