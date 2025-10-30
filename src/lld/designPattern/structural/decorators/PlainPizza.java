package lld.designPattern.structural.decorators;

public class PlainPizza implements Pizza {
    @Override
    public String getDescription() {
        return " plain currentPizza ";
    }

    @Override
    public int getCost() {
        return 100;
    }
}
