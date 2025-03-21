package utility;

import java.util.Scanner;

/**
 * Класс {@code StandartConsole} реализует интерфейс {@link Console} и
 * предоставляет стандартные методы для ввода и вывода через консоль.
 *
 * <p>Используются два сканера: {@code defScan} для стандартного ввода и
 * {@code fileScan} для ввода из файла (если он установлен).</p>
 *
 * @author Ilya Stolbchenko
 * @version 1.0
 */
public class StandartConsole implements Console {
    private static final String prompt = "$ ";
    private static Scanner fileScan = null;
    private static Scanner defScan = new Scanner(System.in);

    @Override
    public void print(Object obj) {
        System.out.print(obj);
    }

    @Override
    public void println(Object obj) {
        System.out.println(obj);
    }

    @Override
    public String readln() {
        System.out.print(prompt);
        if (fileScan != null) {
            if (fileScan.hasNextLine()) {
                return fileScan.nextLine();
            } else {
                return null;
            }
        } else {
            if (defScan.hasNextLine()) {
                return defScan.nextLine();
            } else {
                return null;
            }
        }
    }

    @Override
    public void printError(Object obj) {
        System.err.println("Error: " + obj);
    }
}
