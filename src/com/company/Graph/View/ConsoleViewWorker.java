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
        System.out.println("1. Загрузить граф ( талица смежности )");
        System.out.println("2. Загрузить граф ( списки смежности )");
        System.out.println("3. Сохранить граф в файл( таблица смежности )");
        System.out.println("4. Загрузить граф в файл( списки смежности )");
        System.out.println("5. Создать пустой граф");
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

    public static String getFileName() {
        System.out.print("Введите имя файла: ");
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

    public static void printLine(String line) {
        System.out.println(line);
    }

    public static void printDelimiter() {
        System.out.println("--------------------");
    }
}
