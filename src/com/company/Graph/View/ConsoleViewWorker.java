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
        System.out.println("1. Загрузить граф ( матрица смежности )");
        System.out.println("2. Загрузить граф ( списки смежности )");
    }

    public static int getGeneralAction() {
        System.out.print("Введите пункт меню: ");
        return scanner.nextInt();
    }

    public static String getPathToMatrixFile() {
        System.out.print("Введите путь до таблицы смежности: ");
        return scanner.next();
    }

    public static String getPathToListsFile() {
        System.out.print("Введите путь до списков смежности: ");
        return scanner.next();
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printMatrixChar(int i) {
        System.out.print(i);
    }

    public static void printNewLine() {
        System.out.println(" ");
    }

    public static void printSuccessMessage() {
        System.out.println("Операция выполнена успешно!");
    }
}
