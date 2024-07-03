package id.dojo.things;
import id.dojo.model.Points;
import java.util.Random;

public class Fruit extends Thing{
    private Points position;
    Random random = new Random();

    public Fruit(Builder builder) {
        super(builder.getName(), builder.getAppearance());
        this.position = builder.getPosition();
    }

    public Points getPosition() {
        return position;
    }

    public static Builder getBuilder()
    {
        return new Builder();
    }

    public void setPosition(Points position) {
        this.position = position;
    }

    public void newPosition(Board board){
        int x, y;
        x = (int) (Math.random() * 22) + 3;
        y = (int) (Math.random() * 22) + 3;

        if (x == 0 || x == 1 || x == 25 || x == 24){
            x = 3;
        }

        if (y == 0 || y == 1 || y == 25 || y == 24){
            y = 3;
        }

        position = new Points(x, y);
        board.putObject(position, board);
    }

    public static class Builder{
        private String name;
        private String appearance;
        private int Xpos, Ypos;

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setAppearance(String appearance){
            this.appearance = appearance;
            return this;
        }

        public Fruit build(){
            return new Fruit(this);
        }

        public String getName() {
            return name;
        }

        public String getAppearance() {
            return appearance;
        }

        public Points getPosition() {
            return new Points(Xpos, Ypos);
        }

        public Builder setPosition(int Xpos, int Ypos) {
            this.Xpos = Xpos;
            this.Ypos = Ypos;
            return this;
        }
        //End of Builder
    }
}