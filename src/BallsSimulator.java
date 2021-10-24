import gui.* ;
public class BallsSimulator implements Simulable {
//ATTRIBUTS
	Balls b;
//METHODES
	public BallsSimulator(){
		this.b=new Balls(); //Constructeur ajouté
	}
	@Override
	public void next() {
    b.translate(10,10);
	}
	@Override
	public void restart() {
    b.reInit();
	}
}
