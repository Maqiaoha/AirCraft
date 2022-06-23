package game;


import java.awt.image.BufferedImage;

/*
 * 
 * 
 * 小飞机（我方）英雄类
 * 
 * 
 * */
public class Hero extends FlyingObject{
	
	public int doubleFire;//火力值
	public int life;//生命
	public static BufferedImage[] heroImg ;//第一个图片
	public static BufferedImage[] heroImgBlowup ;//第二个图片
	public BufferedImage image;//
	public int index = 0;//图片下标

	public Hero(){

//		加载图片
		image = Game.heros[0];
//		宽带等于图片宽带
		width = Game.heros[0].getWidth();
		height = Game.heros[0].getHeight();
//		初始位置
		x = 150;
		y = 530;
		//super(width,height,x,y);
//		初始火力 生命
		doubleFire = 0;
		life = 3;
		
		heroImg = new BufferedImage[]{Game.heros[0],Game.heros[1]};
		
		heroImgBlowup = new BufferedImage[]{Game.heros[2],Game.heros[3],Game.heros[4]};
		
		index = 0;
		
	}


	public void step() {
//		图片切换
		//image = heroImg[index++/10%heroImg.length];
		
		
	}
	//英雄机活着时每次返回不同的图片实现英雄机的喷火
	//private int index = 0;
	public BufferedImage getImage() {
		if(isLife()){
			return Game.heros[index++%2];
		}
		return null;
	}
//	移动
	public void moveTo(int x,int y){
		this.x = x -this.width/2;//
		this.y = y -this.height/2;//
	}
	public void clearDoubleFire(){
		doubleFire = 0;
	}
//	发射子弹
public Bullet[] shoot(){
	int xStep = this.width/4;
	int yStep = -20;
	if(doubleFire>1000){
		Bullet[] b = new Bullet[5];
		for(int i=0;i<5;i++){
			b[i] = new Bullet(this.x+i*xStep,y + yStep,"up");
		}
		doubleFire -= 2;
		return b;
	}else if(doubleFire>0){
		Bullet[] b = new Bullet[3];
		for(int i=0;i<3;i++){
			b[i] = new Bullet(this.x+i*2*xStep,y + yStep,"up");
		}
		doubleFire -= 2;
		return b;
	}else{
		Bullet[] b = new Bullet[1];
		b[0] = new Bullet(this.x+2*xStep,this.y+yStep,"up");
		return b;
	}
}

	/*
	public Bullet[] shoot(){
		int xstep = -1+this.width/4;
		int ystep = 20;
		
		if(doubleFire>0){
//			子弹数组   
			Bullet[] bs = new Bullet[2];
			
			bs[0] = new Bullet(this.x+1*xstep-15,this.y-ystep);//英雄4/1的宽度，高度
			bs[1] = new Bullet(this.x+4*xstep-1,this.y-ystep);//英雄3/1的宽度，高度不变
			//doubleFire-=2;//1227
			
			return bs;
		}else{
			Bullet[] bs = new Bullet[1];
			bs[0] = new Bullet(this.x+2*xstep,this.y-ystep);//英雄4/2宽
			return bs;
		}
		
	}
*/
	public boolean outOf() {
		
		return false;//永不越界
	}
	

//	英雄机增命
	
	public void addLife(){
		life++;
		
	}
//	获取命数
	public int getLife(){
		return life;
		
	}
//	减命
	public void subLife(){
		life--;
		
	}
//	加火力值
	public void addDoubleFire(){
		doubleFire += 40;
		
	}
//	清空火力值
	public void subDoubleFire(){
		doubleFire  = 0;
		
	}
//	判断 飞行物 和 飞机是否发生碰撞
	public boolean hit(FlyingObject f){
		
		int x1 = f.x - this.width/2;
		int x2 = f.x + f.width + this.width/2;
		
		int y1 = f.y - this.height/2;
		int y2 = f.y + f.height - this.height/2;
		
		int x = this.x + this.width/2;
		int y = this.y + this.height/2;
			return x>x1 && x<x2 && y>=y1 && y<=y2;//碰到减分
		
	}
//	两张图片切换
	public void Blowup(){
		image = heroImgBlowup[index++/10%heroImgBlowup.length];
	}
	

}
