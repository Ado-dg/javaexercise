package main.java;

import java.util.Scanner;

public class Starter {
	/**
	 * @param args
	 */
	public static void main(String[] args) {

        Application app = new Application(new Scanner(System.in));
        app.run(args);
    }
}
