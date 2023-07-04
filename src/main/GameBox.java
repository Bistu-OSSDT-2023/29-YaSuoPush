package main;

import util.GameUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZY
 * @create 17:00
 */
public class GameBox extends GameCheckpoint{
    public List behavior;
    public Rectangle rect;
    public List<Rectangle> rects;
    int blk = 0;
    int blk1 = 0;
    public int checkpoint = 1;
    boolean boo = false;
    private List blks;


    boolean chenum = true;

    private Image fire;
    private Image blue;
    private Image black;
    private Image yellow;
    private static final String FIRE="img/fire.png";
    private static final String BLUE="img/blue.png";
    private static final String BLACK="img/black.png";
    private static final String YELLOW="img/yellow.png";
    public GameBox(){
        fire = GameUtil.LoadBufferedImg(FIRE);
        blue = GameUtil.LoadBufferedImg(BLUE);
        black = GameUtil.LoadBufferedImg(BLACK);
        yellow = GameUtil.LoadBufferedImg(YELLOW);
        rect = new Rectangle();
        rects = new ArrayList<>();
        blks = new ArrayList();
        behavior = new ArrayList();

    }

    int x=0,y=0;
    int box[][];
    public void draw(Graphics g,GamePeople gamePeople,int check){
        if(chenum) {
            box = che(check);
            chenum = false;
        }
        if (boo){
            box=rs();
            boo = false;
        }
        for(int j=0;j<box.length;j++){
            for (int i = 0; i < box[0].length; i++) {
                switch (box[j][i]){
                    case 0:
                        x=(i+1)*50;
                        y=50+j*50;
                        break;
                    case 1:
                        x=(i+1)*50;
                        y=50+j*50;
                        g.drawImage(fire,x,y,null);
                        save(x,y,g);
                        break;
                    case 2:
                        x=(i+1)*50;
                        y=50+j*50;
                        g.drawImage(blue,x,y,null);
                        break;
                    case 3:
                        x=(i+1)*50;
                        y=50+j*50;
                        g.drawImage(black,x,y,null);
                        break;
                    case 4:
                        x=(i+1)*50;
                        y=50+j*50;
                        if(gamePeople.x == x && gamePeople.y == y){
                            if(gamePeople.up){
                                if (box[j-1][i]!=1 && box[j-1][i]!=4) {
                                    if (box[j-1][i]==3 && !blks.contains((j+1)*10+i)){
                                        blk1++;
                                    }
                                    add(j,i,box[j][i],j-1,i,box[j-1][i]);
                                    if (blks.contains((j+1)*10+i)){
                                        box[j][i] = 3;
                                        box[j - 1][i] = 4;
                                    }else {
                                        box[j][i] = 2;
                                        box[j - 1][i] = 4;
                                    }

                                } else {
                                    gamePeople.y+=50;
                                }
                            } else if (gamePeople.down) {
                                if (box[j+1][i]!=1&& box[j+1][i]!=4) {
                                    if (box[j+1][i]==3&& !blks.contains((j+1)*10+i)){
                                        blk1++;
                                    }
                                    add(j,i,box[j][i],j+1,i,box[j+1][i]);
                                    if (blks.contains((j+1)*10+i)){
                                        box[j][i] = 3;
                                        box[j + 1][i] = 4;
                                    }else {
                                        box[j][i] = 2;
                                        box[j + 1][i] = 4;
                                    }

                                }else {
                                    gamePeople.y-=50;
                                }
                            } else if (gamePeople.right){
                                if (box[j][i+1]!=1&& box[j][i+1]!=4) {
                                    if (box[j][i+1]==3&& !blks.contains((j+1)*10+i)){
                                        blk1++;
                                    }
                                    add(j,i,box[j][i],j,i+1,box[j][i+1]);
                                    if (blks.contains((j+1)*10+i)){
                                        box[j][i] = 3;
                                        box[j ][i+1] = 4;
                                    }else {
                                        box[j][i] = 2;
                                        box[j][i+1] = 4;
                                    }

                                }else {
                                    gamePeople.x-=50;
                                }
                            } else if (gamePeople.left) {
                                if (box[j][i-1]!=1&& box[j][i-1]!=4) {
                                    if (box[j][i-1]==3){
                                        blk1++;
                                    }
                                    add(j,i,box[j][i],j,i-1,box[j][i-1]);

                                    if (blks.contains((j+1)*10+i)){
                                        box[j][i] = 3;
                                        box[j][i-1] = 4;
                                    }else {
                                        box[j][i] = 2;
                                        box[j][i-1] = 4;
                                    }

                                }else {
                                    gamePeople.x+=50;
                                }

                            }
                        }
                        g.drawImage(yellow,x,y,null);
                        break;
                }
            }
        }
        coll(gamePeople);
        if (blk == blk1 ){
            checkpoint ++;
            chenum = true;
        }
        g.setColor(Color.RED);
        String str = "当前关卡:"+this.checkpoint;
        g.setFont(new Font("微软雅黑",1,30));
        g.drawString(str,340,80);
    }
    //绘制墙体
    public void save(int x,int y ,Graphics g){
        if(filesize>0) {
            GameBox gamebox = new GameBox();
            gamebox.rect.x = this.x;
            gamebox.rect.y = this.y;
            gamebox.rect.width = 50;
            gamebox.rect.height = 50;
            rects.add(gamebox.rect);
            filesize--;
        }
        g.setColor(Color.RED);
        g.drawRect(rect.x, rect.y, rect.width, rect.height);

    }
    int filesize = 0;
    public int[][] che(int check) {
        switch (check) {
            case 1:
                rects.clear();
                filesize = 0;
                blk = 0;
                blk1 = 0;
                blks.clear();
                for (int i = 0; i < box1.length; i++) {
                    for (int j = 0; j < box1[0].length; j++) {
                        if (box1[i][j] == 1) {
                            filesize++;
                        }
                        if (box1[i][j] == 3) {
                            blk++;
                            blks.add((i+1)*10+j);
                        }
                    }
                }
                return box1;

            case 2:
                rects.clear();
                filesize = 0;
                blk = 0;
                blk1 = 0;
                blks.clear();
                for (int i = 0; i < box2.length; i++) {
                    for (int j = 0; j < box2[0].length; j++) {
                        if (box2[i][j] == 1) {
                            filesize++;
                        }
                        if (box2[i][j] == 3) {
                            blk++;
                            blks.add((i+1)*10+j);
                        }
                    }
                }
                return box2;
            case 3:
                rects.clear();
                filesize = 0;
                blk = 0;
                blk1 = 0;
                blks.clear();
                for (int i = 0; i < box3.length; i++) {
                    for (int j = 0; j < box3[0].length; j++) {
                        if (box3[i][j] == 1) {
                            filesize++;
                        }
                        if (box3[i][j] == 3) {
                            blk++;
                            blks.add((i+1)*10+j);
                        }
                    }
                }
                return box3;

        }
        return null;
    }


    //发生碰撞
    public void coll(GamePeople gamepeople){
        for (int i = 0; i < rects.size(); i++) {
            Rectangle rectangle = rects.get(i);

            if(gamepeople.rect.intersects(rectangle)){
                System.out.println("撞上了");
                gamepeople.life--;
                if(gamepeople.up ){
                    gamepeople.y+=50;
                } else if (gamepeople.down) {
                    gamepeople.y-=50;
                } else if (gamepeople.left) {
                    gamepeople.x+=50;
                } else if (gamepeople.right) {
                    gamepeople.x-=50;
                }
            }
        }


    }
    //存储箱子上一步位置
    public void add(int j ,int i ,int num,int j1,int i1,int num1){
        behavior.add(j*100000+i*10000+num*1000+j1*100+i1*10+num1);
    }

    public int stepBack(){
        if (behavior.size() == 0)return 0;
        int num = (int)behavior.get(behavior.size()-1);
        behavior.remove(behavior.size()-1);
        int i = num/100000;
        int j = num/10000%10;
        int n = num/1000%10;
        int i1 = num/100%10;
        int j1 = num/10%10;
        int n1 = num%10;
        box[i1][j1]=n1;
        box[i][j]=n;

        int num1 = (i1+1)*10+j1;
        int num2 = (i+1)*10+j;
        for (int k = 0; k < blks.size(); k++) {
            if ((int)blks.get(k) == num1){
                blk--;
            }
        }

        return 0;
    }
    //游戏重置
    public void reset(){
        chenum = true;
        blk = 0;
        blk1 = 0;
        switch (checkpoint){
            case 1:
                for (int i =0;i<box1Rest.length;i++){
                    for (int j = 0; j < box1Rest[0].length; j++) {
                        box[i][j]=box1Rest[i][j];
                    }
                }
                break;
            case 2:
                for (int i =0;i<box2Rest.length;i++){
                    for (int j = 0; j < box2Rest[0].length; j++) {
                        box[i][j]=box2Rest[i][j];
                    }
                }
                break;
            case 3:
                for (int i = 0; i < box3Rest.length; i++) {
                    for (int j = 0; j < box3Rest[0].length; j++) {
                        box[i][j]=box3Rest[i][j];
                    }
                }
        }
    }
    //游戏重置
    public void resett(){
        this.checkpoint = 1;
        boo = true;
        behavior.clear();
    }
    public int[][] rs(){
        filesize = 0;
        blk = 0;
        blk1 = 0;
        rects.clear();
        blks.clear();
        for (int i = 0; i < box1Rest.length; i++) {
            for (int j = 0; j < box1Rest.length; j++) {
                if (box1Rest[i][j] == 1) {
                    filesize++;
                }
                if (box1Rest[i][j] == 3) {
                    blk++;
                    blks.add((i+1)*10+j);
                }
            }
        }
        box = new int[][]{
                {0,0,1,1,1,0,0,0},
                {0,0,1,3,1,0,0,0},
                {0,0,1,2,1,1,1,1},
                {1,1,1,4,2,4,3,1},
                {1,3,2,4,2,1,1,1},
                {1,1,1,1,4,1,0,0},
                {0,0,0,1,3,1,0,0},
                {0,0,0,1,1,1,0,0}
        };
        return box;
    }
}
