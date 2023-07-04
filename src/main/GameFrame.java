package main;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class GameFrame extends Frame {

    private GameBackground gameBackground;
    private GameBox gamebox;
    private GamePeople gamepeople;
    private BufferedImage buffimg = new BufferedImage(700,700,BufferedImage.TYPE_4BYTE_ABGR);

    public GameFrame() throws HeadlessException {
        Music.load();
        Music.playFly();
        setSize(700,700);
        setVisible(true);
        setTitle("六神无组");
        setLocationRelativeTo(null);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        new run().start();

        Instantiation();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyPressed(e);

            }

            @Override
            public void keyPressed(KeyEvent e) {
                add(e);
            }
        });
    }

    public void Instantiation(){
        gameBackground = new GameBackground();
        gamebox = new GameBox();
        gamepeople = new GamePeople();

    }
    class run extends Thread{
        @Override
        public void run() {
            while (true){
                repaint();
                try {
                    Thread.sleep(33);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (gamebox.checkpoint == 2){
                    setSize(550,550);
                } else if (gamebox.checkpoint == 3) {
                    setSize(600,450);
                }

            }
        }
    }
    boolean at = true;

    @Override
    public void update(Graphics g) {
        if (gamepeople.life == 0){
            g.setColor(Color.RED);
            String over = "游戏失败";
            g.setFont(new Font("微软雅黑",1,30));
            g.drawString(over,100,400);
            return;
        }
        if (gamebox.checkpoint==1){
            Graphics graphics = buffimg.getGraphics();
            gameBackground.draw(graphics);
            gamebox.draw(graphics,gamepeople,gamebox.checkpoint);
            gamepeople.draw(graphics,250,250);
            g.drawImage(buffimg,0,0,null);

        }else if (gamebox.checkpoint == 2){
            if (at){
                gamepeople.aa = true;
                gamebox.behavior.clear();
                at = false;
            }
            Graphics graphics = buffimg.getGraphics();
            gameBackground.draw(graphics);
            gamebox.draw(graphics,gamepeople,gamebox.checkpoint);
            gamepeople.draw(graphics,100,100);
            g.drawImage(buffimg,0,0,null);

        } else if (gamebox.checkpoint == 3) {
            if (at){
                gamepeople.aa = true;
                gamebox.behavior.clear();
                at = false;
            }
            Graphics graphics = buffimg.getGraphics();
            gameBackground.draw(graphics);
            gamebox.draw(graphics,gamepeople,gamebox.checkpoint);
            gamepeople.draw(graphics,100,200);
            g.drawImage(buffimg,0,0,null);
        } else if (gamebox.checkpoint == 4) {
            if (at){
                gamepeople.aa = true;
                at = false;
            }
            String over = "闯关结束";
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑",1,60));
            g.drawString(over,100,400);

        }

    }
    public void add(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                gamepeople.walk(1);
                break;
            case KeyEvent.VK_DOWN:
                gamepeople.walk(2);
                break;
            case KeyEvent.VK_LEFT:
                gamepeople.walk(3);
                break;
            case KeyEvent.VK_RIGHT:
                gamepeople.walk(4);
                break;
            case KeyEvent.VK_L:
                gamepeople.stepback();
                gamebox.stepBack();
                break;
            case KeyEvent.VK_SPACE:
                gamepeople.reset();
                gamebox.reset();
                break;
            case KeyEvent.VK_Q:
                gamebox.resett();
                gamepeople.aa = true;
                gamepeople.life = 3;
                at = true;
                break;
        }
    }
}
