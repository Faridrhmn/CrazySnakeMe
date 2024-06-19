package id.dojo.things;
import id.dojo.model.Points;

public interface AnimalBehavior {

    Points checkForward();
    void stepForward(Board board);

//    Points checkLeft();
    void turnLeft(Board board);

//    Points checkRight();
    void turnRight(Board board);
}
