package main;

import com.sun.org.apache.bcel.internal.generic.FieldGen;
import com.sun.org.apache.bcel.internal.generic.NEW;
import util.GameUtil;

import java.awt.*;


public class GamePeople {
    public int life = 3;
    //矩形
    public Rectangle rect;
    //图片
    Image pup;
    Image pdown;
    Image pleft;
    Image pright;
    private static final String PEOPLLE_UP="img/up.png";
    private static final String PEOPLLE_DOWN="img/down.png";
    private static final String PEOPLLE_LEFT="img/left.png";
    private static final String PEOPLLE_RIGHT="img/right.png";

    public GamePeople(){
        pup = GameUtil.LoadBufferedImg(PEOPLLE_UP);
        pdown = GameUtil.LoadBufferedImg( PEOPLLE_DOWN);
        pleft = GameUtil.LoadBufferedImg(PEOPLLE_LEFT);
        pright = GameUtil.LoadBufferedImg(PEOPLLE_RIGHT);
        rect = new Rectangle();
    }

    //位置
    int x,y,x1,y1;
    //方向
    public boolean up = false,down = false,left = false,right = false;
    int b = 0;
    boolean aa =true;
    public void draw(Graphics g,int x,int y){
        if(b==0){
        g.drawImage(pdown,x,y,null);
        }else{
            direction(g);
            rect(g);
        }
        if(aa){
            this.x = x;
            this.y = y;
            this.x1 = x;
            this.y1 = y;

            aa=false;
        }
        g.setColor(Color.RED);
        String str = "生命值:"+life;
        g.setFont(new Font("微软雅黑",1,30));
        g.drawString(str,10,70);

    }
    public void stepback(){
        x=x1;
        y=y1;
    }

    public void walk(int num){
        switch (num){
            case 1:
                up=true;
                down = right = left = false;
                y-=50;
                b=1;
                break;
            case 2:
                down = true;
                up = right = left = false;
                y+=50;
                b=1;
                break;
            case 3:
                left = true;
                up = down = right = false;
                x-=50;
                b=1;
                break;
            case 4:
                right = true;
                up = down = left = false;
                x+=50;
                b=1;
                break;
        }
    }
    //切换小人图片
    public void direction(Graphics g){
        if(up){
            g.drawImage(pup,x,y,null);
        } else if (down) {
            g.drawImage(pdown,x,y,null);
        }else if (left) {
            g.drawImage(pleft,x,y,null);
        }else if (right) {
            g.drawImage(pright,x,y,null);
        }
    }
    //绘制小人矩形
    public void rect(Graphics g){
        rect.x = this.x;
        rect.y = this.y;
        rect.width = 50;
        rect.height = 50;
        g.setColor(Color.red);
        g.drawRect(rect.x,rect.y,rect.width,rect.height);
    }

    //游戏重置
    public void reset(){
        this.x = x1;
        this.y = y1;
    }
}
