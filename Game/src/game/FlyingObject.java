package game;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

/*
 * 
 * 飞行物的共性累  
 * 抽象类 （每个事物是不同的 英雄类 向上飞  敌机 向下 子弹上下）
 * 		 都有自己的图片 宽高 
 * 
 * */
public abstract class FlyingObject {
	//	描述飞机的共性内容
//	图片   protected 受保护的
	protected BufferedImage image;
	//	宽高坐标
	protected int width;
	protected int height;
	protected int x;
	protected int y;
	protected int life;

	//private int direction;
	//状态常量
	public static final int LIFE = 0;
	public static final int DEAD = 1;
	public static final int REMOVE = 2;
	//当前状态
	protected int state = LIFE;

	//*******
//为敌人提供的构造方法，
	/*
	FlyingObject(int width, int height, int life) {
		this.width = width;
		this.height = height;
		this.x = (int) (Math.random() * (Game.WIDTH - width));
		this.y = -height;
		this.life = life;
	}
*/
/*	//为天空和英雄机子弹提供的构造方法
	FlyingObject(int width, int height, int x, int y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
*/
	//生成子弹组数的方法
	public Bullet[] shoot() {
		return new Bullet[0];
	}
	//	检测敌人是否被子弹击中
/*
	public boolean hit(FlyingObject other){
		int x1 = this.x;
		int x2 = this.x+this.width;

		int y1 = this.y;
		int y2 = this.y+this.height;

		int x = other.x;
		int y = other.y;
		return x>=x1 && x<=x2 && y>=y1 && y<=y2;
	}*/

	//判断碰撞的方法
	public boolean hit(FlyingObject other) {
		int x1 = other.x - this.width;
		int x2 = other.x + other.width;
		int y1 = other.y - this.height;
		int y2 = other.y + other.height;
		return x >= x1 && x <= x2 && y >= y1 && y <= y2;
	}

	//生命减一
	public void subtractLife() {
		life--;
	}

	//判断是否活着
	public boolean isLife() {
		return state == LIFE;
	}

	//判断是否死了，后面有用
	public boolean isDead() {
		return state == DEAD;
	}

	//判断状态是否为移除
	public boolean isRemove() {
		return state == REMOVE;
	}

	//将状态改为DEAD
	public void goDead() {
		state = DEAD;
	}

	//在画板上画出图片，getImage()是后面自己写的一个获取图片数据的方法
	public void paintObject(Graphics g) {
		g.drawImage(getImage(), x, y, null);
	}

	//移动抽象方法，因为每个对象移动的方法不一样，又都有移动，所以写为抽象方法
	public abstract void step();

	//获取图片的抽象方法，为什么设置为抽象方法如上
	public abstract BufferedImage getImage();

//*******

	/*//	移动方法
		public abstract void step();
		*/
//	检测飞行类是否出界
	public abstract boolean outOf();
}





