package id.dojo;

import id.dojo.model.Points;
import id.dojo.things.*;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Game {
    private final Board board;
    private List<Wall> walls;
    private Snake snake;
    private int speed;
    private final Fruit fruit;

    public Game(Builder build) {
        this.board = build.board;
        this.walls = build.walls;
        this.speed = build.speed;
        this.snake = build.snake;
        this.fruit = build.fruit;
    }

    public Board getBoard() {
        return board;
    }

    public void render() throws InterruptedException, IOException {
        while (true){
            board.displayBoard();
            snake.stepForward(fruit,board);
            Thread.sleep(100); // for speed
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        }
    }

    private Random random = new Random();

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        Board board;
        List<Wall> walls;
        Snake snake;
        Fruit fruit;
        int speed;

        public Builder createBoard(int rows, int columns) {
            board = new Board("Board", "", rows, columns);
            return this;
        }

        public Builder createWall() {
            // Method untuk membuat area game
            int row = board.getRow();
            int col = board.getCol();

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                        board.putObject(new Points(i, j), new Wall(i, j));
                    }
                }
                System.out.println();
            }
            return this;
        }

        public Builder createSnake(Snake snake) {
            this.snake = snake;
            return this;
        }

        public Builder createFruit(Fruit fruit) {
            this.fruit = fruit;
            return this;
        }

        public Builder generateFruit() {
            board.putObject(fruit.getPosition(), fruit);
            return this;
        }

        public Builder generatePopulation(){
            board.putObject(snake.getHead(), snake);
            return this;
        }


        public Game build(){
            Game game = new Game(this);
            return game;
        }
        //END OG INNERCLASS
    }
    //EOC
}
