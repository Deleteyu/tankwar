package com.yuxi;

public class Wall extends GameObject {

	public int width = 60;
	public int height = 60;
	public Wall(String img, int x, int y, GamePanel gamePanel){
		super(img, x, y, gamePanel);
	}
	
	@Override
	public void paintSelf(java.awt.Graphics g){
		g.drawImage(img, x, y, null);
	}
	
	@Override
	public java.awt.Rectangle getRec(){
		return new java.awt.Rectangle(x, y, width, height);
	}
}
