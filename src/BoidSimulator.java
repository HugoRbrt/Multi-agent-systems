import java.awt.Color;
import gui.* ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Point;
import java.util.Random;
import java.util.Iterator;

class BoidSimulator implements Simulable{

  private Flock boids;
  private GUISimulator gui;
  private int HeightSize;
  private int WidthSize;

  public BoidSimulator(GUISimulator Gui,int HeightSize, int WidthSize){
    this.gui = Gui;
		this.HeightSize = HeightSize;
		this.WidthSize = WidthSize;
		int l = this.gui.getPanelHeight()/HeightSize;
		int c = this.gui.getPanelWidth()/WidthSize;
		this.boids = new Flock();
    Random r=new Random();
		for(int i=0;i<20;i++){
      boids.add(new Boid(r.nextInt(100),r.nextInt(100),r.nextInt(20),r.nextInt(20)));
    }
		this.affichage();
  }
@Override
public void next(){
  for(Boid b:boids.listBoid){
    b.flock(boids.listBoid);
    b.update();
    this.affichage();
  }
}
@Override
public void restart(){
  for(Boid b:boids.listBoid){
    b.pos=new Vector(0,0);
    this.affichage();
  }
}
  public void affichage(){
    Iterator<Boid> it=boids.listBoid.iterator();
    while(it.hasNext()){
      Boid b=it.next();
      gui.addGraphicalElement(new Oval((int)b.pos.getX(),(int)b.pos.getY(),Color.BLACK,Color.WHITE,10,10));
    }
  }
}
