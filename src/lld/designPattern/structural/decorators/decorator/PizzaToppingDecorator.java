package lld.designPattern.structural.decorators.decorator;

import lld.designPattern.structural.decorators.Pizza;

public abstract class PizzaToppingDecorator implements Pizza {

    Pizza pizza;

    public PizzaToppingDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public int getCost() {
        return pizza.getCost();
    }

    @Override
    public String getDescription() {
        return pizza.getDescription();
    }
}
