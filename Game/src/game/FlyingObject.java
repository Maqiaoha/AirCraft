package game;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

/*
 * 
 * ������Ĺ�����  
 * ������ ��ÿ�������ǲ�ͬ�� Ӣ���� ���Ϸ�  �л� ���� �ӵ����£�
 * 		 �����Լ���ͼƬ ��� 
 * 
 * */
public abstract class FlyingObject {
	//	�����ɻ��Ĺ�������
//	ͼƬ   protected �ܱ�����
	protected BufferedImage image;
	//	�������
	protected int width;
	protected int height;
	protected int x;
	protected int y;
	protected int life;

	//private int direction;
	//״̬����
	public static final int LIFE = 0;
	public static final int DEAD = 1;
	public static final int REMOVE = 2;
	//��ǰ״̬
	protected int state = LIFE;

	//*******
//Ϊ�����ṩ�Ĺ��췽����
	/*
	FlyingObject(int width, int height, int life) {
		this.width = width;
		this.height = height;
		this.x = (int) (Math.random() * (Game.WIDTH - width));
		this.y = -height;
		this.life = life;
	}
*/
/*	//Ϊ��պ�Ӣ�ۻ��ӵ��ṩ�Ĺ��췽��
	FlyingObject(int width, int height, int x, int y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
*/
	//�����ӵ������ķ���
	public Bullet[] shoot() {
		return new Bullet[0];
	}
	//	�������Ƿ��ӵ�����
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

	//�ж���ײ�ķ���
	public boolean hit(FlyingObject other) {
		int x1 = other.x - this.width;
		int x2 = other.x + other.width;
		int y1 = other.y - this.height;
		int y2 = other.y + other.height;
		return x >= x1 && x <= x2 && y >= y1 && y <= y2;
	}

	//������һ
	public void subtractLife() {
		life--;
	}

	//�ж��Ƿ����
	public boolean isLife() {
		return state == LIFE;
	}

	//�ж��Ƿ����ˣ���������
	public boolean isDead() {
		return state == DEAD;
	}

	//�ж�״̬�Ƿ�Ϊ�Ƴ�
	public boolean isRemove() {
		return state == REMOVE;
	}

	//��״̬��ΪDEAD
	public void goDead() {
		state = DEAD;
	}

	//�ڻ����ϻ���ͼƬ��getImage()�Ǻ����Լ�д��һ����ȡͼƬ���ݵķ���
	public void paintObject(Graphics g) {
		g.drawImage(getImage(), x, y, null);
	}

	//�ƶ����󷽷�����Ϊÿ�������ƶ��ķ�����һ�����ֶ����ƶ�������дΪ���󷽷�
	public abstract void step();

	//��ȡͼƬ�ĳ��󷽷���Ϊʲô����Ϊ���󷽷�����
	public abstract BufferedImage getImage();

//*******

	/*//	�ƶ�����
		public abstract void step();
		*/
//	���������Ƿ����
	public abstract boolean outOf();
}





