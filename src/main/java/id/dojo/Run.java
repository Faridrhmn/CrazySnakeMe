package id.dojo;

import id.dojo.things.Fruit;
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

    public static void controlUp(){
        snake.turnUp(game.getBoard());
    }

    public static void controlDown(){
        snake.turnDown(game.getBoard());
    }

    public static void controlLeft(){
        snake.turnLeft(game.getBoard());

    }

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

        Fruit fruit = Fruit.getBuilder()
                .setName("Buah")
                .setAppearance(" o")
                .setPosition(5, 5)
                .build();

        game = Game.getBuilder()
                .createBoard(row, col)
                .createWall()
                .createFruit(fruit)
                .createSnake(snake)
                .generatePopulation()
                .generateFruit()
                .build();


        try {
            game.render();
        } catch (Exception e) {
            String pop = Snake.getErrorKind();
            System.out.println("GAME OVER : " + pop);
        }
    }
}