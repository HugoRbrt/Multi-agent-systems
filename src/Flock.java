import java.util.ArrayList;

//Flock : liste de Boids appartenant à un même groupe
public class Flock{
  ArrayList<Boid> listBoid;

  public Flock(){
    listBoid= new ArrayList<Boid>();

    }
  public void add(Boid b){//ajout d'un boids au flock
    listBoid.add(b);
    }
  public void clear(){//suppression de tous les boids du flock
    listBoid.clear();
  }
}
