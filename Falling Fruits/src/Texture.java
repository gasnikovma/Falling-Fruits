import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Texture {

    protected double x, y;

    protected BufferedImage image = null;

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Texture(double x, double y, String fileName) {
        this.x = x;
        this.y = y;

        try {
            image = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.exit(1);
        }
    }

    public void paint(Graphics g) {
        g.drawImage(image, (int)x, (int)y, null );
    }


}