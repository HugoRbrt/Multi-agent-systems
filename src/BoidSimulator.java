import java.awt.Color;
import gui.* ;
import java.util.ArrayList;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Point;
import java.util.Random;
import java.util.Iterator;

class BoidSimulator implements Simulable{
//ATTRIBUTS
  private ArrayList<Flock> boids;
  private ArrayList<Flock> boidsInit;
  private GUISimulator gui;
  private EventManager manager;
//CONSTRUCTEUR
  public BoidSimulator(GUISimulator Gui,int HeightSize, int WidthSize, int nbFlock, int sizeOfFlock){
    this.gui = Gui;
		this.boids = new ArrayList<Flock>();
    this.boidsInit = new ArrayList<Flock>();
    this.manager = new EventManager();
    Random r=new Random();
    for(int k=1;k<nbFlock+1;k++){
      Flock f = new Flock();
      Flock fI= new Flock();
      for(int i=0;i<sizeOfFlock;i++){
        Boid b = new Boid(r.nextInt(HeightSize/3)+HeightSize/2,r.nextInt(WidthSize/3)+WidthSize/3,r.nextInt(5)-2,r.nextInt(5)-2);
        f.add(b);
        fI.add(b.clone());
      }
      this.manager.addFirstEvent(new EventBoids(0,k,f,gui));
      boids.add(f);
      boidsInit.add(fI);

    }
		next();
  }
//METHODES
  @Override
  public void next(){
    manager.next();
  }
  @Override
  public void restart(){
    gui.addGraphicalElement(new Rectangle(gui.getPanelWidth()/2,gui.getPanelHeight()/2, Color.BLACK, Color.BLACK,2*gui.getPanelWidth(), 2*gui.getPanelHeight()));
    //this.affichage(Color.BLACK);//on supprime les formes précédentes
    Iterator<Flock> itF = boids.iterator();
    Iterator<Flock> itFI = boidsInit.iterator();
    manager.restart();
    while(itF.hasNext()){
      Flock f = itF.next();
      Flock fI = itFI.next();
      f.clear();
      for(Boid b:fI.listBoid){
        f.add(b.clone());
      }
    }
    manager.next();
  }
}
