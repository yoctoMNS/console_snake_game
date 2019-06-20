import java.util.logging.LogManager;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;


public class GameManager {
    public static final String CURSOR_POS_0 = "\u001b[0;0f";
    public static final int STAGE_WIDTH = 20;
    public static final int STAGE_HEIGHT = 20;
    public static final int CELL_NONE = 0;
    public static final int CELL_SNAKE_HEAD = 1;
    public static final int CELL_SNAKE_BODY = 2;

    private Snake snake;
    private Fruit fruit;
    private ConsoleController clear;
    private MyNativeHook hook;


    private class MyNativeHook implements NativeKeyListener {
        @Override public void nativeKeyPressed(NativeKeyEvent e) {
            switch (e.getKeyCode()) {
            case NativeKeyEvent.VC_A:
                snake.setDirection(Snake.LEFT);
                break;

            case NativeKeyEvent.VC_S:
                snake.setDirection(Snake.DOWN);
                break;

            case NativeKeyEvent.VC_D:
                snake.setDirection(Snake.RIGHT);
                break;

            case NativeKeyEvent.VC_W:
                snake.setDirection(Snake.UP);
                break;
            }
        }


        @Override public void nativeKeyReleased(NativeKeyEvent e) {
        }


        @Override public void nativeKeyTyped(NativeKeyEvent e) {
        }
    }


    public GameManager() {
        init();
    }


    private void init() {
        buildInstance();
        fruit.setRandomPosition();
    }


    private void buildInstance() {
        snake = new Snake(STAGE_WIDTH/2, STAGE_HEIGHT/2);
        fruit = new Fruit(0, 0);
        clear = new ConsoleController("/bin/bash", "-c", "clear");
        hook = new MyNativeHook();
    }


    private void update() {
        snake.addTail();
        snake.move();

        if (snake.getX()==fruit.getX() &&
            snake.getY()==fruit.getY()) {
            fruit.setRandomPosition();
            snake.addLength();
        }
    }


    private void draw() {
        System.out.println(CURSOR_POS_0);

        for (int x=0; x<STAGE_WIDTH+2; ++x)
            System.out.print("□ ");
        System.out.println();

        for (int y=0; y<STAGE_HEIGHT; ++y) {
            for (int x=-1; x<STAGE_WIDTH+1; ++x) {
                if (x==-1 || x==STAGE_WIDTH) {
                    System.out.print("□ ");
                } else if (x==snake.getX() && y==snake.getY()) {
                    System.out.print("＠");
                } else if (x==fruit.getX() && y==fruit.getY()) {
                    System.out.print("Ｆ");
                } else {
                    boolean draw = false;
                    for (int i=0; i<snake.getLength(); ++i) {
                        if (x==snake.getTailX(i) && y==snake.getTailY(i)) {
                            System.out.print("○ ");
                            draw = true;
                        }
                    }
                    if (!draw) {
                        System.out.print("  ");
                    }
                }
            }
            System.out.println();
        }

        for (int x=0; x<STAGE_WIDTH+2; ++x)
            System.out.print("□ ");
        System.out.println();
    }


    private void registerNativehook() {
        LogManager.getLogManager().reset();
        
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e ) {
            System.out.println("NativeHookExeption");
        }

        GlobalScreen.addNativeKeyListener(hook);
    }


    public void run() {
        clear.execute();
        registerNativehook();

        while (true) {
            draw();
            update();
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
    }
}
