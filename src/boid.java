import  java.lang.Math;
import java.util.ArrayList;
import java.util.Iterator;

//Boids : élément ayant une position, vitesse, accélaration et masse. Limité par une vitesse max et une force max que l'on peut lui appliquer
class Boid{
  Vecteur pos; //premiere coordonnée de la position
  Vecteur speed;// deuxieme coordonnée de la position
  Vecteur acc;//l'acceleration
  float masse; //masse d'un boid
  float maxMag; //vitesse maximal d'une boids
  float maxForce; // la norme max d'une force sur boid
  public Boid(float x, float y, float vx, float vy){//constructeur : initialise la position et la vitesse du boids, sa masse à 1 et les limitations sont appliqué expérimentalement (pour avoir une simulation visuellement parlante)
    pos= new Vecteur(x,y);
    speed= new Vecteur(vx,vy);
    acc=new Vecteur(0,0);
    masse = 1;
    maxMag=10;
    maxForce=(float)0.03;
  }
  public Boid clone(){//renvoie un nouveau Boids dont les propriétés sont identique à this
    return new Boid(pos.getX(),pos.getY(),speed.getX(),speed.getY());
  }
  public void limit(){//limite la vitesse du boids
    if(speed.getMag()>maxMag){
      speed.setMag(maxMag);
    }
  }
  public float dist(Boid b){//renvoie la distance entre this et b
    return (float)Math.sqrt((float)(Math.pow(pos.getX()-b.pos.getX(),2)+Math.pow((pos.getY()-b.pos.getY()),2)));
  }
  public void update(){ // on actualise la position du boid, qui correspond à la position du boid + la distance parcourue en 1s (qui est la vitesse)
    speed.add(acc);
    speed.limit(maxMag);
    pos.add(speed);
  }
  public void applyForce(Vecteur force){//on applique force sur le Boids (ce qui modifie l'accélération du Boids selon le PFD)
    Vecteur massMultForce = new Vecteur(force);
    massMultForce.mult(masse);
    acc.add(massMultForce); // à un instant t, (somme des force)=m*acc
  }
  public Vecteur seek(Vecteur target){ // fonction qui va diriger notre boid vers une cible target
    Vecteur desired=new Vecteur(target.getX(),target.getY()); // la destination désirée
    desired.sub(pos);
    desired.normalize(); // on normalise le vecteur steer pour que le boid ne se téleporte pas directement à la destination désirée
    desired.mult(maxMag); // on fait en sorte que la norme du vecteur steer soit égal à la vitesse max
    Vecteur steer=new Vecteur(desired); // à l'approche de sa cible, une force steer va pousser le boid vers cette dernière
    steer.sub(this.speed);
    steer.limit(maxForce);
    return steer;
  }

  public Vecteur separate(ArrayList<Boid> list){//force qui a tendance à séparer les Boids lorsque les Boids dans list sont trop proche de this
    Vecteur sum=new Vecteur();
    int count=0;
    float desiredDistance=50; //distance pour qu'un boid soit considéré comme trop près(valeur définit expérimentalement pour avoir une simulation visuellement parlante)
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
  public Vecteur align(ArrayList<Boid> listBoid){//force qui a tendance à aligner l'orientation des Boids lorsque les Boids dans list sont suffisament proche de this
    Vecteur sum=new Vecteur();
    int count =0;
    int neighboutMaxDist=500;  //distance pour qu'un boid soit considéré comme trop loin (valeur définit expérimentalement pour avoir une simulation visuellement parlante) pour appliquer la force

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
  public Vecteur cohesion(ArrayList<Boid> boids){//force qui a tendance à rapprocher les Boids lorsque les Boids dans list sont trop loin de this
    float neighboutMaxDist=200;  //distance pour qu'un boid soit considéré comme trop loin(valeur définit expérimentalement pour avoir une simulation visuellement parlante) pour appliquer la force
    float neighboutMinDist=30;  //distance pour qu'un boid soit considéré comme trop près(valeur définit expérimentalement pour avoir une simulation visuellement parlante) pour appliquer le force
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

  void flock(ArrayList<Boid> boids) {//calcul les forces s'exercant la la liste boids et applique ses forces
    Vecteur sep=separate(boids);
    Vecteur ali = align(boids);
    Vecteur coh = cohesion(boids);
    //facteur de multiplication pour chaques forces
    float SEPmult    = (float)1.0;
    float ALImult    = (float)1.2;
    float COHmult    = (float)1.3;
    sep.mult(SEPmult);
    ali.mult(ALImult);
    coh.mult(COHmult);

    applyForce(sep);
    applyForce(ali);
    applyForce(coh);
  }
}
