import  java.lang.Math;
import java.util.ArrayList;
import java.util.Iterator;

class Boid{
  Vector pos; //premiere coordonnée de la position
  Vector speed;// deuxieme coordonnée de la position
  Vector acc;//l'acceleration
  int masse; //masse d'un boid
  int maxMag; //vitesse maximal d'une boids
  int maxForce; // la norme max d'une force sur boid
  public Boid(float x, float y, float vx, float vy){
    pos= new Vector(x,y);
    speed= new Vector(vx,vy);
    acc=new Vector(0,0);
    masse = 1;
  }

  public void limit(){
    if(speed.getMag()>maxMag){
      speed.setMag(maxMag);
    }
  }
  public void update(){ // à chaque frame, on actualise la position du boid, qui correspond à la position du boid + la distance parcourue en 1s (qui est la vitesse)
    speed.add(acc);
    speed.limit(maxMag);
    pos.add(speed);
  }
  public void applyForce(Vector force){
    Vector massMultForce = new Vector(force);
    massMultForce.mult(masse);
    acc.add(massMultForce); // à un instant t, (somme des force)=m*acc
  }
  public Vector seek(Vector target){ // fonction qui va diriger notre boid vers une cible
    Vector desired=new Vector(target.getX(),target.getY()); // la destination désirée
    desired.sub(speed);
    desired.normalize(); // on normalise le vecteur steer pour que le boid ne se téleporte pas directement à la destination désirée
    desired.mult(maxMag); // on fait en sorte que la norme du vecteur steer soit égal à la vitesse max
    Vector steer=new Vector(desired); // à l'approche de sa cible, une force steer va pousser le boid vers cette dernière
    steer.sub(this.speed);
    steer.limit(maxForce);
    return steer;
  }
  public float dist(Boid b){
    return (float)Math.sqrt((float)(Math.pow(pos.getX()-b.pos.getX(),2)+Math.pow((pos.getY()-b.pos.getY()),2)));
  }

  public Vector separate(ArrayList<Boid> list){
    float desiredDistance=20; //valeur choisi arbitrairement
    int count=0;
    Vector forceRes=new Vector();
    Vector sum=new Vector();

    for(Boid other:list){
      float distance=dist(other);
      if(distance>0 &&distance<desiredDistance){
        Vector forceDist=new Vector(this.pos);
        forceDist.sub(other.pos);
        forceDist.normalize();
        forceDist.div(distance);
        forceRes.add(forceDist);
        count++;
      }
    }
    if(count>0){
      sum.div(count);
      sum.setMag(maxMag);
      Vector steer=new Vector(sum);
      steer.sub(this.speed);
      steer.limit(maxForce);
      return steer;
    }
    else{
      return new Vector(0,0);
    }
  }
  // void arrive(Vector target){
  //   Vector desired=new Vector(target.x,target.y);
  //   desired.sub(this.pos);
  //   float d=desired.mag;
  //   desired.normalize();
  //   if(d<100){
  //     float m=map(d,0,100,maxMag);
  //     desired.mult(m);
  //   }
  //   else {
  //     desired.mult(maxMag);
  //   }
  //   Vector steer=new Vector(desired.x,desired.y);
  //   steer.sub(this.speed);
  //   steer.limit(maxForce);
  //   applyForce(steer);
  //   }
  public Vector align(ArrayList<Boid> listBoid){ //fonction qui renvoie un vecteur steer qui permet au boid d'aller à la vitesse moyenne du groupe
    Vector avgSpeed=new Vector(0,0);
    int count =0;
    int neighboutMaxDist=50; //distance maximal pour qu'un boid soit considéré comme voisin (valeur arbitraire)
    for(Boid other: listBoid){
      float dist=dist(other);
      if((dist>0)&& dist<neighboutMaxDist){
          avgSpeed.add(other.speed);
          count ++;
      }
    }
    if(count>0){
      avgSpeed.div(count);
      avgSpeed.setMag(maxMag);
      Vector steer=new Vector(avgSpeed);
      steer.sub(this.speed);
      steer.limit(maxForce);
      return steer;
    }
    else{
      return new Vector(0,0);
    }
  }
  public Vector cohesion(ArrayList<Boid> boids){
    float neighboutMaxDist=50; //distance maximal pour qu'un boid soit considéré comme voisin (valeur arbitraire)
    Vector sum=new Vector(0,0);
    int count=0;
    for(Boid other:boids){
      float dist=dist(other);
      if(dist>0 && dist<neighboutMaxDist){
        sum.add(other.pos);
        count++;
      }
    }
      if(count>0){
        sum.div(count);
        Vector steer=new Vector();
        return seek(sum);
      }
      else{
        return new Vector(0,0);
      }
  }

  void flock(ArrayList<Boid> boids) {
    Vector sep=separate(boids);
    Vector ali = align(boids);
    Vector coh = cohesion(boids);

    sep.mult((float)1.5);
    ali.mult((float)1.0);
    coh.mult((float)1.0);

    applyForce(sep);
    applyForce(ali);
    applyForce(coh);
  }
}
