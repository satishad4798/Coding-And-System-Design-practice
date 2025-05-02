package systemDesign.decoratorPattern.decorators;

import systemDesign.decoratorPattern.Pizza;

public class Mushroom extends PizzaToppingDecorator {

    public Mushroom(Pizza pizza) {
        super(pizza);
        System.out.println("Adding mushroom");
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " mushroom ";
    }

    @Override
    public int getCost() {
        return super.getCost() + 10;
    }
}
