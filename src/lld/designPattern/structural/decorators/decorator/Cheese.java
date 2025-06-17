package lld.designPattern.structural.decorators.decorator;

import lld.designPattern.structural.decorators.Pizza;

public class Cheese extends PizzaToppingDecorator {

    public Cheese(Pizza pizza) {
        super(pizza);
        System.out.println("Adding cheese");
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " cheese ";
    }

    @Override
    public int getCost() {
        return super.getCost() + 10;
    }
}
