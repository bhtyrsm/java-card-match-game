package model;
import javax.swing.JLabel;

public class TurnCounter extends JLabel{
	
	private int number_of_turn = 0;
	
	public TurnCounter()
	{
		super();
		NewGameReset();
	}
	public void NewGameReset() {
		
		number_of_turn = 0;
		Last_Situation();	
	}

	public void inc() {
		
		number_of_turn++;
		Last_Situation();
		
	}

	private void Last_Situation() {
		
		setText("TURNS : " + Integer.toString(number_of_turn));
		
	}
}
