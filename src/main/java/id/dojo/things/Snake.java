package id.dojo.things;

import id.dojo.model.Points;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake extends Thing implements AnimalBehavior{
    private Points head;
    private int size;
    private List<Points> body;
    static public int row;
    static public int column;
    private static String direction = "kiri";
    private static String errorKind = "nothing";
    static int buahX;
    static int buahY;
    private Points tail;

    public Snake(Builder builder) {
        super(builder.getName(), builder.getAppearance());
        this.head = builder.getPosition();
        this.size = builder.getSize();
        body = new ArrayList<>();
    }

    public void generateBody(){
        int x = head.getX();
        int y = head.getY();
        for (int i = 1; i < size; i++) {
            body.add(new Points(x, ++y));
        }
    }

    public static Builder getBuilder()
    {

        System.out.println("coba aja");
        return new Builder();
    }

    public Points getHead() {
        return head;
    }

    public void setHead(Points head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public static String getErrorKind() {
        return errorKind;
    }

    public void setErrorKind(String errorKind) { this.errorKind = errorKind; }

    public static class Builder{
        private int size;
        private String name;
        private String appearance;
        private int posX, posY;

        public int getSize(){
            return size;
        }

        public  Builder setSize(int size){
            this.size = size;
            return this;
        }

        public String getName(){
            return name;
        }

        public  Builder setName(String name){
            this.name = name;
            return this;
        }

        public  Builder setAppearance(String appearance){
            this.appearance = appearance;
            return this;
        }

        public String getAppearance(){
            return appearance;
        }

        public Points getPosition(){
            return new Points(posX, posY);
        }

        public Builder setPosition(int posX, int posY){
            this.posX = posX;
            this.posY = posY;
            return this;
        }

        public Snake build(){

//            System.out.println("coba aja");
            return new Snake(this);
        }

        //END OF INNER CLASSS
    }

    @Override
    public Points checkForward() {
        int xPos = 0;
        int yPos = 0;

        // ke selatan
        if (head.getX() == 28 && head.getY()== 1){
            if (direction.equals("kiri")){
                xPos = head.getX();
                yPos = head.getY() + 1;
            } else {
                xPos = head.getX() - 1;
                yPos = head.getY();
            }
        } else if (head.getX() - 1 == body.getFirst().getX()){
            xPos = head.getX() + 1;
            yPos = head.getY();
            // ke timur
        } else if (head.getY() - 1 == body.getFirst().getY()){
            xPos = head.getX();
            yPos = head.getY() + 1;
            //ke barat
        } else if (head.getY() + 1 == body.getFirst().getY()){
            xPos = head.getX();
            yPos = head.getY() - 1;
            //keutara
        } else if (head.getX() == 1 && head.getY() == 28) {
            if (direction.equals("kiri")){
                xPos = head.getX();
                yPos = head.getY() - 1;
            } else {
                xPos = head.getX() + 1;
                yPos = head.getY();
            }
        }else if (head.getX() + 1 == body.getFirst().getX()){
            xPos = head.getX() - 1;
            yPos = head.getY() ;
        } 
        

        return new Points(xPos, yPos);
    }
    
    @Override
//    public void stepForward(Board board) {
//        column = board.getCol();
//        row = board.getRow();
//        Points position = checkForward();
//        board.putObject(head, null);
//        for (Points bodPosisi : body) {
//            board.putObject(bodPosisi, null);
//        }
//
//        Points oldHead = new Points(head.getX(), head.getY());
//        head.setX(position.getX());
//        head.setY(position.getY());
//        board.putObject(head, this);
//        int cekX = head.getX();
//        int cekY = head.getY();
//
//        Points prev = oldHead;
//        for (int i = 0; i < body.size(); i++) {
//            Points crnt = body.get(i);
//            Points temp = new Points(crnt.getX(), crnt.getY());
//            crnt.setX(prev.getX());
//            crnt.setY(prev.getY());
//            try {
//                if(cekX == crnt.getX() && cekY == crnt.getY()){
//                    setErrorKind("Snake eat body!");
//                    throw new handleError(100,100);
//                }
////                else {
////                    setErrorKind("snake out from field!");
////                }
//            } catch (Exception e) {
//                head.setX(100);
//                head.setY(100);
//                setErrorKind("Snake eat body!");
//            }
//
//            prev = temp;
//            board.putObject(crnt, this);
//        }
//
//    }

    public void stepForward(Fruit fruit, Board board) {

        if (head.getY() == fruit.getPosition().getY() && head.getX() == fruit.getPosition().getX()){
            eat(fruit, board);
        }

        if (head.getX() == 0 || head.getX() == column - 1 || head.getY() == 0 || head.getY() == row - 1){
            setErrorKind("Snake out from field!");
        }

        column = board.getCol();
        row = board.getRow();
        Points position = checkForward();
        board.putObject(head, null);
        for (Points bodPosisi : body) {
            board.putObject(bodPosisi, null);
            if(head.getX() == bodPosisi.getX() && head.getY() == bodPosisi.getY()){
                head.setX(100);
                head.setY(100);
                setErrorKind("Snake eat body!");
            }
        }

        Points oldHead = new Points(head.getX(), head.getY());
        head.setX(position.getX());
        head.setY(position.getY());
        int cekX = head.getX();
        int cekY = head.getY();
        board.putObject(head, this);

        Points prev = oldHead;
        for (int i = 0; i < body.size(); i++) {
            Points crnt = body.get(i);
            Points temp = new Points(crnt.getX(), crnt.getY());
            crnt.setX(prev.getX());
            crnt.setY(prev.getY());
            prev = temp;
            board.putObject(crnt, this);
        }

    }

    public void turnLeft(Board board) {
        Points position = moveLeft();
        board.putObject(head, null);
        for (Points bodPosisi : body) {
            board.putObject(bodPosisi, null);
        }

        Points oldHead = new Points(head.getX(), head.getY());
        head.setX(position.getX());
        head.setY(position.getY());
        board.putObject(head, this);
        int cekX = head.getX();
        int cekY = head.getY();

        Points prev = oldHead;
        for (int i = 0; i < body.size(); i++) {
            Points crnt = body.get(i);
            Points temp = new Points(crnt.getX(), crnt.getY());
            crnt.setX(prev.getX());
            crnt.setY(prev.getY());
//            try {
//                if(cekX == crnt.getX() && cekY == crnt.getY()){
//                    setErrorKind("Snake eat body!");
//                    throw new handleError(100,100);
//                }
//            } catch (Exception e) {
//                head.setX(100);
//                head.setY(100);
//                setErrorKind("Snake eat body!");
//            }
            prev = temp;
            board.putObject(crnt, this);
        }

        direction = "kiri";
    }

//    @Override
    public void turnRight(Board board) {
        Points position = moveRight();
        board.putObject(head, null);
        for (Points bodPosisi : body) {
            board.putObject(bodPosisi, null);
        }
        Points oldHead = new Points(head.getX(), head.getY());
        head.setX(position.getX());
        head.setY(position.getY());
        board.putObject(head, this);
        int cekX = head.getX();
        int cekY = head.getY();

        Points prev = oldHead;
        for (int i = 0; i < body.size(); i++) {
            Points crnt = body.get(i);
            Points temp = new Points(crnt.getX(), crnt.getY());
            crnt.setX(prev.getX());
            crnt.setY(prev.getY());
//            try {
//                if(cekX == crnt.getX() && cekY == crnt.getY()){
//                    setErrorKind("Snake eat body!");
//                    throw new handleError(100,100);
//                }
//            } catch (Exception e) {
//                head.setX(100);
//                head.setY(100);
//                setErrorKind("Snake eat body!");
//            }
            prev = temp;
            board.putObject(crnt, this);
        }
        direction = "kanan";
    }

    public void turnUp(Board board) {
        Points position = moveUp();
        board.putObject(head, null);
        for (Points bodPosisi : body) {
            board.putObject(bodPosisi, null);
        }

        Points oldHead = new Points(head.getX(), head.getY());
        head.setX(position.getX());
        head.setY(position.getY());
        board.putObject(head, this);
        int cekX = head.getX();
        int cekY = head.getY();

        Points prev = oldHead;
        for (int i = 0; i < body.size(); i++) {
            Points crnt = body.get(i);
            Points temp = new Points(crnt.getX(), crnt.getY());
            crnt.setX(prev.getX());
            crnt.setY(prev.getY());
//            try {
//                if(cekX == crnt.getX() && cekY == crnt.getY()){
//                    setErrorKind("Snake eat body!");
//                    throw new handleError(100,100);
//                }
//            } catch (Exception e) {
//                head.setX(100);
//                head.setY(100);
//                setErrorKind("Snake eat body!");
//            }
            prev = temp;
            board.putObject(crnt, this);
        }
        direction = "atas";
    }

    public void turnDown(Board board) {
        Points position = moveDown();
        board.putObject(head, null);
        for (Points bodPosisi : body) {
            board.putObject(bodPosisi, null);
        }

        Points oldHead = new Points(head.getX(), head.getY());
        head.setX(position.getX());
        head.setY(position.getY());
        board.putObject(head, this);
        int cekX = head.getX();
        int cekY = head.getY();

        Points prev = oldHead;
        for (int i = 0; i < body.size(); i++) {
            Points crnt = body.get(i);
            Points temp = new Points(crnt.getX(), crnt.getY());
            crnt.setX(prev.getX());
            crnt.setY(prev.getY());
//            try {
//                if(cekX == crnt.getX() && cekY == crnt.getY()){
//                    setErrorKind("Snake eat body!");
//                    throw new handleError(100,100);
//                }
//            } catch (Exception e) {
//                head.setX(100);
//                head.setX(100);
//                setErrorKind("Snake eat body!");
//            }
            prev = temp;
            board.putObject(crnt, this);
        }
        direction = "bawah";
    }

    public Points moveDown() {
        int xPos = 0;
        int yPos = 0;
        int bodyPos;
        bodyPos = body.getFirst().getX();
        try{
            xPos = head.getX() + 1;
            yPos = head.getY();
            if (xPos > 0 && xPos < column-1 && yPos > 0 && yPos < row-1 && bodyPos != xPos) {
                return new Points(xPos, yPos);
            } else if (bodyPos == xPos) {
                return new Points(xPos-2, yPos);
            } else if (xPos < 0 && xPos > -15 || xPos > 30 && xPos < 40 || yPos < 0 && yPos > -15 || yPos > 30 && yPos < 40){
//                setErrorKind("Snake out from field!");
                throw new handleError(-12,-12);
            }
        } catch (handleError e) {
            return new Points(xPos+1, yPos);
        }
        return null;
    }

    public Points moveUp() {
        int xPos = 0;
        int yPos = 0;
        int bodyPos;
        bodyPos = body.getFirst().getX();
        try{
            xPos = head.getX() - 1;
            yPos = head.getY();
            if (xPos > 0 && xPos < column-1 && yPos > 0 && yPos < row-1 && bodyPos != xPos) {
                return new Points(xPos, yPos);
            } else if (bodyPos == xPos) {
                return new Points(xPos+2, yPos);
            } else if (xPos < 0 && xPos > -15 || xPos > 30 && xPos < 40 || yPos < 0 && yPos > -15 || yPos > 30 && yPos < 40){
//                setErrorKind("Snake out from field!");
                throw new handleError(-12,-12);
            }
        } catch (handleError e) {
            return new Points(xPos-1, yPos);
        }
        return null;
    }

    public Points moveLeft() {
        int xPos = 0;
        int yPos = 0;
        int bodyPos;
        bodyPos = body.getFirst().getY();
        try{
            xPos = head.getX();
            yPos = head.getY() - 1;
            if (xPos > 0 && xPos < column-1 && yPos > 0 && yPos < row-1 && bodyPos != yPos) {
                return new Points(xPos, yPos);
            } else if (bodyPos == yPos) {
                return new Points(xPos, yPos+2);
            } else if(xPos < 0 && xPos > -15 || xPos > 30 && xPos < 40 || yPos < 0 && yPos > -15 || yPos > 30 && yPos < 40){
//                setErrorKind("Snake out from field!");
                throw new handleError(-12,-12);
            }
        } catch (handleError e) {
            return new Points(xPos, yPos-1);
        }
        return null;
    }

    public Points moveRight() {
        int xPos = 0;
        int yPos = 0;
        int bodyPos;
        bodyPos = body.getFirst().getY();
        try{
            xPos = head.getX();
            yPos = head.getY() + 1;
            if (xPos > 0 && xPos < column-1 && yPos > 0 && yPos < row-1 && bodyPos != yPos) {
                return new Points(xPos, yPos);
            } else if (bodyPos == yPos) {
                return new Points(xPos, yPos-2);
            } else {
//                setErrorKind("Snake went out of bounds!");
                throw new handleError(-12,-12);
            }
        } catch (handleError e) {
            return new Points(xPos, yPos+1);
        }
    }

    public void eat(Fruit fruit, Board board) {
        size += 1;
        board.putObject(fruit.getPosition(), null);

        for (int i = 0; i < body.size(); i++) {
            Points crnt = body.get(i);
            buahX = crnt.getX();
            buahY = crnt.getY();
            tail = new Points(buahX, buahY);
        }

        body.add(new Points(buahX, buahY+1));

        int x, y;
        x = (int) (Math.random() * 22) + 3;
        y = (int) (Math.random() * 22) + 3;

        if (x == 0 || x == 1 || x == 25 || x == 24 || x == 23){
            x = 3;
        }

        if (y == 0 || y == 1 || y == 25 || y == 24 || y == 23){
            y = 3;
        }
        fruit.setPosition(new Points(x, y));
        board.putObject(fruit.getPosition(), this);
    }
}

class handleError extends Exception {

    private String message;

    public handleError(int x, int y) {
        // Use correct variable name
        if (x == 100 && y == 100) {  // Use && for logical AND
            this.message = "Snake ate its body!"; // Clear and concise message
//            Snake.setErrorKind("Snake ate its body!");
        } else {
            this.message = "Snake went out of bounds!"; // Descriptive message
//            Snake.setErrorKind("Snake ate its body!");
        }
    }

    @Override
    public String getMessage() {
        return message;
    }
}
