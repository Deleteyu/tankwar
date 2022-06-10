package com.yuxi;

import java.awt.*;

public class BlastObj extends GameObject {
	 static java.awt.Image[] imgs = new Image[8];
	 
	 int explodeCount = 0;
	 
	 static{
		 for (int i = 0; i < 8; i++) {
			imgs[i] = java.awt.Toolkit.getDefaultToolkit().getImage("images/blast/blast" +(i + 1)+".gif");
		}
	 }
	 
	 public BlastObj(){
		 super();
	 }
	 
	 public BlastObj(int x, int y){
		 this.x = x;
		 this.y = y;
	 }
	 
	 @Override
	 public void paintSelf(java.awt.Graphics g){
		 //绘制点击爆炸效果
		 if (explodeCount < 8) {
			g.drawImage(imgs[explodeCount], x, y, null);
			explodeCount++;
		}
	 }
	 
	 @Override
	 public java.awt.Rectangle getRec(){
		 return null;
	 }
}
