import java.awt.Point;


public class Snake extends GameObject {
    public static final int STOP = 0;
    public static final int UP = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int DOWN = 4;

    private int length;
    private int[] tailX;
    private int[] tailY;
    private int direction;


    public Snake(int x, int y) {
        super(x, y);
        length = 0;
        tailX = new int[100];
        tailY = new int[100];
        direction = STOP;
    }

    public void move() {
        switch (direction) {
        case UP:
            up(); break;

        case DOWN:
            down(); break;

        case LEFT:
            left(); break;

        case RIGHT:
            right(); break;
        }
    }


    public void addTail() {
        int preX = tailX[0];
        int preY = tailY[0];
        tailX[0] = x;
        tailY[0] = y;
        int preX2 = 0, preY2 = 0;

        for (int i=1; i<length; ++i) {
            preX2 = tailX[i];
            preY2 = tailY[i];
            tailX[i] = preX;
            tailY[i] = preY;
            preX = preX2;
            preY = preY2;
        }
    }


    public int getTailX(int idx) {
        return tailX[idx];
    }


    public int getTailY(int idx) {
        return tailY[idx];
    }


    public void up() {
        --y;
    }


    public void down() {
        ++y;
    }


    public void left() {
        --x;
    }


    public void right() {
        ++x;
    }


    public void setDirection(int dir) {
        direction = dir;
    }


    public int getLength() {
        return length;
    }


    public void addLength() {
        ++length;
    }
}
