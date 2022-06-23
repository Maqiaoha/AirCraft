package game;


import java.awt.image.BufferedImage;

/*
 * 
 * 
 * С�ɻ����ҷ���Ӣ����
 * 
 * 
 * */
public class Hero extends FlyingObject{
	
	public int doubleFire;//����ֵ
	public int life;//����
	public static BufferedImage[] heroImg ;//��һ��ͼƬ
	public static BufferedImage[] heroImgBlowup ;//�ڶ���ͼƬ
	public BufferedImage image;//
	public int index = 0;//ͼƬ�±�

	public Hero(){

//		����ͼƬ
		image = Game.heros[0];
//		�������ͼƬ���
		width = Game.heros[0].getWidth();
		height = Game.heros[0].getHeight();
//		��ʼλ��
		x = 150;
		y = 530;
		//super(width,height,x,y);
//		��ʼ���� ����
		doubleFire = 0;
		life = 3;
		
		heroImg = new BufferedImage[]{Game.heros[0],Game.heros[1]};
		
		heroImgBlowup = new BufferedImage[]{Game.heros[2],Game.heros[3],Game.heros[4]};
		
		index = 0;
		
	}


	public void step() {
//		ͼƬ�л�
		//image = heroImg[index++/10%heroImg.length];
		
		
	}
	//Ӣ�ۻ�����ʱÿ�η��ز�ͬ��ͼƬʵ��Ӣ�ۻ������
	//private int index = 0;
	public BufferedImage getImage() {
		if(isLife()){
			return Game.heros[index++%2];
		}
		return null;
	}
//	�ƶ�
	public void moveTo(int x,int y){
		this.x = x -this.width/2;//
		this.y = y -this.height/2;//
	}
	public void clearDoubleFire(){
		doubleFire = 0;
	}
//	�����ӵ�
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
//			�ӵ�����   
			Bullet[] bs = new Bullet[2];
			
			bs[0] = new Bullet(this.x+1*xstep-15,this.y-ystep);//Ӣ��4/1�Ŀ�ȣ��߶�
			bs[1] = new Bullet(this.x+4*xstep-1,this.y-ystep);//Ӣ��3/1�Ŀ�ȣ��߶Ȳ���
			//doubleFire-=2;//1227
			
			return bs;
		}else{
			Bullet[] bs = new Bullet[1];
			bs[0] = new Bullet(this.x+2*xstep,this.y-ystep);//Ӣ��4/2��
			return bs;
		}
		
	}
*/
	public boolean outOf() {
		
		return false;//����Խ��
	}
	

//	Ӣ�ۻ�����
	
	public void addLife(){
		life++;
		
	}
//	��ȡ����
	public int getLife(){
		return life;
		
	}
//	����
	public void subLife(){
		life--;
		
	}
//	�ӻ���ֵ
	public void addDoubleFire(){
		doubleFire += 40;
		
	}
//	��ջ���ֵ
	public void subDoubleFire(){
		doubleFire  = 0;
		
	}
//	�ж� ������ �� �ɻ��Ƿ�����ײ
	public boolean hit(FlyingObject f){
		
		int x1 = f.x - this.width/2;
		int x2 = f.x + f.width + this.width/2;
		
		int y1 = f.y - this.height/2;
		int y2 = f.y + f.height - this.height/2;
		
		int x = this.x + this.width/2;
		int y = this.y + this.height/2;
			return x>x1 && x<x2 && y>=y1 && y<=y2;//��������
		
	}
//	����ͼƬ�л�
	public void Blowup(){
		image = heroImgBlowup[index++/10%heroImgBlowup.length];
	}
	

}
