package model;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.JLabel;
import controller.cardController;

public class Card extends JLabel implements MouseListener{
	// data fields
	Icon faceIcon;
	Icon backIcon;
	//card is initially face down
	boolean faceUp = false;
	//card number
	int num;
	//half the dimensions of the back face icon
	int iconWidthHalf, iconHeightHalf;
	boolean mousePressedOnMe = false;
	cardController controller;
	/**
	* Constructor
	* @param face face of the card, two cards have the same face
	* @param back back of the card
	* @param num number corresponding to the face icon
	*/
	public Card(cardController controller, Icon face, Icon back, int num){
	super(back); //initially all cards face down
	this.controller=controller;
	this.faceIcon=face;
	this.backIcon=back;
	this.num=num;
	//catch mouse clicks
	this.addMouseListener(this);
	//(face or back) dimensions for mouse click testing
	this.iconHeightHalf=back.getIconHeight()/2;
	this.iconWidthHalf=face.getIconWidth()/2;
	}
	public int getNum(){return num;}//return the number of the card
	/**
	* Check the coordinates for mouse click
	* @param x
	* @param y
	* @return
	*/
	private boolean overIcon(int x, int y){
	int distX=Math.abs(x-(this.getWidth()/2));
	int distY=Math.abs(y-(this.getHeight()/2));
	//outside
	if (distX>this.iconWidthHalf||distY>this.iconHeightHalf)
	return false;
	//inside
	return true;
	}
	/**
	 * * turn face up
*/
public void turnUp(){
if (this.faceUp) return;
//this.faceUp=true; controller result checked in here
this.faceUp=this.controller.turnUp(this);
if(this.faceUp) this.setIcon(this.faceIcon);
}
public void turnDown(){
if (!this.faceUp)return;
this.setIcon(this.backIcon);
this.faceUp=false; //card is now down
}

public void mouseClicked(MouseEvent arg0) {
// TODO Auto-generated method stub
if (overIcon(arg0.getX(), arg0.getY())) this.turnUp();
}

public void mouseEntered(MouseEvent arg0) {
// TODO Auto-generated method stub
}

public void mouseExited(MouseEvent arg0) {
// TODO Auto-generated method stub
this.mousePressedOnMe=false;
}

public void mousePressed(MouseEvent arg0) {
// TODO Auto-generated method stub
if (overIcon(arg0.getX(),arg0.getY()))this.mousePressedOnMe=true;
}

public void mouseReleased(MouseEvent arg0) {
// TODO Auto-generated method stub
if (this.mousePressedOnMe){
this.mousePressedOnMe=false;
this.mouseClicked(arg0);
}
}
}
