class Vector{
  public float x;
  public float y; // pas besoin de mettre les attributs en private
  public float mag; // la norme du vecteur
  public Vector(){ // le constructeur par défaut, si l'on ne donne pas de coordonnées
    x=0;
    y=0;
    mag=0;
  }
  public Vector(int X, int Y){
    x=X;
    y=Y;
    mag=sqrt(x^2+y^2);
  }

  public void add(Vector vec){
    x+=vec.x;
    y+=vec.y;
  }
  public void sub(Vector vec){
    x-=vec.x;
    y-=vec.y;
  }
  public void mult(float scalar)(
    x*=scalar;
    y+=scalar;
  )
  public void div(float scalar){
    if(scalar==0){throw new ArithmeticException("Division par 0")};
    else{
      x=x/scalar;
      y=y/scalar;
    }
  }
  public void normalize(){
    if(mag!=0){
      x=x/mag;
      y=y/mag;
      mag=1;
    }
  public void limit(int Mag){
    if(mag>Mag){
      mag=Mag;
    }
  }

}
