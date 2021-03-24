import java.awt.*;

public class Hitbox {

    private Rectangle r;
    private int pad;

    public Hitbox (int x, int y, int w, int h) {
        r = new Rectangle(x, y, w,h);

    }

    public void update (int x, int y) {
        r.x = x;
        r.y = y;
    }

    public boolean intersects (Hitbox hb) {
        return this.r.intersects(hb.r);
    }

}