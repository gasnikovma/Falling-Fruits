import java.awt.event.KeyEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyState implements KeyListener {


    private boolean [] oldstates = new boolean[256];
    private boolean [] states = new boolean[256];


    public void update () {
        oldstates = states.clone();
    }

    public boolean isKeyPress (int code) {
        return states[code] && !oldstates[code];
    }

    public boolean isKeyDown (int code)  {
        return states[code];
    }

    public void setKeyState (boolean state, int code) {
        states[code] = state;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        setKeyState(true, e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        setKeyState(false, e.getKeyCode());
    }
}
