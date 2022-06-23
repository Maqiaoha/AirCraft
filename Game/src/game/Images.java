package game;


import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.ImageIO;

import com.sun.imageio.plugins.common.ImageUtil;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/*
 * ����ͼƬ���࣬ʵ���˽����������ͼƬ���ص�������������ÿ�ζ�Ҫȥ��ȡ
 */
/*
public class Images {
    public static BufferedImage bgImg;
    public static BufferedImage[] bullets;
    public static BufferedImage[] bossairplanes;
    public static BufferedImage[] heros;
    public static BufferedImage[] airplanes;
    public static BufferedImage[] bigairplanes;
    public static BufferedImage[] bees;

    static{

        //���ͼƬ�ļ���
        bgImg = ImageUtil.getImg("C:\\Users\\mqh\\Desktop\\�½��ļ��� (4)\\Game-master\\Game-master\\Game\\src\\background.png");

        //Ӣ�ۻ�ͼƬ�ļ���
        heros = new BufferedImage[2];
        heros[0] = readImage("Game\\src\\hero0.png");
        heros[1] = readImage("Game\\src\\hero1.png");

        //�ӵ�ͼƬ�ļ���
        bullets = new BufferedImage[2];
        bullets[0] = readImage("Game\\src\\bullet.png");
        bullets[1] = readImage("Game\\src\\bulletdown.png");

        //boss��ͼƬ�ļ���
        bossairplanes = new BufferedImage[5];
        bossairplanes[0] = readImage("Game\\src\\bossAirplane.png");

        //С�л�ͼƬ�ļ���
        airplanes = new BufferedImage[5];
        airplanes[0] = readImage("Game\\src\\airplane.png");
/*
        //��л��ļ���
        bigairplanes = new BufferedImage[5];
        bigairplanes[0] = readImage("bigairplane0.png");
*/
/*
        //����ͼƬ�ļ���
        bees = new BufferedImage[5];
        bees[0] = readImage("Game\\src\\bee.png");

        //����ͼƬ�ļ���
        for(int i=1;i<5;i++){
            bees[i] = readImage("Game\\src\\bom"+i+".png");
            airplanes[i] = readImage("Game\\src\\bom"+i+".png");
            bigairplanes[i] = readImage("Game\\src\\bom"+i+".png");
            bossairplanes[i] = readImage("Game\\src\\bom"+i+".png");
        }

    }
/*
    //��ȡͼƬ���ڴ�
    public static BufferedImage readImage(String fileName){
        try{
            BufferedImage img = ImageIO.read(FlyingObject.class.getResource(fileName));
            return img;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }*/
/*
}
*/