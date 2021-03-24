public class Fruit extends Actor{
    public Fruit(String fileName, int speed) {
        super((Math.random() * 520 + 10), (Math.random() * 600 - 1000), fileName, 0, 0, 100, 100);
        this.hp = 100;
        this.damage = 0;
        setSpeed(speed);

        down();
        respawn();
    }

    @Override
    public void onIntersection(Actor act) {
        damage(act.damage);
    }

    @Override
    public void update(int ms) {
        super.update(ms);
        if(s.getY() > 1050)  {
            Panel.life--;
            respawn();

        }
    }

    private void respawn() {
        s.setX(Math.random() * 520 + 10);
        s.setY(Math.random() * 600 - 1200);
        hp = 100;
        active = true;
    }

    @Override
    public void kill() {
        respawn();
    }
}
