import java.util.Comparator;

//comparateur utilisé par EventManager
public class Comparateur implements Comparator<Event> {
  public int compare(Event e1, Event e2){//compare e1 et e2 donc l'ordre croissant de leurs date et, si ils sont égaux, dans l'ordre croissant de leurs pas
    if((int)(e1.getDate()-e2.getDate())==0){
      if((int)(e1.getPas()-e2.getPas())==0){
        return 1;//pour que le système voit que e1 != e2 pour s'assurer de bien ajouter l'Event (car chaques Event est différent lorsqu'on les ajoute, pour les fichiers mis en place en tout cas )
      }
      return (int)(e1.getPas()-e2.getPas());
    }
    return (int)(e1.getDate()-e2.getDate());
  }
}
