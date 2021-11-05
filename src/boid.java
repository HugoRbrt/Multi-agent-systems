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
    maxMag=1000;
  }

  public Boid clone(){
    return new Boid(pos.getX(),pos.getY(),speed.getX(),speed.getY());
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
  public void applyForce(Vecteur force){
    Vecteur massMultForce = new Vecteur(force);
    massMultForce.mult(masse);
    acc.add(massMultForce); // à un instant t, (somme des force)=m*acc
  }
  public Vecteur seek(Vecteur target){ // fonction qui va diriger notre boid vers une cible
    Vecteur desired=new Vecteur(target.getX(),target.getY()); // la destination désirée
    desired.sub(speed);
    desired.normalize(); // on normalise le vecteur steer pour que le boid ne se téleporte pas directement à la destination désirée
    desired.mult(maxMag); // on fait en sorte que la norme du vecteur steer soit égal à la vitesse max
    Vecteur steer=new Vecteur(desired); // à l'approche de sa cible, une force steer va pousser le boid vers cette dernière
    steer.sub(this.speed);
    steer.limit(maxForce);
    return steer;
  }
  public float dist(Boid b){
    return (float)Math.sqrt((float)(Math.pow(pos.getX()-b.pos.getX(),2)+Math.pow((pos.getY()-b.pos.getY()),2)));
  }

  public Vecteur separate(ArrayList<Boid> list){
    float desiredDistance=15; //distance pour qu'un boid soit considéré comme trop près
    int count=0;
    Vecteur sum=new Vecteur();

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
    Vecteur avgSpeed=new Vecteur(0,0);
    int count =0;
    int neighboutMaxDist=50; //distance max de prise en compte des autres boids pour alignement
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
      Vecteur steer=new Vecteur(avgSpeed);
      steer.sub(this.speed);
      steer.limit(maxForce);
      return steer;
    }
    else{
      return new Vecteur(0,0);
    }
  }
  public Vecteur cohesion(ArrayList<Boid> boids){
    float neighboutMaxDist=2000; //distance max de prise en compte des autres boids pour cohesion
    float neighboutMinDist=200; //distance miin de prise en compte des autres boids pour cohesion
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
        Vecteur steer=new Vecteur();
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

    sep.mult((float)1.0);
    ali.mult((float)1.0);
    coh.mult((float)1.0);

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
