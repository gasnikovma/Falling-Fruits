public class Block extends Actor {

    public Block(double x, double y, int w, int speed) {
        super(x, y, "block.png", 0, 0, w, 20);

        this.hp = 100000;
        setSpeed(speed);
        this.damage = 1000;

    }


    @Override
    public void onIntersection(Actor act) {
        Panel.fruitCounter++;
    }

    @Override
    public void update(int ms) {
        super.update(ms);
        if(s.getX() < 10) {
            s.setX(10);
            //right();
        }
        else if (s.getX() > 470) {
            s.setX(470);
            //left();
        }
    }
}
