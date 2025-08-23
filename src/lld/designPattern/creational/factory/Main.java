package lld.designPattern.creational.factory;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ShapeFactory shapeFactoryFactory = new ShapeFactory();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the your choice");
        String userOption = scanner.nextLine();

        Shape shape = shapeFactoryFactory.getShape(userOption);
        System.out.println(shape.draw());


    }
}
