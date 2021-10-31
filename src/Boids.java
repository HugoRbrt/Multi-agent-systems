public class Boids{
  LinkedList<Boid> listBoid;
  public Boids(){
    listBoid= new LinkedList<Boid>();

    }
  public void add(Boid b){
    listBoid.add(b);
    }
  public void next(){
    for(Boid b:listBoid){
      b.update();
    }
}
