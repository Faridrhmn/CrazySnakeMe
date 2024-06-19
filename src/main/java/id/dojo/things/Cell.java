package id.dojo.things;

public class Cell extends Thing{
    private final int row;
    private final int col;
    private Thing thing;

    public Cell(String name, String appearance, int row, int col) {
        super(name, appearance);
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Thing getThing() {
        return thing;
    }

    public void addThing(Thing thing) {
        this.thing = thing;
    }


    public void removeThing() {
        thing = null;
    }

    public void displayCell(){
        if (thing != null) {
            System.out.print(thing.getAppearance());
        } else {
            System.out.print("  ");
        }
    }

    //EOC
}
