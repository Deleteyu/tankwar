package com.yuxi;

public class EnemyBullet extends Bullet {
	public EnemyBullet(String img, int x, int y, Direction direction, GamePanel gamePanel){
		super(img, x, y, direction, gamePanel);
	}
	
	public void hitTank(){
		java.awt.Rectangle next = this.getRec();
		java.util.List<Tank> tanks = this.gamePanel.tankList;
		//子弹和tank
		for (Tank tank : tanks) {
			if (tank.getRec().intersects(next)) {
				System.out.println("hit tank");
				tank.alive = false;
				this.gamePanel.blastList.add(new BlastObj(tank.x-34, tank.y-14));
				this.gamePanel.tankList.remove(tank);
				this.gamePanel.removeList.add(this);
				break;
			}
		}
	}
	
	public void paintSelf(java.awt.Graphics g){
		g.drawImage(img, x, y, null);
		go();
		hitBase();
		hitWall();
		hitTank();
	}
}
