package com.yuxi;

public class Bot extends Tank {
	int moveTime = 0;
	public Bot(String img, int x, int y, String upImage, String downImage,
			String leftImage, String rightImage, GamePanel gamePanel){
		super(img, x, y, upImage, downImage, leftImage, rightImage, gamePanel);
	}
	
	public void go(){
		attack();
		if (moveTime>=20) {
			direction = randomDirection();
			moveTime = 0;
		} else {
			moveTime += 1;
		}
		switch (direction) {
			case UP:
				upward();
				break;
			case DOWN:
				downward();
				break;
			case RIGHT:
				rightward();
				break;
			case LEFT:
				leftward();
				break;
		}
	}
	
	//电脑坦克随机方向
	public Direction randomDirection(){
		java.util.Random r = new java.util.Random();
		int rnum = r.nextInt(4);
		switch (rnum) {
		case 0:
			return Direction.UP;
		case 1:
			return Direction.RIGHT;
		case 2:
			return Direction.LEFT;
		default:
			return Direction.DOWN;
		}
	}
	
	//只有2%几率攻击
	public void attack(){
		java.awt.Point p = getHeadPoint();
		java.util.Random r = new java.util.Random();
		int rnum = r.nextInt(100);
		//System.out.println("r: " + rnum);
		if (rnum<2) {
			System.out.println(rnum);
			EnemyBullet enemyBullet = new EnemyBullet("images/bullet/bulletYellow.gif",p.x,p.y,direction,gamePanel);
			this.gamePanel.bulletsList.add(enemyBullet);
		}
	}
	
	@Override
	public void paintSelf(java.awt.Graphics g){
		g.drawImage(img, x, y, null);
		this.go();
	}
	
	@Override 
	public java.awt.Rectangle getRec(){
		return new java.awt.Rectangle(x, y, width, height);
	}
}

