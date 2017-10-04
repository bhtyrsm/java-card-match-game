package controller;
import model.TurnCounter;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.Card;

import java.util.Vector;

public class cardController implements ActionListener{
//data fields
	
private Vector turnedCards;
private TurnCounter turnCounterLabel;
Container mainContentPain;
private Timer turnDownTimer;
private final int turnDownDelay=2000;
public cardController(){
this.turnedCards=new Vector(2);
this.turnDownTimer=new Timer(this.turnDownDelay, this);
this.turnDownTimer.setRepeats(false);
}




public cardController(TurnCounter turnCounterLabel, Container mainContentPane) {
	// TODO Auto-generated constructor stub
	// save parameter
			this.turnCounterLabel = turnCounterLabel;
			this.mainContentPain = mainContentPane;
			// create list
			this.turnedCards = new Vector(2);
			// make timer object
			this.turnDownTimer = new Timer(this.turnDownDelay, this);
			this.turnDownTimer.setRepeats(false);
}











private boolean doAddCard(Card card)
{

	
	// add the card to the list
			this.turnedCards.add(card);
			if(this.turnedCards.size() == 2)
			{

				this.turnCounterLabel.inc();

				Card otherCard = (Card)this.turnedCards.get(0);

				if( otherCard.getNum() == card.getNum())
					this.turnedCards.clear();
					

				else
					(this.turnDownTimer).start();
					
			}
			return true;
	
			}


	public boolean turnUp(Card card){
	if (this.turnedCards.size()<2)return doAddCard(card);
	return false;
	}
	
	public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	for (int i=0;i< this.turnedCards.size();i++){
	Card card=(Card)this.turnedCards.get(i);
	card.turnDown();
	}
	this.turnedCards.clear();
	// we need to add this controller to the Card class and also we need to use
	// methods in the view class
	}
	}
