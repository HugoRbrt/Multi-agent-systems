import java.awt.Color;
import gui.* ;
import java.awt.Point;
import java.util.LinkedList;
import java.awt.Point;
import java.util.Random;

class BoidSimulator implements Simulable{
  import java.awt.Color;
  import gui.* ;
  import java.awt.Point;
  import java.util.LinkedList;
  import java.awt.Point;
  private Boids boids;
  private GUISimulator gui;
  private int HeightSize;
  private int WidthSize;

  public boidSimulator(GUISimulator Gui,int HeightSize, int WidthSize){
    this.gui = Gui;
		this.HeightSize = HeightSize;
		this.WidthSize = WidthSize;
		int l = this.gui.getPanelHeight()/HeightSize;
		int c = this.gui.getPanelWidth()/WidthSize;
		this.boids = new Boids();
    Random randomInt;
		for(int i=0;i<20;i++){
      boids.add(new Boid(r.nextInt(100),r.nextInt(100),r.nextInt(20),r.nextInt(20)));
    }
		this.affichage();
  }

  public void affichage(){
    Iterator<Boid> it=boids.iterator();
    while(it.hasNext()){
      Boid b=it.next();
      gui.addGraphicalElement(new Oval(b.x,b.y,COLOR.BLACK,COLOR.WHITE));
    }
  }
}
