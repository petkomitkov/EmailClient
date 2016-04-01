package com.company;

import java.util.Scanner;

/**
 * Created by Petko on 3/29/2016.
 */
public class Client {

    public static boolean readInput() {

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter a command(type help for commands): ");
        String nextCommand = reader.next();

        switch (nextCommand) {
            case "exit":
                return false;
            case "send":
                System.out.println("SENDING EMAIL!");
                EmailHelper.sendEmail();
                break;
            case "last":
                System.out.println("SHOWING EMAIL");
                EmailHelper.showLastEmail();
                break;
            case "help":
                showHelp();
                break;
            default:
                System.out.println("Invalid command");
                break;
        }
        return true;
    }

    private static void showHelp() {
        System.out.println("exit - exits the command line");
        System.out.println("last - shows last send email");
        System.out.println("send - sends an email using only Gmail account");
    }
}
