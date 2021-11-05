import java.util.ArrayList;

public class Flock{
  ArrayList<Boid> listBoid;
  public Flock(){
    listBoid= new ArrayList<Boid>();

    }
  public void add(Boid b){
    listBoid.add(b);
    }
  public void clear(){
    listBoid.clear();
  }
}
