package id.dojo.things;

public class Thing {
    private String name;
    private String appearance;

    public Thing(String name, String appearance) {
        this.name = name;
        this.appearance = appearance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    //EOC
}
