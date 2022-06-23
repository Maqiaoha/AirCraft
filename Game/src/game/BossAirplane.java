package game;

import java.awt.image.BufferedImage;

/*
 * boss���࣬ʵ��boss�����ƶ��������ӵ�
 */

public class BossAirplane extends FlyingObject implements Enemy,Award {

    private int speed;

    BossAirplane(){
        //super(150,113,10);

        this.width = 130;
        this.height = 113;
        this.x = (int) (Math.random() * (Game.WIDTH - width));
        this.y = -height;
        this.life = 10;

        speed = 1;
    }

    //����boss���ķ���
    public int getScore() {
        return 33;
    }

    //boss�����ƶ�
    public void step() {
        y += speed;
    }

    /*
     * ��ȡboss����ͼƬ��״̬ΪLIFEʱ����boss��ͼƬ
     * ״̬ΪDEADʱ����4�ű���ͼƬ��ȫ�����غ�״̬��ΪREMOVE������null
     */
    int index = 1;
    public BufferedImage getImage() {
        if(isLife()){
            return Game.bossairplanes;
        }else if(isDead()){
            if(index==5){
                state = REMOVE;
                return null;
            }
            return Game.bossairplanes;
        }
        return null;
    }

    //���������ӵ�
    public Bullet[] shoot(){
        Bullet[] res = new Bullet[2];
        res[0] = new Bullet(x-this.width/3,y+this.height+10,"down");
        res[1] = new Bullet(x+this.width/3,y+this.height+10,"down");
        return res;
    }

    //�ж��Ƿ�Խ��
    public boolean outOf() {
        return y>=Game.HEIGHT;
    }

    //���ؽ�������
    @Override
    public int getType() {
        return (int)(Math.random()*2);
    }
}
