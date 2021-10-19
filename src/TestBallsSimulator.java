import gui.GUISimulator;
import java.awt.Color;


public class TestBallsSimulator{
	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
		gui.setSimulable(new BallsSimulator()) ;
	}
}
public class BallsSimulator implements Simulable {
//ATTRIBUTS
	Balls b;
//METHODES	
	@Override
	public void next() {
		
	}
	@Override
	public void restart() {
		
	}
}
