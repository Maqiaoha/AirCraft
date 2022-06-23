package game;
/*
 * ������ ��̬���� ��ʼ���� ����ؿ� ����Boss �ӱ������� 
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
	
	//	����IO�� ����ͼƬ
	public static BufferedImage logoImg;//logoͼƬ
	public static BufferedImage bgImg;//����ͼƬ
	public static BufferedImage logoImg1;//����ͼƬ
	
	public static BufferedImage play;//��ʼ
	public static BufferedImage pause;//��ͣ
	public static BufferedImage gameover;//��Ϸ����
	
	public static BufferedImage[] heros;//Ӣ�ۻ�
	/*
	public static BufferedImage hero1;//Ӣ�ۻ� 
	public static BufferedImage hero2;//Ӣ�ۻ�
	public static BufferedImage hero3;//Ӣ�ۻ�
	public static BufferedImage hero4;//Ӣ�ۻ� 
	*/
	public static BufferedImage airplane;//�л�
	public static BufferedImage bossairplanes;//
	public static BufferedImage bee;//�۷�
	public static BufferedImage prop_type;//�۷�
	public static BufferedImage[] bullet;//�ӵ�

	public static BufferedImage zty;//
	
//	��������Hero��������
	private Hero hero = new Hero();
	
	private ChecKpoint Checkpoints = new ChecKpoint();
	
	private FlyingObject[] enemies = {};//һ�ѵ��ˣ��л�+С�۷䣩
	private Bullet[] heroBullets = {};//Ӣ���ӵ�
	private Bullet[] enemiesBullets ={};//�����ӵ�


	public static final int START = 0;//����״̬
	public static final int RUNNING = 1;//����״̬
	public static final int PAUSE = 2;//��ͣ״̬
	public static final int GAME_OVER = 3;//��Ϸ����
	
	public int state = START;//Ĭ������״̬
	
	
	
	public String[] Checkpoint = new String[]{"����","��һ��","�ڶ���","������","���Ĺ�","�����"};
	
	

//	main����һ�� ����ҲҲ����һ��   �����ಿ��
	static{
//		���쳣
		try {
//			IO����ͼƬ
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
			System.out.println("ͼƬ����");
			e.printStackTrace();
			
		}
	}
//	������
	public static void main(String[] args) {
//		��������
		JFrame frame = new JFrame("Java�ɻ���ս");
//		����Ĭ�Ϲر�   �˳�Ӧ�ó���Ĭ�ϴ��ڹرղ�����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		���ô��ڵĴ�С ��λ PX 
		frame.setSize(Game.WIDTH, Game.HEIGHT);
		
		frame.setLocationRelativeTo(null);
		
		Game g = new Game();
//		������
		frame.add(g);
		
		frame.setVisible(true);
		
		g.action();
		
		
		
	}
	
	public void paint(Graphics g){
//		���ڼ��ر��� logo ͼƬ 
		g.drawImage(bgImg, 0, 0, null);
//		g.drawImage(logoImg, 0, 0, null);
		/*
		paintHero(g);
		*/
		paintFlying(g);

		paintBullsets(g);

		paintState(g);//����״̬ͼ
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
//		������������
		MouseAdapter l = new MouseAdapter() {

//			����ƶ�
			@Override
			public void mouseMoved(MouseEvent e) {
				if(state==RUNNING){//����״̬
					int x =e.getX();
					int y =e.getY();//��ȡ����x��y
					hero.moveTo(x,y);
				}
			}
		
			
			
			//	�����
			@Override
			public void mouseClicked(MouseEvent e) {
				
				switch (state) {
				case START://
					state = RUNNING;////����״̬
					break;
				case GAME_OVER://����
					Checkpoints.score = 0;//
					hero = new Hero();//���µ���
					enemies = new FlyingObject[0]; //��������������
					heroBullets = new Bullet[0];//�ӵ���������
					enemiesBullets = new Bullet[0];
					state = START;//����״̬
					break;
			
				}
				
			}


//   	����Ƴ�
			@Override
			public void mouseExited(MouseEvent e) {
				if(state==RUNNING){
					state=PAUSE;//��ͣ
				}
			}


//	   	�������
			
			public void mouseEntered(MouseEvent e) {
				if(state==PAUSE){
					state=RUNNING;//����
				}
			}

		
		};
//		����������¼�
		this.addMouseListener(l);
//		������껬���¼�
		this.addMouseMotionListener(l);
		
//		���ö�ʱ��
		Timer t = new Timer();
//		����  ��дTimerTask   �е� run ����
		t.schedule(new TimerTask() {
			
			public void run() {
				if(state==RUNNING){
//					checkImg();//���ͼƬ
				enterAction();//���л�
				stepAction();//��������
				enemiesShoot();//�л����ӵ�

					shootAction();//���ӵ�//





//					bangAction();//�ж��Ƿ���ел�
					//outOfAction();//Խ������� ɾ��

					checkHitAction();//	����ӵ�������Ƿ�����ײ
					hitAction();//Ӣ�ۻ�������ײ
					Checkpoints.Checkpoints();//�жϹؿ�
					checkGameOverAction();//�ж���Ϸ�Ƿ����
				}
				
//				����Hero��repanint����
				repaint();//��Ӣ�ۻ�
				
				
				
			}
		}, 10,10);
	}
//	����ӵ�������Ƿ�����ײ���ߵ��˵��ӵ���û�д�hero
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
				hero.subDoubleFire();//�������
				}
//			����
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
			enemies[index] = enemies[enemies.length-1];//�����Ƴ�
			enemies[enemies.length-1] = t;
//			����
			enemies = Arrays.copyOf(enemies, enemies.length-1);
		}
	}
//

	public void paintSL(Graphics g){
		g.setColor(Color.BLUE);
		g.setFont(new Font("����",3,16));
		g.drawString("������" + Checkpoints.score, 10, 20);
		g.drawString("������" + hero.getLife(), 10, 40);
		g.drawString("������" + hero.doubleFire, 10, 60);
		g.drawString("�ؿ���" + Checkpoint[Checkpoints.ckt], 10, 80);
	}
	public void paintBullsets(Graphics g){
		for (int i = 0; i < enemiesBullets.length; i++) {
			Bullet b = enemiesBullets[i];
			g.drawImage(Game.bullet[1], b.x, b.y, null);
		}
	}
	//�����˷���
	public void paintFlying(Graphics g){
		for (int i = 0; i < enemies.length; i++) {
			FlyingObject f = enemies[i];
			g.drawImage(f.image, f.x, f.y, null);
		}
	}
	/*
//	��Ӣ�۶��󷽷�
	public void paintHero(Graphics g){
		g.drawImage(hero.image, hero.x, hero.y, null);
	}
//	�����˷���
	public void paintFlying(Graphics g){
		for (int i = 0; i < enemies.length; i++) {
			FlyingObject f = enemies[i];
			g.drawImage(f.image, f.x, f.y, null);
		}
	}
//	���ӵ�����
	public void paintBullsets(Graphics g){
		for (int i = 0; i < heroBullets.length; i++) {
			Bullet b = heroBullets[i];
			g.drawImage(b.image, b.x, b.y, null);
		}
	}*/
//	��״̬
	public void paintState(Graphics g){
		
		switch (state) {//��ͬ״̬��ͬͼ
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
	//������ɴ�л���С�л��������Ķ���
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
	 *ÿ��һ��ʱ�����nextOne��������һ��������ӵ��������鵱��
	 *����������100�������һ��ʱ������boss����ӵ���������
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
// �����볡
	int flyIndex = 0;//�����볡����
	
	public void enterAction(){
		flyIndex++;
//		С��ȡ�����л�
		if (flyIndex%40==0) {
			
			FlyingObject obj = nextOne();//��ȡ���˶���
//			��ȡ�л�����
			flyings = Arrays.copyOf(flyings,flyings.length+1);
			flyings[flyings.length-1] = obj; 
		}
	}
//	���ɵл�����
	public FlyingObject nextOne(){
//		�������������
		Random rand = new Random();
//		�������0-19
		int type = rand.nextInt(50);
//		��������С��4 ���۷� ���� ���л�
		if(type<2){
			return new Bee();
		}else{	
			
			return new AirPlane(); 			
		}
		
	}

//	������л� �۷� �ӵ���
	public void stepAction(){
		hero.step();
		for(int i= 0;i<flyings.length;i++){
			flyings[i].step();//����

		}
		
		for(int i=0;i<bullets.length;i++){
			bullets[i].step();//�ӵ�
		}
*/
//ʹ��������

	public void stepAction(){
		//��ն�����
		//sky.step();
		//���˶�����
		for(int i=0;i<enemies.length;i++){
			enemies[i].step();
		}
		//Ӣ�ۻ��ӵ�������
		for(int i=0;i<heroBullets.length;i++){
			heroBullets[i].step();
		}
		//�����ӵ�������
		for(int i=0;i<enemiesBullets.length;i++){
			enemiesBullets[i].step();
		}
	}




	//�����ӵ�
	//ÿ��һ��ʱ��������˵���������ÿһ���������鷢���ӵ�����ӵ������ӵ�����
	private int enemiesShootIndex = 0;
	public void enemiesShoot(){
		enemiesShootIndex++;
		if(enemiesShootIndex%100==0){
			//������������
			for(int i=0;i<enemies.length;i++){
				FlyingObject f = enemies[i];
				//���˻������벻������ʱ�����ӵ�
				if(f.life>=1 && !(f instanceof Bee)){
					//���˵���shoot���������ӵ�����
					Bullet[] b = f.shoot();
					//�����ɵ��ӵ�������ӵ������ӵ�����
					enemiesBullets = Arrays.copyOf(enemiesBullets, enemiesBullets.length+b.length);
					System.arraycopy(b, 0, enemiesBullets, enemiesBullets.length-b.length,b.length);
				}
			}
		}
	}


	//	Ӣ���ӵ��볡

	private int shootIndex = 0;
	public void shootAction(){
		shootIndex++;
		if(shootIndex%40==0){
			//Ӣ�ۻ�����shoot���������ӵ�����
			Bullet[] b = hero.shoot();
			//���ӵ�������ӵ�Ӣ�ۻ��ӵ�������
			heroBullets = Arrays.copyOf(heroBullets, heroBullets.length+b.length);
			System.arraycopy(b, 0, heroBullets, heroBullets.length-b.length,b.length);
		}
	}


//	Ӣ�ۻ�������﷢����ײ
	public void hitAction(){
		for(int i =0;i<enemies.length;i++){
			FlyingObject f = enemies[i];
			if(hero.hit(f)){
				
				hero.subLife();//�����
				hero.Blowup();//���������
				hero.subDoubleFire();//�������
				
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
				//�����ײ���ӵ�mqhadd
				FlyingObject t = enemiesBullets[i];
				enemiesBullets[i] = enemiesBullets[enemiesBullets.length-1];//������ʱ�Ѹ��ӵ����ӵ������Ƴ�
				//enemiesBullets[enemiesBullets.length-1] = t;
				enemiesBullets = Arrays.copyOf(enemiesBullets, enemiesBullets.length - 1);

			}
		}
	}
//	�ж���Ϸ����
	public void checkGameOverAction(){
		if(hero.getLife()<=0){
			state = GAME_OVER;
			
		}
	}

	
//	���ͼƬ
//	public void checkImg(Graphics g){
//		if(state == START){
//			g.drawImage(logoImg, 0, 0, null);
//		}
//	}
	
}
