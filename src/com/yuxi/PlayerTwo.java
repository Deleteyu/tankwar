package com.yuxi;

import java.awt.*;

public class PlayerTwo extends Tank {
	private boolean up = false;
	private boolean left = false;
	private boolean right = false;
	private boolean down = false;

	public PlayerTwo(String img, int x, int y, String upImage,
			String downImage, String leftImage, String rightImage,GamePanel gamePanel) {
		super(img, x, y, upImage, downImage, leftImage, rightImage, gamePanel);
	}
	
	public  void keyPressed(java.awt.event.KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case java.awt.event.KeyEvent.VK_LEFT:
			left = true;
			break;
		case java.awt.event.KeyEvent.VK_DOWN:
			down = true;
			break;
		case java.awt.event.KeyEvent.VK_RIGHT:
			right = true;
			break;
		case java.awt.event.KeyEvent.VK_UP:
			up = true;
			break;
		case java.awt.event.KeyEvent.VK_K:
			this.attack();
			break;
		default:
			break;
		}
	}
	
	public void keyReleased(java.awt.event.KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case java.awt.event.KeyEvent.VK_LEFT:
			left = false;
			break;
		case java.awt.event.KeyEvent.VK_DOWN:
			down = false;
			break;
		case java.awt.event.KeyEvent.VK_RIGHT:
			right = false;
			break;
		case java.awt.event.KeyEvent.VK_UP:
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
