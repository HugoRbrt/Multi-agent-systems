import  java.lang.Math;
import java.util.ArrayList;
import java.util.Iterator;

class Boid{
  Vector pos; //premiere coordonnée de la position
  Vector speed;// deuxieme coordonnée de la position
  Vector acc;//l'acceleration
  int masse;
  int maxMag;
  int maxForce; // la norme max d'une force sur boid
  public Boid(float x, float y, float vx, float vy){
    pos= new Vector(x,y);
    speed= new Vector(vx,vy);
    acc=new Vector(0,0);
  }

  public void limit(){
    if(speed.mag>maxMag){
      speed.mag=maxMag;
    }
  }
  public void update(){ // à chaque frame, on actualise la position du boid, qui correspond à la position du boid + la distance parcourue en 1s (qui est la vitesse)
    speed.add(acc);
    speed.limit(maxMag);
    pos.add(speed);
  }
  public void applyForce(Vector force){
    acc.add(force); // à un instant t, (somme des force)=m*acc, or m=1 ici
  }
  public Vector seek(Vector target){ // fonction qui va diriger notre boid vers une cible
    Vector desired=new Vector(target.x,target.y); // la destination désirée
    desired.sub(speed);
    desired.normalize(); // on normalise le vecteur steer pour que le boid ne se téleporte pas directement à la destination désirée
    desired.mult(maxMag); // on fait en sorte que la norme du vecteur steer soit égal à la vitesse max
    Vector steer=new Vector(desired.x, desired.y); // à l'approche de sa cible, une force steer va pousser le boid vers cette dernière
    steer.limit(maxForce);
    return steer;
  }
  public float dist(Boid b){
    return (float)Math.sqrt((float)(Math.pow(pos.x-b.pos.x,2)+Math.pow((pos.y-b.pos.y),2)));
  }
  public Vector separate(ArrayList<Boid> list){
    float distance=0;
    float desiredDistance=20;
    Vector forceRes=new Vector();
    int count=0;
    Vector sum=new Vector();
    for(Boid other:list){
      distance=dist(other);
      if(distance>0 &&distance<desiredDistance){
        Vector forceDist=new Vector(pos.x,pos.y);
        forceDist.sub(other.pos);
        forceDist.normalize();
        forceRes.add(forceDist);
        count++;
      }
    }
    if(count>0){
      sum.div(count);
      sum.mag=maxMag;
      Vector steer=new Vector(sum.x,sum.y);
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
    int neighboutMaxDist=50;
    for(Boid other:listBoid){
      float distToOther=dist(other);
      if((distToOther>0)&&(distToOther)<neighboutMaxDist){
          avgSpeed.add(other.speed);
      }
      else{
        return new Vector(0,0);
      }
    }
    avgSpeed.div(listBoid.size());
    avgSpeed.mag=maxMag;
    Vector steer=new Vector(avgSpeed.x,avgSpeed.y);
    steer.sub(this.speed);
    steer.limit(maxForce);
    return steer;
  }
  public Vector cohesion(ArrayList<Boid> boids){
    float neighboutMaxDist=50;
    Vector sum=new Vector(0,0);
    int count=0;
    for(Boid other:boids){
      float distToOther=dist(other);
      if((distToOther>0)&&(distToOther)<neighboutMaxDist){
        sum.add(other.pos);
        count++;

      }
    }
      if(count>0){
        sum.div(count);
        Vector steer=new Vector();
        steer= seek(sum);
        return sum;
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
