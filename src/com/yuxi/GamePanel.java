package com.yuxi;

import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class GamePanel extends JFrame {
	
	//定义双缓存图片
	private java.awt.Image offScreenImage = null;
	//游戏状态 0 游戏未开始 1 单人模式 2 双人模式 3.游戏暂停 4.游戏失败 5.游戏胜利
	public int state = 0;
	//临时变量
	private int a = 1;
	//重绘次数
	public int count = 0;
	//窗口长宽
	private int width = 800;
	private int height = 610;
	//敌人数量
	private int enemycount = 0;
	//指针初始纵坐标
	private int y = 150;
	//游戏是否开始
	private boolean start = false;
	//物理集合
	public List<Bullet> bulletsList = new java.util.ArrayList<>();
	public List<Bot> botList = new java.util.ArrayList<>();
	public List<Tank> tankList = new java.util.ArrayList<>();
	public List<Wall> wallList = new java.util.ArrayList<>();
	public List<Bullet> removeList = new java.util.ArrayList<>();
	public List<Base> baseList = new java.util.ArrayList<>();
	public List<BlastObj> blastList = new java.util.ArrayList<>();
	//背景图片
	public Image background = java.awt.Toolkit.getDefaultToolkit().getImage("images/background.jpg");
	//指针图片
	private Image select = java.awt.Toolkit.getDefaultToolkit().getImage("images/tankR.gif");
	//基地
	private Base base = new Base("images/star.gif", 377, 563, this);
	//玩家
	private PlayerOne playerOne = new PlayerOne("images/tankU.gif", 125, 510,
			"images/tankU.gif","images/tankD.gif",
			"images/tankL.gif", "images/tankR.gif",this);
	private PlayerTwo playerTwo = new PlayerTwo("images/player2/p2tankU.gif", 625, 510,
            "images/player2/p2tankU.gif","images/player2/p2tankD.gif",
            "images/player2/p2tankL.gif","images/player2/p2tankR.gif", this);
	
	//窗口的启动方法
	public void launch(){
		//标题
		setTitle("我的坦克大战");
		//窗口初始大小
		setSize(width, height);
		//用户不能调整大小
		setResizable(false);
		//使窗口可见
		setVisible(true);
		//获取屏幕分辨率，使窗口生成时居中
		setLocationRelativeTo(null);
		//添加关闭事件
		setDefaultCloseOperation(3);
		//添加键盘监视器
		this.addKeyListener(new GamePanel.KeyMonitor());
		//添加围场
		for (int i = 0; i < 14; i++) {
			wallList.add(new Wall("images/walls.gif", i*60, 170, this));
		}
		wallList.add(new Wall("images/walls.gif", 305, 560, this));
		wallList.add(new Wall("images/walls.gif", 305, 500, this));
		wallList.add(new Wall("images/walls.gif", 365, 500, this));
		wallList.add(new Wall("images/walls.gif", 425, 500, this));
		wallList.add(new Wall("images/walls.gif", 425, 560, this));
		//添加基地
		baseList.add(base);
		
		//重绘
		while (true) {
			if (botList.size() == 0 && enemycount == 10) {
				state = 5;
			}
			if (tankList.size() == 0 && (state == 1 || state == 2)) {
				state = 4;
			}
			if (state == 1 || state == 2) {
				if (count % 100 == 1 && enemycount < 10) {
					Random r = new Random();
					int rnum = r.nextInt(800);
					botList.add(new Bot("images/enemy/enemy1U.gif", rnum, 110,
	                        "images/enemy/enemy1U.gif","images/enemy/enemy1D.gif",
	                        "images/enemy/enemy1L.gif","images/enemy/enemy1R.gif", this));
					enemycount ++;
					//System.out.println("bot: " + botList.size())
				}
			}
			repaint();
			try {
				//线程休眠 1秒等于1000毫秒
				Thread.sleep(25);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void paint(java.awt.Graphics g){
		//创建和容器一样大小的Image图片
		if (offScreenImage == null) {
			offScreenImage = this.createImage(width, height);
		}
		//获得该图片的画笔
		java.awt.Graphics gImage = offScreenImage.getGraphics();
		//设置画笔颜色
		gImage.setColor(java.awt.Color.gray);
		//绘制实心矩形
		gImage.fillRect(0, 0, width, height);
		//改变画笔颜色
		gImage.setColor(java.awt.Color.yellow);
		//改变文字大小和样式
		gImage.setFont(new java.awt.Font("宋",java.awt.Font.BOLD,50));
		//state = 0，游戏未开始
		if (state == 0) {
			//添加文字
			gImage.drawString("选择游戏模式", 220, 100);
			gImage.drawString("单人模式", 220, 200);
			gImage.drawString("双人模式", 220, 300);
			gImage.drawString("按1，2选择模式，按回车开始游戏", 0, 400);
			gImage.drawImage(select, 160, y, null);
		} 
		else if(state == 1||state == 2){
			//添加文字
			gImage.setColor(java.awt.Color.red);
			gImage.setFont(new java.awt.Font("仿宋", java.awt.Font.BOLD, 30));
			gImage.drawString("WASD控制移动", 0, 510);
			gImage.drawString("空格射击" , 0, 550);
			if (state == 2) {
				gImage.drawString("方向键控制移动", 575, 510);
				gImage.drawString("K射击", 575, 550);
			}
			//paint重绘游戏元素
			for(Tank player: tankList){
				player.paintSelf(gImage);				
			}
			for (Bullet bullet : bulletsList) {
				bullet.paintSelf(gImage);
			}
			bulletsList.removeAll(removeList);
			for(Bot bot : botList){
				bot.paintSelf(gImage);
			}
			for (Wall wall : wallList) {
				wall.paintSelf(gImage);
			}
			for (Base base : baseList) {
				base.paintSelf(gImage);
			}
			for (BlastObj blast : blastList) {
				blast.paintSelf(gImage);
			}
			//重绘次数+1
			count ++;
		}
		else if (state == 3) {
			gImage.drawString("游戏暂停", 220, 200);
		}
		else if (state == 4) {
			gImage.drawString("游戏失败", 220, 200);
		}
		else if (state == 5) {
			gImage.drawString("游戏胜利", 220, 200);
		}
		/*将缓冲区给绘制好的图形整个绘制到容器的画布中*/
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	// 键盘监视器
	public class KeyMonitor extends java.awt.event.KeyAdapter{

		//按下键盘
		@Override
		public void keyPressed(java.awt.event.KeyEvent e){
			//返回键值
			int key = e.getKeyCode();
			switch (key) {
			case java.awt.event.KeyEvent.VK_1:
					y = 150;
					a = 1;
				break;
			case java.awt.event.KeyEvent.VK_2:
					y = 250;
					a = 2;
				break;
			case java.awt.event.KeyEvent.VK_ENTER:
				state = a;
				//添加玩家
				if (state == 1 && !start) {
					tankList.add(playerOne);
				}else {
					tankList.add(playerOne);
					tankList.add(playerTwo);
				}
				start = true;
				break;
			case java.awt.event.KeyEvent.VK_P:
				if (state != 3) {
					a = state;
					state = 3;
				} else {
					state = a;
					if (a == 0) {
						a = 1;
					}
				}
//				System.out.println(toString());
				break;
			default:
				playerOne.keyPressed(e);
				playerTwo.keyPressed(e);
				break;
			}
		}	
		@Override
		public void keyReleased(java.awt.event.KeyEvent e){
			playerOne.keyReleased(e);
			playerTwo.keyReleased(e);
		}
	}
		
	//main
	public static void main(String[] args) {
		GamePanel gamePanel = new GamePanel();
		gamePanel.launch();
	}
}
