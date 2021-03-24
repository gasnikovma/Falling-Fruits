import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Main {

    public static void main(String[] args) {

        new Window(640, 1000);
    }
}

class Window extends JFrame {

    public Window(int w, int h)  {
        setSize(w + 17, h + 46);
        setTitle("Falling Fruits");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        KeyState keyState = new KeyState();
        addKeyListener(keyState);


        Panel p = new Panel(keyState);
        add(p);

        revalidate();
    }
}

class Panel extends JPanel {

    long t2, t1;

    Actor block = new Block(5, 900, 160, 250);

    ActorCollection fruits = new ActorCollection();


    Sprite bg = new Sprite(0, 0, "bg.png", 0, 130, 640, 1000);

    KeyState keyState;

    Font f0 = new Font(Font.MONOSPACED, Font.BOLD, 70);
    Font f1 = new Font(Font.MONOSPACED, Font.BOLD, 100);

    int blockSpeed = 0;
    int fruitsSpeed = 0;

    static int life = 3;

    Color color = new Color (175, 52, 179);

    long k = 0;

    Menu menu = new Menu();

    int bonusSpeed = 25;

    public static int fruitCounter = 0;

    public Panel (KeyState keyState) {
        this.keyState = keyState;
        setFocusable(true);
        setBackground(Color.BLACK);

        bg.setSpeed(0);

        fruits.addActor(new Fruit("0.png", 200));

        t1 = System.currentTimeMillis();


    }

    public void lifePaint (Graphics g, int x) {
        for (int i = 0; i < life; i++) {
             Sprite s1 = new Sprite(x + i * 100, 50, "life.png",0, 0,  100, 81);
             s1.paint(g);
        }
    }

    public void endGame() {
        pauseGame();
        JOptionPane.showMessageDialog(null, "The end! \n Your score: " + String.valueOf(fruitCounter));
    }

    public void pauseGame () {
        blockSpeed = block.s.getSpeed();
        fruitsSpeed = fruits.getSpeed();

        block.setSpeed(0);
        fruits.setSpeed(0);
    }

    public void continueGame () {
        block.setSpeed(blockSpeed);
        fruits.setSpeed(fruitsSpeed);
    }

    private void exitGame() {
        System.exit(0);
    }

    private void speedGame(int speed) {
        block.speedUp(speed);
        fruits.setSpeed(fruits.getSpeed() + speed);
    }

    private void controlGame() {

        if(keyState.isKeyPress(KeyEvent.VK_RIGHT)) {
            block.right();
        }

        else if(keyState.isKeyPress(KeyEvent.VK_LEFT)) {
            block.left();
        }


        if ((t1 + 1000) % 3000 == 0) {
            int a = (int)(Math.random() * 13);
            fruits.addActor(new Fruit(String.valueOf(a) + ".png", 200));
        }

        if(!menu.visibility && keyState.isKeyPress(KeyEvent.VK_ESCAPE)) {
            pauseGame();
            menu.visibility = true;
        }

        if(menu.visibility && keyState.isKeyPress(KeyEvent.VK_UP)) {
            menu.prev();
        }

        if(menu.visibility && keyState.isKeyPress(KeyEvent.VK_DOWN)) {
            menu.next();
        }

        if(menu.visibility && keyState.isKeyPress(KeyEvent.VK_ENTER) && menu.select() == 0) {
            continueGame();
        }

        if(menu.visibility && keyState.isKeyPress(KeyEvent.VK_ENTER) && menu.select() == 1) {

        }

        if(keyState.isKeyPress(KeyEvent.VK_ENTER) && menu.select() == 2) {
            exitGame();
        }

        if(life <= 0) {
            endGame();
        }

        switch (fruitCounter) {
            case 25: if (k == 0) speedGame(bonusSpeed); k++;
            case 50: if (k == 1) speedGame(bonusSpeed); k++;
            case 100: if (k == 2) speedGame(bonusSpeed); k++;
            case 150: if (k == 3) speedGame(bonusSpeed); k++;
        }

    }

    private void updateGame(int ms) {
        bg.update(ms);
        block.update(ms);
        fruits.update(ms);
        keyState.update();
//        System.out.println(block.s.getAngle());
    }

    private void paintGame (Graphics g) {
        bg.paint(g);
        block.paint(g);
        fruits.paint(g);

        if (fruitCounter % 25 == 0) {
            g.setColor(color);
            g.setFont(f1);
        }
        else {
            g.setColor(Color.WHITE);
            g.setFont(f0);
        }
        g.drawString(String.valueOf(fruitCounter), 300, 500);

//        g.setColor(color);
//        g.setFont(f0);
//        g.drawString(String.valueOf(life),300, 100);

        lifePaint(g, 175);

        fruits.paint(g);

        menu.paint(g,225, 400);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        t2 = System.currentTimeMillis();
        int ms = (int)(t2-t1);

        controlGame();
        updateGame(ms);
        paintGame(g);

        fruits.intersects(block);


        t1 = t2;
        repaint();
    }
}