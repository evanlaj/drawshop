package drawshop.shapes.factories;

public enum DrawingType {

    HANDRAWN("Handdrawn Drawing"),
    PERFECT("Standard Drawing");

    private final String name;

    private DrawingType(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }
}
