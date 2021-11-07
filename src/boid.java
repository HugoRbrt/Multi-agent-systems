import  java.lang.Math;
import java.util.ArrayList;
import java.util.Iterator;

class Boid{
  Vecteur pos; //premiere coordonnée de la position
  Vecteur speed;// deuxieme coordonnée de la position
  Vecteur acc;//l'acceleration
  float masse; //masse d'un boid
  float maxMag; //vitesse maximal d'une boids
  float maxForce; // la norme max d'une force sur boid
  public Boid(float x, float y, float vx, float vy){
    pos= new Vecteur(x,y);
    speed= new Vecteur(vx,vy);
    acc=new Vecteur(0,0);
    masse = 1;
    maxMag=10;
    maxForce=(float)0.03;
  }

  public Boid clone(){
    return new Boid(pos.getX(),pos.getY(),speed.getX(),speed.getY());
  }
  public void limit(){
    if(speed.getMag()>maxMag){
      speed.setMag(maxMag);
    }
  }
  public float dist(Boid b){
    return (float)Math.sqrt((float)(Math.pow(pos.getX()-b.pos.getX(),2)+Math.pow((pos.getY()-b.pos.getY()),2)));
  }
  public void update(){ // à chaque frame, on actualise la position du boid, qui correspond à la position du boid + la distance parcourue en 1s (qui est la vitesse)
    speed.add(acc);
    speed.limit(maxMag);
    pos.add(speed);
  }
  public void applyForce(Vecteur force){
    Vecteur massMultForce = new Vecteur(force);
    massMultForce.mult(masse);
    acc.add(massMultForce); // à un instant t, (somme des force)=m*acc
  }
  public Vecteur seek(Vecteur target){ // fonction qui va diriger notre boid vers une cible
    Vecteur desired=new Vecteur(target.getX(),target.getY()); // la destination désirée
    desired.sub(pos);
    desired.normalize(); // on normalise le vecteur steer pour que le boid ne se téleporte pas directement à la destination désirée
    desired.mult(maxMag); // on fait en sorte que la norme du vecteur steer soit égal à la vitesse max
    Vecteur steer=new Vecteur(desired); // à l'approche de sa cible, une force steer va pousser le boid vers cette dernière
    steer.sub(this.speed);
    steer.limit(maxForce);
    return steer;
  }

  public Vecteur separate(ArrayList<Boid> list){
    Vecteur sum=new Vecteur();
    int count=0;
    float desiredDistance=50; //distance pour qu'un boid soit considéré comme trop près

    for(Boid other:list){
      float distance=dist(other);
      if(distance>0 &&distance<desiredDistance){
        Vecteur forceDist=new Vecteur(this.pos);
        forceDist.sub(other.pos);
        forceDist.normalize();
        forceDist.div(distance);

        sum.add(forceDist);
        count++;
      }
    }
    if(count>0){
      sum.div(count);
      sum.setMag(maxMag);
      Vecteur steer=new Vecteur(sum);
      steer.sub(this.speed);
      steer.limit(maxForce);
      return steer;
    }
    else{
      return new Vecteur(0,0);
    }
  }
  public Vecteur align(ArrayList<Boid> listBoid){ //fonction qui renvoie un vecteur steer qui permet au boid d'aller à la vitesse moyenne du groupe
    Vecteur sum=new Vecteur();
    int count =0;
    int neighboutMaxDist=500; //distance max de prise en compte des autres boids pour alignement

    for(Boid other: listBoid){
      float dist=dist(other);
      if((dist>0)&& dist<neighboutMaxDist){
          sum.add(other.speed);
          count ++;
      }
    }
    if(count>0){
      sum.div(count);
      sum.setMag(maxMag);
      Vecteur steer=new Vecteur(sum);
      steer.sub(this.speed);
      steer.limit(maxForce);
      return steer;
    }
    else{
      return new Vecteur(0,0);
    }
  }
  public Vecteur cohesion(ArrayList<Boid> boids){
    float neighboutMaxDist=200; //distance max de prise en compte des autres boids pour cohesion
    float neighboutMinDist=30; //distance miin de prise en compte des autres boids pour cohesion
    Vecteur sum=new Vecteur(0,0);
    int count=0;
    for(Boid other:boids){
      float dist=dist(other);
      if(dist>neighboutMinDist && dist<neighboutMaxDist){
        sum.add(other.pos);
        count++;
      }
    }
      if(count>0){
        sum.div(count);
        return seek(sum);
      }
      else{
        return new Vecteur(0,0);
      }
  }

  void flock(ArrayList<Boid> boids) {
    Vecteur sep=separate(boids);
    Vecteur ali = align(boids);
    Vecteur coh = cohesion(boids);
    //facteur de multiplication pour chaques forces
    float SEPmult    = (float)1.0;
    float ALImult    = (float)1.0;
    float COHmult    = (float)1.0;
    sep.mult(SEPmult);
    ali.mult(ALImult);
    coh.mult(COHmult);
    // System.out.println(coh.getX());
    // System.out.println(ali.getX());
    // System.out.println(sep.getX());
    applyForce(sep);
    applyForce(ali);
    applyForce(coh);
  }
/*
  void arrive(Vecteur target){
    Vecteur desired=new Vecteur(target.getX(),target.getY());
    desired.sub(this.pos);
    float d=desired.getMag();
    desired.normalize();
    if(d<100){
      float m=map(d,0,100,maxMag);
      desired.mult(m);
    }
    else {
      desired.mult(maxMag);
    }
    Vecteur steer=new Vecteur(desired.getX(),desired.getY());
    steer.sub(this.speed);
    steer.limit(maxForce);
    applyForce(steer);
  }*/
}
