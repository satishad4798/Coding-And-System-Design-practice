package lld.design.patterns.decoratorPattern.factory.factory;

public class ShapeFactory {
    public Shape getShape(String shape) {

        switch (shape) {
            case "circle":
                return new Circle();
            case "rectangle":
                return new Rectangle();
            default:
                return null;

        }

    }
}
