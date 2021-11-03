class boid{
  Vector pos; //premiere coordonnée de la position
  Vector speed;// deuxieme coordonnée de la position
  Vector acc;//l'acceleration
  int masse;
  int maxMag;
  int maxForce; // la norme max d'une force sur boid
  public boid(float x, float y, float vx, float vy){
    pos= new Vector(x,y);
    speed= new Vector(vx,vy);
  }
  @Override
  public limit(){
    if(Speed.mag>maxMag){
      Speed.mag=maxMag;
    }
  }
  public update(){ // à chaque frame, on actualise la position du boid, qui correspond à la position du boid + la distance parcourue en 1s (qui est la vitesse)
    Speed.add(acc);
    Speed.limit();
    Pos.add(Speed);
  }
  public applyForce(Vector force){
    acc=acc.add(force); // à un instant t, (somme des force)=m*acc, or m=1 ici
  }
  public void seek(Vector target){ // fonction qui va diriger notre boid vers une cible
    Vector desired=new Vector(target.x,target.y); // la destination désirée
    desired.sub(speed);
    desired.normalize(); // on normalise le vecteur steer pour que le boid ne se téleporte pas directement à la destination désirée
    desired.mult(maxMag); // on fait en sorte que la norme du vecteur steer soit égal à la vitesse max
    steer=new Vector(desired.x, desired.y); // à l'approche de sa cible, une force steer va pousser le boid vers cette dernière
    steer.limit(maxForce);
    this.applyForce(steer);
  }
  public int dist(Boid b){
    return sqrt((b-this.x)^2+(b.y-this.y)^2);
  }
  public void separate(ArrayList<Boid> list){
    float distance=0;
    float desiredDistance=20;
    Vector forceRes=new Vector();
    int count=0
    Vector sum=new Vector();
    for(Boid other:list){
      distance=this.dist(other);
      if(distance>0 &&distance<desiredDistance){
        Vector forceDist=Vector.sub(pos,other.pos);
        forceDist.normalize();
        forceRes.add(forceDist);
        count++;
      }
    }
    if(count>0){
      sum.div(count);
      sum.mag=maxMag;
      Vector steer=Vector.sub(sum,this.speed);
      steer.limit(maxForce);
      applyForce(steer);
    }
  }
  void arrive(Vector Target){
    Vector desired=Vector.sub(target, this.pos);
    float d=desired.mag();
    desired.normalize();
    if(d<100){
      float m=map(d,0,100,maxMag);
      desired.mult(m);
    }
    else {
      desired.mult(maxMag);
    }
    Vector steer=Vector.sub(desired,velocity);
    steer.limit(maxForce);
    applyForce(steer);
    }



  public Vector align(ArrayList<Boid> listBoid){ //fonction qui renvoie un vecteur steer qui permet au boid d'aller à la vitesse moyenne du groupe
    Vector avgSpeed=new Vector(0,0);
    Vector neighboutMaxDist=50;
    for(Boid b:list){
      float distToOther=Vector.sub(this.pos,other.pos);
      if((distToOther>0)&&(distToOther)<neighboutMaxDist){
          avgSpeed.add(b.speed);
      }
      else{
        return new Vector(0,0);
      }
    }
    avgSpeed.div(boids.size());
    avgSpeed.setMag(maxMag);
    Vector steer=Vector.sub(avgSpeed,velocity);
    steer.limit(maxForce);
    return steer;
  }
  public void cohesion(ArrayList<Boid> boids){
    float neighboutMaxDist=50;
    Vector sum=new Vector(0,0);
    int count=0;
    for(Boid other:boids){
      float distToOther=Vector.dist(this.pos,other.pos);
      if((distToOther>0)&&(distToOther)<neighboutMaxDist){
        sum.add(other.pos);
        count++;

      }
      if(count>0){
        sum.div(count);
        return seek(sum);
      }
      else {
        return new Vector(0,0);
      }
    }
  }
  void flock(ArrayList<Boid> boids) {
    Vector sep = separate(boids);
    Vector ali = align(boids);
    Vector coh = cohesion(boids);


    sep.mult(1.5);
    ali.mult(1.0);
    coh.mult(1.0);

    applyForce(sep);
    applyForce(ali);
    applyForce(coh);
  }
}
