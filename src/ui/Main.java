package ui;

import model.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

import exception.QueueException;
import exception.StackException;

public class Main {

    private Controller c;
    private Scanner sc;
    private SimpleDateFormat format;

    public Main() {
        this.sc = new Scanner(System.in);
        this.c = new Controller();
        this.format = new SimpleDateFormat("dd/MM/yyyy");
    }

    public static void main(String[] args) {
        Main m = new Main();
        int option;

        do {
            option = m.showMenuAndGetOption();
            m.answerOption(option);
        } while (option != 0);
    }

    public int showMenuAndGetOption() {
        int input;
        try {
            System.out.print("\nApplication menu, type in an option\n" +
                    "(1) Register activity\n" +
                    "(2) Modify activity info\n" +
                    "(3) Delete activity\n" + // Option for deleting an activity
                    "(4) View activities list\n" +
                    "(0) Exit\n:");
            input = sc.nextInt();
            sc.nextLine();
            return input;
        } catch (InputMismatchException e) {
            sc.nextLine();
            return -1;
        }
    }

    public void answerOption(int userOption) {
        switch (userOption) {
            case 1:
                registerActivity();
                break;
            case 2:

                break;
            case 3:
                removeActivity();
                break;
            case 4:

                break;
            case 0:
                System.out.println("Program ends");
                break;
            default:
                System.out.println("Invalid option. Please, try again");
                break;
        }
    }

    public void registerActivity() {
        System.out.println("Please, type in the activity title");
        String title = sc.nextLine();

        System.out.println("Please, type in the activity description");
        String description = sc.nextLine();

        Calendar deadLine = readDate("Please, type in the activity deadline");

        System.out.println("Please, type (1) if it is priority or (2) otherwise");
        int priorityOption = sc.nextInt();

        System.out.println("Please, type (1) if it is a task or (2) if it is a reminder");
        int typeOption = sc.nextInt();

        System.out.println(c.registerActivity(title, description, deadLine, priorityOption, typeOption));
    }

    public void removeActivity() {
        System.out.println("Please, type in the title of the activity you want to remove:");
        String title = sc.nextLine();

        try {
            String message = c.removeActivity(title);
            System.out.println(message);
        } catch (StackException | QueueException e) {
            System.out.println("An error occurred while removing the activity: " + e.getMessage());
        }
    }

    public Calendar readDate(String message) {
        Calendar calendarTime = Calendar.getInstance();
        format.setLenient(false);

        String date = "";
        boolean validDate = false;

        while (!validDate) {
            System.out.print("\nThe date must follow the format: dd/MM/yyyy\n" + message);
            date = sc.nextLine();

            try {
                calendarTime.setTime(format.parse(date));
                validDate = true;
            } catch (ParseException error) {
                System.out.println("Invalid date format. Please try again.");
            }
        }

        return calendarTime;
    }
}