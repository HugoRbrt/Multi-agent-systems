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
		for(int i=0;i<20;i++){
      Boid b = new Boid(r.nextInt(HeightSize/3)+HeightSize/2,r.nextInt(WidthSize/3)+WidthSize/3,r.nextInt(5)-2,r.nextInt(5)-2);
      boids.add(b);
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
      // A CHOISIR ENTRE LES DEUX 5PAS LES 2 EN MËME TEMPS !
            //si on veut revenir toujours au même état de départ :
    /*
    for(Boid b:boidsInit.listBoid){
      boids.add(b.clone());
      this.affichage(Color.WHITE);//on affiche les nouvelles formes
    }
    */
            //si on veut refaire une nouvelle simulation à chaque restart

    Random r=new Random();
    for(Boid b:boidsInit.listBoid){
      boids.add(new Boid(r.nextInt(gui.getPanelHeight()/3)+gui.getPanelHeight()/2,r.nextInt(gui.getPanelWidth()/3)+gui.getPanelWidth()/3,r.nextInt(5)-2,r.nextInt(5)-2));
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
