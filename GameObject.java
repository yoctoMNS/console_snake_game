public class GameObject {
    protected int x;
    protected int y;


    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    
    public void setX(int x) {
        if (x < GameManager.STAGE_WIDTH) {
            this.x = x;
        }
    }


    public int getX() {
        return x;
    }


    public void setY(int y) {
        if (y < GameManager.STAGE_HEIGHT) {
            this.y = y;
        }
    }


    public int getY() {
        return y;
    }
}
