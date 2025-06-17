package lld.designPattern.structural.decorators.decorator;

import lld.designPattern.structural.decorators.Pizza;

public class Pepperoni extends PizzaToppingDecorator {

    public Pepperoni(Pizza pizza) {
        super(pizza);
        System.out.println("Adding pepperoni ");
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " pepperoni ";
    }

    @Override
    public int getCost() {
        return super.getCost() + 10;
    }
}
