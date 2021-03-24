import java.awt.*;
import java.util.ArrayList;

public class Sprite extends Texture {

    private double angle = 0; // радианы
    private int    speed = 300; // скорость в пикс/с

    private int frameWidth, frameHeight;
    private int frame;
    private int frameTime = 80, elapsedFrameTime = 0;
    private ArrayList <Point> list = new ArrayList <Point> ();


    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Sprite(double x, double y, String fileName, int frameX, int frameY, int w, int h) {
        super(x, y, fileName);
        addFrame(frameX, frameY) ;
        frame = 0;
        this.frameHeight = h;
        this.frameWidth  = w;
    }

    public void addFrame(int x, int y) {
        list.add( new Point(x, y));
    }

    private void nextFrame() {
        frame++;
        if (frame >= list.size() ) {
            frame = 0;
        }
    }

    private void updateFrames (int ms) {
        elapsedFrameTime += ms;

        if (elapsedFrameTime >= frameTime) {
            nextFrame();
            elapsedFrameTime = 0;
        }
    }
    private void updateXY (int ms) {
        double dx, dy;
        dx = speed * Math.cos(angle) * ms/1000.0;
        dy = speed * Math.sin(angle) * ms/1000.0;
        x = x + dx;
        y = y + dy;
    }

    public void update (int ms) {
        updateFrames(ms);
        updateXY(ms);

    }

    public void paint(Graphics g) {
        g.drawImage(
                image,
                (int)x, (int)y, (int)x + frameWidth, (int)y + frameHeight,
                list.get(frame).x, list.get(frame).y, list.get(frame).x + frameWidth, list.get(frame).y + frameHeight,
                null );
    }


}
