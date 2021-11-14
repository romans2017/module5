package ua.petstore.client;

import ua.petstore.client.console.InputConsole;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args ) {
        InputConsole inputConsole = InputConsole.getInstance();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            inputConsole.processInput(scanner.nextLine());
        }
    }
}
