package game;

import java.awt.image.BufferedImage;

/*
 * boss机类，实现boss机的移动，生成子弹
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

    //返回boss机的分数
    public int getScore() {
        return 33;
    }

    //boss机的移动
    public void step() {
        y += speed;
    }

    /*
     * 获取boss机的图片，状态为LIFE时返回boss机图片
     * 状态为DEAD时返回4张爆破图片，全部返回后将状态改为REMOVE，返回null
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

    //生成两颗子弹
    public Bullet[] shoot(){
        Bullet[] res = new Bullet[2];
        res[0] = new Bullet(x-this.width/3,y+this.height+10,"down");
        res[1] = new Bullet(x+this.width/3,y+this.height+10,"down");
        return res;
    }

    //判断是否越界
    public boolean outOf() {
        return y>=Game.HEIGHT;
    }

    //返回奖励类型
    @Override
    public int getType() {
        return (int)(Math.random()*2);
    }
}
