public class Flock{
  LinkedList<Boid> listBoid;
  public Flock(){
    listBoid= new ArrayList<Boid>();

    }
  public void add(Boid b){
    listBoid.add(b);
    }
}
