package id.dojo.things;

public class Wall extends Thing {
    private int row;
    private int column;

    public Wall(int row, int column) {
        super("Wall", " *");
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
