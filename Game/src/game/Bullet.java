package game;


/*
 * 
 * �ӵ�
 * 
 * */

import java.awt.image.BufferedImage;

public class Bullet extends FlyingObject{
//	�ٶ�
	private ChecKpoint Checkpoints = new ChecKpoint();

	//dirΪ��ͬ���ӵ�ͼƬ��ǣ�0Ϊ���ϣ�1Ϊ����
	private int speed;
	private int dir;

	Bullet(int x,int y,String direction){
		//super(8,20,x,y);
		this.width = 8;
		this.height = 20;
		this.x = x;
		this.y = y;
		if(direction.equals("up")){
			speed = -5;
			dir = 0;
			if(Checkpoints.ckt>=1){
				speed = Checkpoints.ckt*8;
				System.out.println("�ӵ��ٶȣ�"+speed);
			}else {

				speed = 0;

			}
		}else if(direction.equals("down")){
			speed = 3;
			dir = 1;
		}

	}
/*
	public Bullet(int x,int y){
		if(Checkpoints.ckt>=1){
			speed = Checkpoints.ckt*8;
			System.out.println("�ӵ��ٶȣ�"+speed);
		}else {
			
				speed = 0;
			
		}
//		ͼƬ
		image = Game.bullet;
//		�ӵ���С λ��
		width = image.getWidth();
		height = image.getHeight();
		this.x = x;
		this.y = y;
	
	}*//*
	 * �����ӵ�ͼƬ��Images.bullets[0]�����ϵģ�Images.bullets[1]�����µ�
	 * ��״̬ΪDEADʱ,�ı�״̬ΪREMOVE
	 */
	public BufferedImage getImage() {
		if(isLife()){
			return Game.bullet[dir];
		}else if(isDead()){
			state = REMOVE;
			return null;
		}
		return null;
	}

	public void step(){
		// ny changed
		if (dir == 0) {
			y -= speed;
		}else {y += speed ;}
	}
	
	public boolean outOf() {
		
		return this.y <= -this.height;
	}
	
	
}
