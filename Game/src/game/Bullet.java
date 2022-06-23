package game;


/*
 * 
 * 子弹
 * 
 * */

import java.awt.image.BufferedImage;

public class Bullet extends FlyingObject{
//	速度
	private ChecKpoint Checkpoints = new ChecKpoint();

	//dir为不同的子弹图片标记，0为向上，1为向下
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
				System.out.println("子弹速度："+speed);
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
			System.out.println("子弹速度："+speed);
		}else {
			
				speed = 0;
			
		}
//		图片
		image = Game.bullet;
//		子弹大小 位置
		width = image.getWidth();
		height = image.getHeight();
		this.x = x;
		this.y = y;
	
	}*//*
	 * 返回子弹图片，Images.bullets[0]是向上的，Images.bullets[1]是向下的
	 * 当状态为DEAD时,改变状态为REMOVE
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
