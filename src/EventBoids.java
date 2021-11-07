import java.awt.Color;
import java.util.Iterator;
import gui.* ;
import java.awt.Point;

public class EventBoids extends Event {
  private Flock boids;
  private GUISimulator gui;
  public EventBoids(long date, int pas, Flock b, GUISimulator Gui){
    super(date) ;
    super.SetPasDeTemps(pas) ;
    this.boids = b;
    this.gui =  Gui;
  }
  public EventBoids execute () {
    this.affichage(Color.BLACK);//on supprime les formes précédentes
    for(Boid b:boids.listBoid){
      b.flock(boids.listBoid);
    }
    for(Boid s:boids.listBoid){
      s.update();
    }
    this.affichage(Color.WHITE);//on affiche les nouvelles formes
    //on crée à la volé l'évenement suivant
    EventBoids e = new EventBoids(getDate()+super.getPas(),super.getPas(),boids,gui);
    return e;
  }
  public void affichage(Color couleur){
    Color c = couleur;
    if(!couleur.equals(Color.BLACK)){
      c = new Color((int)(couleur.getRed()*getPas()/2)%255,(int)(couleur.getGreen()*getPas()/3)%255,(int)(couleur.getBlue()*getPas()/4)%255);
    }
    if(!boids.listBoid.isEmpty()){
      Iterator<Boid> it=boids.listBoid.iterator();
      while(it.hasNext()){
        Boid b=it.next();
        gui.addGraphicalElement(new Oval((int)b.pos.getX(),(int)b.pos.getY(),Color.BLACK,c,10+3*super.getPas(),10+3*super.getPas()));
      }
    }
  }
  @Override
  public String toString(){
    return "date : " + super.getDate() + " pas de temps :" + super.getPas();
  }
}
