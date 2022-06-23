package game;


import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.ImageIO;

import com.sun.imageio.plugins.common.ImageUtil;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/*
 * 加载图片的类，实现了将各个对象的图片加载到方法区，不用每次都要去读取
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

        //天空图片的加载
        bgImg = ImageUtil.getImg("C:\\Users\\mqh\\Desktop\\新建文件夹 (4)\\Game-master\\Game-master\\Game\\src\\background.png");

        //英雄机图片的加载
        heros = new BufferedImage[2];
        heros[0] = readImage("Game\\src\\hero0.png");
        heros[1] = readImage("Game\\src\\hero1.png");

        //子弹图片的加载
        bullets = new BufferedImage[2];
        bullets[0] = readImage("Game\\src\\bullet.png");
        bullets[1] = readImage("Game\\src\\bulletdown.png");

        //boss机图片的加载
        bossairplanes = new BufferedImage[5];
        bossairplanes[0] = readImage("Game\\src\\bossAirplane.png");

        //小敌机图片的加载
        airplanes = new BufferedImage[5];
        airplanes[0] = readImage("Game\\src\\airplane.png");
/*
        //大敌机的加载
        bigairplanes = new BufferedImage[5];
        bigairplanes[0] = readImage("bigairplane0.png");
*/
/*
        //侦察机图片的加载
        bees = new BufferedImage[5];
        bees[0] = readImage("Game\\src\\bee.png");

        //爆破图片的加载
        for(int i=1;i<5;i++){
            bees[i] = readImage("Game\\src\\bom"+i+".png");
            airplanes[i] = readImage("Game\\src\\bom"+i+".png");
            bigairplanes[i] = readImage("Game\\src\\bom"+i+".png");
            bossairplanes[i] = readImage("Game\\src\\bom"+i+".png");
        }

    }
/*
    //读取图片到内存
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