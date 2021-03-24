import java.awt.*;

abstract public class Actor {

    boolean active;

    int damage;
    protected int hp;

    Sprite s;

    int oldSpeed = 300;

    Hitbox h;

    public Actor (double x, double y, String fileName, int frameX, int frameY, int w, int h) {
        s = new Sprite(x, y, fileName, frameX, frameY, w, h);
        this.h = new Hitbox((int)x, (int)y, w, h);
        hp = 100;
        damage = 0;
        active = true;
    }

    public void setSpeed (int speed) {
        s.setSpeed(speed);
    }

    abstract public void onIntersection (Actor act);

    public void intersects (Actor act) {
        if(h.intersects(act.h)) {
            onIntersection(act);
            act.onIntersection(this);
        }
    }

    public void update(int ms) {
        if (active) {
            s.update(ms);
            h.update((int)s.getX(), (int)s.getY());
        }
    }

    public void speedUp (int speed) {
        this.setSpeed(this.s.getSpeed() + speed);
    }

    public void paint(Graphics g) {

        if (active) {
            s.paint(g);
        }
    }

    public void kill() {
        hp = 0;
        active = false;
    }

    public void damage (int damage) {
        hp -= damage;
        if (hp <= 0) {
            kill();
        }
    }

    public void left () {
        s.setAngle(-Math.PI);
    }

    public void right () {
        s.setAngle(0);
    }

    public void up () {
        s.setAngle(-Math.PI / 2);
    }

    public void down () {
        s.setAngle(Math.PI / 2);
    }

    public void stop () {
        oldSpeed = s.getSpeed();
        s.setSpeed(0);
    }

    public void go () {
        s.setSpeed(oldSpeed);
    }
}
