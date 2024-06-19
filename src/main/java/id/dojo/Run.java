package id.dojo;

import id.dojo.things.Snake;

public class Run {

    static Snake snake;
    static Game game;

    static {
        System.loadLibrary("native");

        Thread runControl = new Thread(new Runnable() {
            @Override
            public void run() {
                controls();
            }
        });

        runControl.start();
    }

    public static native void controls();

    // UATARA ATAU ATAS
    public static void controlUp(){
        snake.turnUp(game.getBoard());
    }
    // SELATAN ATAU BAWAH
    public static void controlDown(){
        snake.turnDown(game.getBoard());
    }
    // TIMUR ATAU KIRI
    public static void controlLeft(){
        snake.turnLeft(game.getBoard());

    }
    // BARAT ATAU KANAN
    public static void controlRight(){
        snake.turnRight(game.getBoard());

    }


    public static void main(String[] args) {
        // controls();
        int row = 30;
        int col = 30;
        int posX = 5;
        int posY = 20;


        snake = Snake.getBuilder()
                .setName("Ularku")
                .setAppearance(" o")
                .setPosition(posX, posY)
                .setSize(8)
                .build();
        snake.generateBody();

        game = Game.getBuilder()
                .createBoard(row, col)
                .createWall()
                .createSnake(snake)
                .generatePopulation()
                .build();


        try {
            game.render();
        } catch (Exception e) {
            String pop = Snake.getErrorKind();
            System.out.println("GAME OVER : " + pop);
        }
    }
}