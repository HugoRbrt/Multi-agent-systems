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
}
