import java.util.Comparator;

public class Comparateur implements Comparator<Event> {
  public int compare(Event e1, Event e2){
    return (int)(e1.getDate()-e2.getDate());
  }
}