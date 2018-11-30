package com.company.Graph.View;

import java.util.Scanner;

public class ConsoleViewWorker {
    private ConsoleViewWorker() {}
    private static Scanner scanner;
    static  {
        scanner = new Scanner(System.in);
    }

    public static void printFirstMenu() {
        System.out.println("Добро пожаловать в программу по работе с графами.");
        System.out.println("Меню:");
        System.out.println("1. Загрузить граф");
    }

    public static int getGeneralAction() {
        System.out.print("Введите пункт меню: ");
        return scanner.nextInt();
    }

    public static String getPathToFile() {
        System.out.print("Введите путь до таблицы смежности: ");
        return scanner.next();
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }
}
