package com.yuxi;

import java.awt.*;

public class PlayerOne extends Tank {
	
	private boolean up = false;
	private boolean left = false;
	private boolean right = false;
	private boolean down = false;

	public PlayerOne(String img, int x, int y, String upImage,
			String downImage, String leftImage, String rightImage,GamePanel gamePanel) {
		super(img, x, y, upImage, downImage, leftImage, rightImage, gamePanel);
	}
	
	public  void keyPressed(java.awt.event.KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case java.awt.event.KeyEvent.VK_A:
			left = true;
			break;
		case java.awt.event.KeyEvent.VK_S:
			down = true;
			break;
		case java.awt.event.KeyEvent.VK_D:
			right = true;
			break;
		case java.awt.event.KeyEvent.VK_W:
			up = true;
			break;
		case java.awt.event.KeyEvent.VK_SPACE:
			this.attack();
			break;
		default:
			break;
		}
	}
	
	public void keyReleased(java.awt.event.KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case java.awt.event.KeyEvent.VK_A:
			left = false;
			break;
		case java.awt.event.KeyEvent.VK_S:
			down = false;
			break;
		case java.awt.event.KeyEvent.VK_D:
			right = false;
			break;
		case java.awt.event.KeyEvent.VK_W:
			up = false;
			break;
		default:
			break;
		}
	}
	
	public void move(){
		if (left) {
			leftward();
		}else if (right) {
			rightward();
		}else if (up) {
			upward();
		}else if (down) {
			downward();
		}
	}
	
 
	@Override
	public void paintSelf(Graphics g){
		g.drawImage(img, x, y, null);
		move();
	}
	
	@Override
	public Rectangle getRec(){
		return new java.awt.Rectangle(x, y, width, height);
	}

}
