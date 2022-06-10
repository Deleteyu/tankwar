package com.yuxi;

import java.awt.*;

public abstract class GameObject {
	//图片
	java.awt.Image img;
	//坐标
	int x;
	int y;
	//游戏元素的宽高
	int width;
	int height;
	//游戏元素的移动速度
	int speed;
	//游戏元素的移动方向
	Direction direction;
	//界面
	GamePanel gamePanel;
	
	public GameObject(){}
	public GameObject(String img,int x,int y,GamePanel gamePanel){
		this.img = java.awt.Toolkit.getDefaultToolkit().getImage(img);
		this.x = x;
		this.y = y;
		this.gamePanel = gamePanel;
	}
	public java.awt.Image getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = java.awt.Toolkit.getDefaultToolkit().getImage(img);
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public GamePanel getGamePanel() {
		return gamePanel;
	}
	
	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	//继承元素绘制自己的方法
	public abstract void paintSelf(Graphics g);
	
	//获取当前游戏元素的矩形,是为碰撞检测而写
	public abstract java.awt.Rectangle getRec();
	
}
