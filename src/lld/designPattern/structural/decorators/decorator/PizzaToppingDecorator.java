package lld.designPattern.structural.decorators.decorator;

import lld.designPattern.structural.decorators.Pizza;

public abstract class PizzaToppingDecorator implements Pizza {

    Pizza currentPizza;

    public PizzaToppingDecorator(Pizza currentPizza) {
        this.currentPizza = currentPizza;
    }

    @Override
    public int getCost() {
        return currentPizza.getCost();
    }

    @Override
    public String getDescription() {
        return currentPizza.getDescription();
    }
}
