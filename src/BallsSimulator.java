import gui.GUISimulator ;
public class BallsSimulator implements Simulable {
//ATTRIBUTS
	Balls b;
//METHODES
	@Override
	public void next() {
    b.translate(10,10);
	}
	@Override
	public void restart() {
    b.reInit();
	}
}
