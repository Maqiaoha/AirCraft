package game;
/*
 * 可升级 动态背景 开始界面 加入关卡 加入Boss 加背景音乐 
 * */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3233689943517353386L;
	
	

	public static final int WIDTH = 400;
	
	public static final int HEIGHT = 700;
	
	//	创建IO流 加入图片
	public static BufferedImage logoImg;//logo图片
	public static BufferedImage bgImg;//背景图片
	public static BufferedImage logoImg1;//背景图片
	
	public static BufferedImage play;//开始
	public static BufferedImage pause;//暂停
	public static BufferedImage gameover;//游戏结束
	
	public static BufferedImage[] heros;//英雄机
	/*
	public static BufferedImage hero1;//英雄机 
	public static BufferedImage hero2;//英雄机
	public static BufferedImage hero3;//英雄机
	public static BufferedImage hero4;//英雄机 
	*/
	public static BufferedImage airplane;//敌机
	public static BufferedImage bossairplanes;//
	public static BufferedImage bee;//蜜蜂
	public static BufferedImage prop_type;//蜜蜂
	public static BufferedImage[] bullet;//子弹

	public static BufferedImage zty;//
	
//	创建调用Hero方法函数
	private Hero hero = new Hero();
	
	private ChecKpoint Checkpoints = new ChecKpoint();
	
	private FlyingObject[] enemies = {};//一堆敌人（敌机+小蜜蜂）
	private Bullet[] heroBullets = {};//英雄子弹
	private Bullet[] enemiesBullets ={};//敌人子弹


	public static final int START = 0;//启动状态
	public static final int RUNNING = 1;//运行状态
	public static final int PAUSE = 2;//暂停状态
	public static final int GAME_OVER = 3;//游戏结束
	
	public int state = START;//默认启动状态
	
	
	
	public String[] Checkpoint = new String[]{"结束","第一关","第二关","第三关","第四关","第五关"};
	
	

//	main运行一次 方法也也允许一次   匿名类部类
	static{
//		抛异常
		try {
//			IO读入图片
			logoImg = ImageIO.read(Game.class.getClassLoader().getResource("logo.jpg"));
			//logoImg1 = ImageIO.read(Game.class.getClassLoader().getResource("Logo.png"));
			bgImg = ImageIO.read(Game.class.getClassLoader().getResource("background.png"));
			
			play = ImageIO.read(Game.class.getClassLoader().getResource("Play.png"));
			pause = ImageIO.read(Game.class.getClassLoader().getResource("pause.png"));
			gameover = ImageIO.read(Game.class.getClassLoader().getResource("gameover.png"));
			heros = new BufferedImage[5];
			heros[0] = ImageIO.read(Hero.class.getClassLoader().getResource("hero0.png"));
			heros[1] = ImageIO.read(Hero.class.getClassLoader().getResource("hero1.png"));
			heros[2] = ImageIO.read(Hero.class.getClassLoader().getResource("hero_blowup2.png"));
			heros[3] = ImageIO.read(Hero.class.getClassLoader().getResource("hero_blowup3.png"));
			heros[4] = ImageIO.read(Hero.class.getClassLoader().getResource("hero_blowup4.png"));
			
			airplane = ImageIO.read(Hero.class.getClassLoader().getResource("airplane.png"));
			bossairplanes = ImageIO.read(Hero.class.getClassLoader().getResource("bossAirplane.png"));
			bee = ImageIO.read(Hero.class.getClassLoader().getResource("bee.png"));
			prop_type = ImageIO.read(Hero.class.getClassLoader().getResource("prop_type.png"));
			bullet = new BufferedImage[2];
			bullet[0] = ImageIO.read(Hero.class.getClassLoader().getResource("bullet.png"));
			bullet[1] = ImageIO.read(Hero.class.getClassLoader().getResource("bulletdown.png"));
			
			//zty = ImageIO.read(Hero.class.getClassLoader().getResource("ZTY.png"));
			
		} catch (IOException e) {
			System.out.println("图片错误");
			e.printStackTrace();
			
		}
	}
//	主方法
	public static void main(String[] args) {
//		创建窗口
		JFrame frame = new JFrame("Java飞机大战");
//		窗口默认关闭   退出应用程序默认窗口关闭操作。
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		设置窗口的大小 单位 PX 
		frame.setSize(Game.WIDTH, Game.HEIGHT);
		
		frame.setLocationRelativeTo(null);
		
		Game g = new Game();
//		加载类
		frame.add(g);
		
		frame.setVisible(true);
		
		g.action();
		
		
		
	}
	
	public void paint(Graphics g){
//		窗口加载背景 logo 图片 
		g.drawImage(bgImg, 0, 0, null);
//		g.drawImage(logoImg, 0, 0, null);
		/*
		paintHero(g);
		*/
		paintFlying(g);

		paintBullsets(g);

		paintState(g);//加载状态图
		paintSL(g);
		hero.paintObject(g);
		for(int i=0;i<enemies.length;i++){
			enemies[i].paintObject(g);
		}
		for(int i=0;i<heroBullets.length;i++){
			heroBullets[i].paintObject(g);
		}

		for(int i=0;i<enemiesBullets.length;i++){
            //
			//enemiesBullets[i].y = 200;
			//enemiesBullets[i].
			enemiesBullets[i].paintObject(g);

		}

		
	}
	public void action(){
//		创建鼠标监听器
		MouseAdapter l = new MouseAdapter() {

//			鼠标移动
			@Override
			public void mouseMoved(MouseEvent e) {
				if(state==RUNNING){//运行状态
					int x =e.getX();
					int y =e.getY();//获取鼠标的x和y
					hero.moveTo(x,y);
				}
			}
		
			
			
			//	鼠标点击
			@Override
			public void mouseClicked(MouseEvent e) {
				
				switch (state) {
				case START://
					state = RUNNING;////运行状态
					break;
				case GAME_OVER://结束
					Checkpoints.score = 0;//
					hero = new Hero();//重新调用
					enemies = new FlyingObject[0]; //飞行类数组清零
					heroBullets = new Bullet[0];//子弹数组清零
					enemiesBullets = new Bullet[0];
					state = START;//启动状态
					break;
			
				}
				
			}


//   	鼠标移除
			@Override
			public void mouseExited(MouseEvent e) {
				if(state==RUNNING){
					state=PAUSE;//暂停
				}
			}


//	   	鼠标移入
			
			public void mouseEntered(MouseEvent e) {
				if(state==PAUSE){
					state=RUNNING;//运行
				}
			}

		
		};
//		添加鼠标监听事件
		this.addMouseListener(l);
//		处理鼠标滑动事件
		this.addMouseMotionListener(l);
		
//		启用定时器
		Timer t = new Timer();
//		调用  重写TimerTask   中的 run 方法
		t.schedule(new TimerTask() {
			
			public void run() {
				if(state==RUNNING){
//					checkImg();//清除图片
				enterAction();//画敌机
				stepAction();//画飞行物
				enemiesShoot();//敌机打子弹

					shootAction();//画子弹//





//					bangAction();//判断是否击中敌机
					//outOfAction();//越界飞行物 删除

					checkHitAction();//	检测子弹与敌人是否发生碰撞
					hitAction();//英雄机发生碰撞
					Checkpoints.Checkpoints();//判断关卡
					checkGameOverAction();//判断游戏是否结束
				}
				
//				调用Hero的repanint方法
				repaint();//画英雄机
				
				
				
			}
		}, 10,10);
	}
//	检测子弹与敌人是否发生碰撞或者敌人的子弹有没有打到hero
	int score = 0;

	public void checkHitAction(){
		for(int i = 0; i<heroBullets.length;i++){
			Bullet b = heroBullets[i];
			check(b);
		}

	}
	public void check1(Bullet b){
		for(int i =0;i<enemiesBullets.length;i++) {
			if (hero.hit(b)) {
				hero.subLife();//
				hero.subDoubleFire();//清除火力
				}
//			缩容
			enemiesBullets = Arrays.copyOf(enemiesBullets, enemiesBullets.length - 1);
		}
	}

	public void check(Bullet b){
		int index = -1;
		for(int i = 0;i<enemies.length;i++){
			FlyingObject f = enemies[i];
			if(f.hit(b)){
				index = i ;//
				f.life -= 1;
				break;
			}
		}
		if(index != -1){
			FlyingObject f = enemies[index];
			if(f instanceof Enemy){
				Enemy e =  (Enemy)f;
				Checkpoints.score += e.getScore();

			}
			if (f instanceof Award){
				Award a = (Award)f;
				int type = a.getType();
				switch(type){
				case Award.DOUBLE_FIRE:
					hero.addDoubleFire();
					break;
				case Award.LIFE:
					hero.addLife();


				}

			}
			FlyingObject t = enemies[index];
			enemies[index] = enemies[enemies.length-1];//数组移除
			enemies[enemies.length-1] = t;
//			缩容
			enemies = Arrays.copyOf(enemies, enemies.length-1);
		}
	}
//

	public void paintSL(Graphics g){
		g.setColor(Color.BLUE);
		g.setFont(new Font("宋体",3,16));
		g.drawString("分数：" + Checkpoints.score, 10, 20);
		g.drawString("命数：" + hero.getLife(), 10, 40);
		g.drawString("火力：" + hero.doubleFire, 10, 60);
		g.drawString("关卡：" + Checkpoint[Checkpoints.ckt], 10, 80);
	}
	public void paintBullsets(Graphics g){
		for (int i = 0; i < enemiesBullets.length; i++) {
			Bullet b = enemiesBullets[i];
			g.drawImage(Game.bullet[1], b.x, b.y, null);
		}
	}
	//画敌人方法
	public void paintFlying(Graphics g){
		for (int i = 0; i < enemies.length; i++) {
			FlyingObject f = enemies[i];
			g.drawImage(f.image, f.x, f.y, null);
		}
	}
	/*
//	画英雄对象方法
	public void paintHero(Graphics g){
		g.drawImage(hero.image, hero.x, hero.y, null);
	}
//	画敌人方法
	public void paintFlying(Graphics g){
		for (int i = 0; i < enemies.length; i++) {
			FlyingObject f = enemies[i];
			g.drawImage(f.image, f.x, f.y, null);
		}
	}
//	画子弹方法
	public void paintBullsets(Graphics g){
		for (int i = 0; i < heroBullets.length; i++) {
			Bullet b = heroBullets[i];
			g.drawImage(b.image, b.x, b.y, null);
		}
	}*/
//	画状态
	public void paintState(Graphics g){
		
		switch (state) {//不同状态不同图
		case START:
			
			
			g.drawImage(logoImg, 0, 0, null);
			g.drawImage(logoImg1,45, Game.HEIGHT/3,300,100, null);
			
			g.drawImage(play,50,Game.HEIGHT/2, 300,100,null);
//			g.drawImage(zty, 0, 0, 40,50,null);
			
			break;
		case PAUSE:
			g.drawImage(pause, 0, 0, null);
			break;
		case GAME_OVER:
			g.drawImage(gameover, 0, 0, null);
			break;

		
		}
	}
	//
	//随机生成大敌机、小敌机、侦察机的对象
	public FlyingObject nextOne(){
		int n = (int)(Math.random()*100);
		if(n>95){
			return new Bee();
		}
		if(n<10){
			return new BossAirplane();
		}
		else{
			return new AirPlane();
		}
	}

	/*
	 *每隔一段时间调用nextOne方法生成一个敌人添加到敌人数组当中
	 *当分数大于100后隔更长一段时间生成boss机添加到敌人数组
	 */
	private int enemiesIndex = 0;
	public void enterAction(){
		enemiesIndex++;
		if(enemiesIndex%30==0){
			enemies = Arrays.copyOf(enemies, enemies.length+1);
			enemies[enemies.length-1] = nextOne();
		}
		if(enemiesIndex%1000==0 && score>100){
			enemies = Arrays.copyOf(enemies, enemies.length+1);
			enemies[enemies.length-1] = new BossAirplane();
		}
	}
//
/*
// 敌人入场
	int flyIndex = 0;//敌人入场次数
	
	public void enterAction(){
		flyIndex++;
//		小于取余加入敌机
		if (flyIndex%40==0) {
			
			FlyingObject obj = nextOne();//获取敌人对象
//			获取敌机数组
			flyings = Arrays.copyOf(flyings,flyings.length+1);
			flyings[flyings.length-1] = obj; 
		}
	}
//	生成敌机对象
	public FlyingObject nextOne(){
//		调用随机数方法
		Random rand = new Random();
//		随机生成0-19
		int type = rand.nextInt(50);
//		如果随机数小于4 出蜜蜂 否则 出敌机
		if(type<2){
			return new Bee();
		}else{	
			
			return new AirPlane(); 			
		}
		
	}

//	飞行物（敌机 蜜蜂 子弹）
	public void stepAction(){
		hero.step();
		for(int i= 0;i<flyings.length;i++){
			flyings[i].step();//敌人

		}
		
		for(int i=0;i<bullets.length;i++){
			bullets[i].step();//子弹
		}
*/
//使对象动起来

	public void stepAction(){
		//天空动起来
		//sky.step();
		//敌人动起来
		for(int i=0;i<enemies.length;i++){
			enemies[i].step();
		}
		//英雄机子弹动起来
		for(int i=0;i<heroBullets.length;i++){
			heroBullets[i].step();
		}
		//敌人子弹动起来
		for(int i=0;i<enemiesBullets.length;i++){
			enemiesBullets[i].step();
		}
	}




	//敌人子弹
	//每隔一段时间遍历敌人敌人数组让每一个敌人数组发射子弹，添加到敌人子弹数组
	private int enemiesShootIndex = 0;
	public void enemiesShoot(){
		enemiesShootIndex++;
		if(enemiesShootIndex%100==0){
			//遍历敌人数组
			for(int i=0;i<enemies.length;i++){
				FlyingObject f = enemies[i];
				//敌人还活着与不是侦察机时发射子弹
				if(f.life>=1 && !(f instanceof Bee)){
					//敌人调用shoot方法生成子弹数组
					Bullet[] b = f.shoot();
					//将生成的子弹数组添加到敌人子弹数组
					enemiesBullets = Arrays.copyOf(enemiesBullets, enemiesBullets.length+b.length);
					System.arraycopy(b, 0, enemiesBullets, enemiesBullets.length-b.length,b.length);
				}
			}
		}
	}


	//	英雄子弹入场

	private int shootIndex = 0;
	public void shootAction(){
		shootIndex++;
		if(shootIndex%40==0){
			//英雄机调用shoot方法生成子弹数组
			Bullet[] b = hero.shoot();
			//将子弹数组添加到英雄机子弹数组中
			heroBullets = Arrays.copyOf(heroBullets, heroBullets.length+b.length);
			System.arraycopy(b, 0, heroBullets, heroBullets.length-b.length,b.length);
		}
	}


//	英雄机与飞行物发生碰撞
	public void hitAction(){
		for(int i =0;i<enemies.length;i++){
			FlyingObject f = enemies[i];
			if(hero.hit(f)){
				
				hero.subLife();//清除命
				hero.Blowup();//清除命动画
				hero.subDoubleFire();//清除火力
				
				FlyingObject t = enemies[i];
				enemies[i] = enemies[enemies.length-1];
				enemies[enemies.length-1] = t;
				enemies = Arrays.copyOf(enemies, enemies.length - 1);
				
			}
		}
		for(int i = 0;i<enemiesBullets.length;i++){
			FlyingObject f = enemiesBullets[i];
			if(hero.hit(f)){
				hero.subLife();

				hero.Blowup();
				hero.subDoubleFire();
				//清除碰撞的子弹mqhadd
				FlyingObject t = enemiesBullets[i];
				enemiesBullets[i] = enemiesBullets[enemiesBullets.length-1];//当击中时把该子弹从子弹数组移除
				//enemiesBullets[enemiesBullets.length-1] = t;
				enemiesBullets = Arrays.copyOf(enemiesBullets, enemiesBullets.length - 1);

			}
		}
	}
//	判断游戏结束
	public void checkGameOverAction(){
		if(hero.getLife()<=0){
			state = GAME_OVER;
			
		}
	}

	
//	清除图片
//	public void checkImg(Graphics g){
//		if(state == START){
//			g.drawImage(logoImg, 0, 0, null);
//		}
//	}
	
}
