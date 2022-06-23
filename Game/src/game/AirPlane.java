package game;

import java.util.Random;
import java.awt.image.BufferedImage;
/*
 * 
 * 
 * 小飞机（敌）   继承飞行类（飞行物）          实现接口 奖励 获得奖励 
 * 
 * */
public class AirPlane extends FlyingObject implements Enemy{
	private ChecKpoint Checkpoints = new ChecKpoint();
	//	移动速度
	 private int speed;

//	飞机一旦创建就有自己的类型
	public AirPlane(){
//		获取图片
		System.out.println("速度："+Checkpoints.ckt);
		

		image = Game.airplane;
//		获取图片自己的大小
		width = image.getWidth();
		height = image.getHeight();
//		取随机数 随机小飞机飞行
		Random rand = new Random();
//		随机小飞机飞行位置   游戏宽高减去小飞机的宽高
		x = rand.nextInt(Game.WIDTH - this.width);
		y = -this.height;
		life = 1;
		speed = 2;

	}
	/*
	 * 获取小敌机的图片，状态为LIFE时返回小敌机图片
	 * 状态为DEAD时返回4张爆破图片，全部返回后将状态改为REMOVE，返回null
	 */
	int index = 1;
	public BufferedImage getImage() {
		if(isLife()){
			return Game.airplane;
		}else if(isDead()){
			if(index == 5){
				state = REMOVE;
				return null;
			}
			return Game.airplane;
		}
		return null;
	}

	//	发射子弹
	public Bullet[] shoot(){
		Bullet[] res = new Bullet[1];
		res[0] = new Bullet(this.x+width/2,this.y+height+10,"down");


		return res;
	}
//	重写得分方法 击败得分
	public int getScore(){
		return 3;
	}

	//	重写移动方法  从上往下   y增大
	public void step() {
		y+=speed;//向下
		
	
	}
	//出界方法
	public boolean outOf() {
		
		
		return this.y>=Game.HEIGHT;
		
	}
	

}

