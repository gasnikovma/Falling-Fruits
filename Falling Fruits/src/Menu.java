import java.awt.*;

public class Menu {

    private String [] options = {"continue", "...","exit"};
    private int index = 0;

    private Font f = new Font(Font.SANS_SERIF, Font.BOLD, 30 );
    public boolean visibility = false;

    public void next() {
        index ++;
        if (index > options.length - 1) {
            index = 0;
        }
    }

    public int select () {
        visibility = false;
        return index;
    }

    public void toggleMenu () {
        visibility = !visibility;
    }

    public void prev() {
        index --;
        if (index < 0) {
            index = options.length - 1;
        }
    }

    public void paint (Graphics g, int x, int y) {
        if (!visibility) {
            return;
        }

        g.setColor(Color.BLACK);
        g.fillRect(x - 50, y - 50, 300, (options.length + 1) * 60);

        g.setFont(f);
        g.setColor(Color.GRAY);

        g.drawString("       MENU       ", x, y);

        for (int i = 0; i < options.length; i++) {
            if (i == index) {
                g.setColor(Color.RED);
            }
            else {
                g.setColor(Color.WHITE);
            }
            g.drawString(options[i], x, y + (i + 1) * 50);
        }

    }



}
