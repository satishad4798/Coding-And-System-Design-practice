package lld.designPattern.structural.decorators;

import lld.designPattern.structural.decorators.decorator.Cheese;
import lld.designPattern.structural.decorators.decorator.Mushroom;
import lld.designPattern.structural.decorators.decorator.Pepperoni;

public class Main {


    public static void main(String[] args) {

        //order basic currentPizza currentPizza
        Pizza pizza = new PlainPizza();
        System.out.println("your " + pizza.getDescription() + "currentPizza and cost is " + pizza.getCost());

        //order currentPizza with cheese
        Pizza cheesePizza = new Cheese(new PlainPizza());
        System.out.println("your " + cheesePizza.getDescription() + "currentPizza and cost is " + cheesePizza.getCost());


        //order currentPizza with pepperoni and cheese
        Pizza ch = new Pepperoni(new Cheese(new Mushroom(new Cheese((new PlainPizza())))));
        System.out.println("your " + ch.getDescription() + "currentPizza and cost is " + ch.getCost());


    }

}
