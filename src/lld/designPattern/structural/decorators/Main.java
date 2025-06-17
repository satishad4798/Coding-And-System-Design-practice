package lld.designPattern.structural.decorators;

import lld.designPattern.structural.decorators.decorator.Cheese;
import lld.designPattern.structural.decorators.decorator.Mushroom;
import lld.designPattern.structural.decorators.decorator.Pepperoni;

public class Main {


    public static void main(String[] args) {

        //order basic pizza pizza
        Pizza pizza = new PlainPizza();
        System.out.println("your " + pizza.getDescription() + "pizza and cost is " + pizza.getCost());

        //order pizza with cheese
        Pizza cheesePizza = new Cheese(new PlainPizza());
        System.out.println("your " + cheesePizza.getDescription() + "pizza and cost is " + cheesePizza.getCost());


        //order pizza with pepperoni and cheese
        Pizza ch = new Pepperoni(new Cheese(new Mushroom(new Cheese((new PlainPizza())))));
        System.out.println("your " + ch.getDescription() + "pizza and cost is " + ch.getCost());


    }

}
