import java.awt.Color;
import gui.* ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Point;
import java.util.Random;
import java.util.Iterator;

class BoidSimulator implements Simulable{
//ATTRIBUTS
  private Flock boids;
  private Flock boidsInit;
  private GUISimulator gui;
//CONSTRUCTEUR
  public BoidSimulator(GUISimulator Gui,int HeightSize, int WidthSize){
    this.gui = Gui;
		this.boids = new Flock();
    this.boidsInit = new Flock();
    Random r=new Random();
		for(int i=0;i<40;i++){
      Boid b = new Boid(r.nextInt(100),r.nextInt(100),r.nextInt(20),r.nextInt(20));
      boids.add(b.clone());
      boidsInit.add(b.clone());
    }
		this.affichage(Color.WHITE);
  }
//METHODES
  @Override
  public void next(){
    this.affichage(Color.BLACK);//on supprime les formes précédentes
    for(Boid b:boids.listBoid){
      b.flock(boids.listBoid);
    }
    for(Boid s:boids.listBoid){
      s.update();
    }
    this.affichage(Color.WHITE);//on affiche les nouvelles formes
  }
  @Override
  public void restart(){
    this.affichage(Color.BLACK);//on supprime les formes précédentes
    boids.clear();
    for(Boid b:boidsInit.listBoid){
      boids.add(b.clone());
      this.affichage(Color.WHITE);//on affiche les nouvelles formes
    }
  }
  public void affichage(Color couleur){
    Iterator<Boid> it=boids.listBoid.iterator();
    while(it.hasNext()){
      Boid b=it.next();
      gui.addGraphicalElement(new Oval((int)b.pos.getX(),(int)b.pos.getY(),Color.BLACK,couleur,10,10));
    }
  }
}
