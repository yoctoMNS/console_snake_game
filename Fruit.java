import java.util.Random;


public class Fruit extends GameObject {
    private Random random;


    public Fruit(int x, int y) {
        super(x, y);
        random = new Random();
    }


    public void setRandomPosition() {
        x = random.nextInt(GameManager.STAGE_WIDTH);
        y = random.nextInt(GameManager.STAGE_HEIGHT);
    }
}
