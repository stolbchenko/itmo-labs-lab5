package managers;

import java.time.LocalDate;
import models.Car;
import models.Coordinates;
import models.HumanBeing;
import models.Mood;
import models.WeaponType;
import utility.Console;

/**
 * Класс {@code AskManager} предоставляет методы для пошагового запроса ввода данных с
 * консоли для создания объекта {@link models.HumanBeing}.
 *
 * <p>Каждый метод запрашивает конкретное поле, проверяет корректность ввода и повторяет
 * запрос при ошибке. Если пользователь вводит "exit", выбрасывается исключение
 * {@code AskBreak} для прекращения ввода.</p>
 *
 * @author Ilya Stolbchenko
 * @version 1.0
 */
public class AskManager {

    /**
     * Константа, задающая максимальное допустимое значение для поля y.
     */
    private static final double MAX_Y = 932.0;

    private static final String INTEGER_REGEX = "^[+-]?\\d+$";

    private static final int DECIMAL_BASE = 10;

    /**
     * Исключение, выбрасываемое, если пользователь хочет прервать ввод.
     */
    public static class AskBreak extends Exception {

    }

    /**
     * Запрашивает у пользователя все необходимые данные для создания объекта
     * {@code HumanBeing}.
     *
     * <p>Метод последовательно запрашивает:
     * <ul>
     *   <li>Имя (String). Если имя пустое, запрос повторяется.</li>
     *   <li>Координаты через метод {@code askCoordinates(console)}.</li>
     *   <li>Булевое значение realHero
     *   через {@code askBoolean("realHero (boolean): ", console)}.</li>
     *   <li>Булевое значение hasToothpick
     *   через {@code askBoolean("hasToothpick (boolean): ", console)}.</li>
     *   <li>Целочисленное значение impactSpeed (long) через
     *       {@code askLong("impactSpeed (long): ", console, "impactSpeed")}.</li>
     *   <li>Вещественное значение minutesOfWaiting (float) через
     *       {@code askFloat("minutesOfWaiting (float): ", console, "minutesOfWaiting")}.</li>
     *   <li>Значения для WeaponType, Mood и Car через соответствующие методы.
     *       (Остальные методы можно оставить без изменений.)</li>
     * </ul>
     * Значение поля {@code id} задаётся извне, а {@code creationDate} устанавливается как
     * текущая дата.
     *
     * @param console объект консоли для ввода и вывода.
     * @param id      уникальный идентификатор для создаваемого объекта.
     * @return        созданный объект {@code HumanBeing} на основе введённых данных.
     * @throws AskManager.AskBreak если пользователь вводит "exit" на любом этапе ввода.
     */
    public static HumanBeing askHumanBeing(final Console console, final Long id)
            throws AskBreak {
        String name;
        while (true) {
            name = readLineOrExit("name (String; поле не может быть null): ", console);
            if (name.isEmpty()) {
                console.printError("Имя не может быть пустым. Повторите ввод.");
            } else {
                break;
            }
        }
        final Coordinates coordinates = askCoordinates(console);
        final boolean realHero = askBoolean("realHero (boolean): ", console);
        final boolean hasToothpick = askBoolean("hasToothpick (boolean): ", console);
        final long impactSpeed = askLong("impactSpeed (long): ", console, "impactSpeed");
        final float minutesOfWaiting = askFloat("minutesOfWaiting (float): ", console,
                "minutesOfWaiting");
        final WeaponType weapon = askWeaponType(console);
        final Mood mood = askMood(console);
        final Car car = askCar(console);
        return new HumanBeing(id, name, coordinates, LocalDate.now(), realHero,
                hasToothpick, impactSpeed, minutesOfWaiting, weapon, mood, car);
    }

    /**
     * Выводит запрос и считывает строку от пользователя.
     *
     * <p>Если введено "exit" (без учета регистра), метод выбрасывает
     * исключение {@code AskBreak}. Если возвращается null, то строка
     * заменяется на пустую. Полученная строка обрезается.</p>
     *
     * @param prompt  Текст запроса для пользователя.
     * @param console Объект консоли для ввода и вывода.
     * @return        Считанная строка без ведущих и замыкающих пробелов.
     * @throws AskBreak Если пользователь вводит "exit".
     */
    public static String readLineOrExit(String prompt, Console console) throws AskBreak {
        console.print(prompt);
        String line = console.readln();
        if (line == null) {
            line = "";
        }
        line = line.trim();
        if (line.equalsIgnoreCase("exit")) {
            throw new AskBreak();
        }
        return line;
    }

    /**
     * Запрашивает булево значение от пользователя.
     *
     * <p>Метод выводит указанный запрос, считывает ответ и возвращает true,
     * если введено "true", или false, если введено "false". При ошибочном
     * вводе выводит сообщение и повторяет запрос.</p>
     *
     * @param prompt  Текст запроса для пользователя.
     * @param console Объект консоли для ввода/вывода.
     * @return        true, если пользователь ввёл "true", иначе false.
     * @throws AskBreak Если пользователь вводит "exit".
     */
    public static boolean askBoolean(String prompt, Console console) throws AskBreak {
        while (true) {
            final String line = readLineOrExit(prompt, console);
            if (line.equalsIgnoreCase("true")) {
                return true;
            } else if (line.equalsIgnoreCase("false")) {
                return false;
            } else {
                console.printError("Некорректное значение. Введите true или false.");
            }
        }
    }



    /**
     * Запрашивает у пользователя значение типа float.
     *
     * <p>Метод выводит заданный prompt, считывает ввод, заменяет запятые на точки и
     * проверяет корректность формата. При некорректном вводе запрос повторяется.
     * Если введено "exit", выбрасывается исключение {@code AskBreak}.
     * </p>
     *
     * @param prompt       Текст запроса для пользователя.
     * @param console      Объект консоли для ввода и вывода.
     * @param argumentName Имя аргумента (например, "minutesOfWaiting").
     * @return Значение типа float, полученное из ввода.
     * @throws AskBreak   Если пользователь вводит "exit".
     */
    public static float askFloat(String prompt, Console console,
                                 String argumentName) throws AskBreak {
        while (true) {
            console.print(prompt);
            final String line = console.readln().trim();
            if (line.equalsIgnoreCase("exit")) {
                throw new AskBreak();
            }
            final String normalized = line.contains(",") ? line.replace(',', '.') : line;
            if (normalized.isEmpty()) {
                console.printError("Значение " + argumentName
                        + " не может быть пустым. Повторите ввод.");
                continue;
            }
            int startIndex = 0;
            boolean negative = false;
            final char firstChar = normalized.charAt(0);
            if (firstChar == '-' || firstChar == '+') {
                negative = (firstChar == '-');
                startIndex = 1;
                if (normalized.length() == 1) {
                    console.printError("Неверный формат ввода " + argumentName
                            + ". Повторите ввод.");
                    continue;
                }
            }
            int dotCount = 0;
            boolean valid = true;
            for (int i = startIndex; i < normalized.length(); i++) {
                final char c = normalized.charAt(i);
                if (c == '.') {
                    dotCount++;
                    if (dotCount > 1) {
                        valid = false;
                        break;
                    }
                } else if (!Character.isDigit(c)) {
                    valid = false;
                    break;
                }
            }
            if (!valid) {
                console.printError("Неверный формат ввода " + argumentName
                        + ". Повторите ввод.");
                continue;
            }
            final float result = Float.parseFloat(normalized);
            return result;
        }
    }

    /**
     * Запрашивает у пользователя значение типа long.
     *
     * <p>Метод выводит заданный prompt, считывает ввод, проверяет корректность
     * формата и повторяет запрос, если ввод некорректен. Если введено "exit",
     * выбрасывается исключение {@code AskBreak}.
     * </p>
     *
     * @param prompt       Текст запроса для пользователя.
     * @param console      Объект консоли для ввода/вывода.
     * @param argumentName Имя аргумента (например, "impactSpeed").
     * @return Значение типа long, полученное из ввода.
     * @throws AskBreak   Если пользователь вводит "exit".
     */
    public static long askLong(String prompt, Console console, String argumentName)
            throws AskBreak {
        while (true) {
            final String line = readLineOrExit(prompt, console);
            if (line.isEmpty()) {
                console.printError("Значение " + argumentName
                        + " не может быть пустым. Повторите ввод.");
                continue;
            }
            int start = 0;
            boolean negative = false;
            final char first = line.charAt(0);
            if (first == '-' || first == '+') {
                negative = (first == '-');
                start = 1;
                if (line.length() == 1) {
                    console.printError("Неверный формат ввода для " + argumentName
                            + ". Повторите ввод.");
                    continue;
                }
            }
            boolean valid = true;
            long result = 0;
            for (int i = start; i < line.length(); i++) {
                final char c = line.charAt(i);
                if (!Character.isDigit(c)) {
                    valid = false;
                    break;
                }
                final int digit = c - '0';
                if (!negative) {
                    if (result > (Long.MAX_VALUE - digit) / DECIMAL_BASE) {
                        valid = false;
                        break;
                    }
                } else {
                    if (-result < (Long.MIN_VALUE + digit) / (double) DECIMAL_BASE) {
                        valid = false;
                        break;
                    }
                }
                result = result * DECIMAL_BASE + digit;
            }
            if (!valid) {
                console.printError("Неверный формат ввода для " + argumentName
                        + ". Повторите ввод.");
                continue;
            }
            return negative ? -result : result;
        }
    }

    /**
     * Парсит строку аргумента в число типа int.
     * Если формат некорректный или число выходит за пределы int, выводит сообщение
     * об ошибке и возвращает null.
     *
     * @param args           строка с аргументом.
     * @param console        объект консоли для вывода ошибок.
     * @param usageMessage   сообщение, описывающее корректный формат команды
     * @param argumentName   имя аргумента для вывода в сообщениях.
     * @return число типа int, если парсинг успешен, иначе null.
     */
    public static Integer parseInt(String args,
                                   Console console,
                                   String usageMessage,
                                   String argumentName) {
        if (args == null || args.trim().isEmpty()) {
            console.printError("Не указан " + argumentName + ". Формат команды: " + usageMessage);
            return null;
        }
        final String[] tokens = args.trim().split("\\s+");
        if (tokens.length != 1) {
            console.printError("Неверный формат команды. Ожидается: " + usageMessage);
            return null;
        }
        final String numberStr = tokens[0];
        if (!numberStr.matches(INTEGER_REGEX)) {
            console.printError(argumentName + " должен быть целым числом (тип int).");
            return null;
        }
        boolean negative = false;
        int i = 0;
        if (numberStr.startsWith("-")) {
            negative = true;
            i++;
        } else if (numberStr.startsWith("+")) {
            i++;
        }

        int result = 0;
        for (; i < numberStr.length(); i++) {
            final char c = numberStr.charAt(i);
            final int digit = c - '0';
            if (!negative) {
                if (result > (Integer.MAX_VALUE - digit) / DECIMAL_BASE) {
                    console.printError(argumentName + " выходит за пределы типа int.");
                    return null;
                }
            } else {
                if (-result < (Integer.MIN_VALUE + digit) / DECIMAL_BASE) {
                    console.printError(argumentName + " выходит за пределы типа int.");
                    return null;
                }
            }
            result = result * DECIMAL_BASE + digit;
        }
        if (negative) {
            result = -result;
        }
        return result;
    }

    /**
     * Парсит строку аргумента в число типа long.
     * Если формат некорректный или число выходит за пределы long, выводит сообщение
     * об ошибке и возвращает null.
     *
     * @param args           строка с аргументом.
     * @param console        объект консоли для вывода ошибок.
     * @param usageMessage   сообщение, описывающее корректный формат команды
     * @param argumentName   имя аргумента для вывода в сообщениях.
     * @return число типа long, если парсинг успешен, иначе null.
     */
    public static Long parseLong(String args,
                                 Console console,
                                 String usageMessage,
                                 String argumentName) {
        if (args == null || args.trim().isEmpty()) {
            console.printError("Не указан " + argumentName + ". Формат команды: " + usageMessage);
            return null;
        }
        final String[] tokens = args.trim().split("\\s+");
        if (tokens.length != 1) {
            console.printError("Неверный формат команды. Ожидается: " + usageMessage);
            return null;
        }
        final String numberStr = tokens[0];
        if (!numberStr.matches(INTEGER_REGEX)) {
            console.printError(argumentName + " должен быть целым числом (тип long).");
            return null;
        }
        boolean negative = false;
        int i = 0;
        if (numberStr.startsWith("-")) {
            negative = true;
            i++;
        } else if (numberStr.startsWith("+")) {
            i++;
        }
        long result = 0;
        for (; i < numberStr.length(); i++) {
            final char c = numberStr.charAt(i);
            final int digit = c - '0';
            if (negative) {
                if (-result < (Long.MIN_VALUE + digit) / DECIMAL_BASE) {
                    console.printError(argumentName + " выходит за пределы типа long.");
                    return null;
                }
            } else {
                if (result > (Long.MAX_VALUE - digit) / DECIMAL_BASE) {
                    console.printError(argumentName + " выходит за пределы типа long.");
                    return null;
                }
            }
            result = result * DECIMAL_BASE + digit;
        }
        if (negative) {
            result = -result;
        }

        return result;
    }


    /**
     * Запрашивает у пользователя координаты и возвращает объект {@code Coordinates}.
     *
     * <p>Поле {@code x} должно быть введено как Long и не может быть пустым.
     * Поле {@code y} должно быть введено как double, не может быть пустым и не должно превышать 932
     * Если пользователь вводит "exit", метод выбрасывает {@code AskBreak}.</p>
     *
     * @param console объект консоли для ввода и вывода.
     * @return объект {@code Coordinates} с введенными значениями.
     * @throws AskBreak если пользователь вводит "exit".
     */
    public static Coordinates askCoordinates(final Console console) throws AskBreak {
        Long x;
        while (true) {
            console.print("x (Long; не может быть null): ");
            final String line = console.readln().trim();
            if (line.equalsIgnoreCase("exit")) {
                throw new AskBreak();
            }
            if (line.isEmpty()) {
                console.printError("Значение x не может быть пустым. Повторите ввод.");
                continue;
            }
            int start = 0;
            final char first = line.charAt(0);
            if (first == '-' || first == '+') {
                if (line.length() == 1) {
                    console.printError("Некорректное значение для x. Введите целое число (Long).");
                    continue;
                }
                start = 1;
            }
            boolean valid = true;
            for (int i = start; i < line.length(); i++) {
                if (!Character.isDigit(line.charAt(i))) {
                    valid = false;
                    break;
                }
            }
            if (!valid) {
                console.printError("Некорректное значение для x. Введите целое число (Long).");
                continue;
            }
            x = Long.parseLong(line);
            break;
        }
        double y;
        while (true) {
            console.print("y (double; максимальное значение поля: 932): ");
            String line = console.readln().trim();
            if (line.equalsIgnoreCase("exit")) {
                throw new AskBreak();
            }
            if (line.isEmpty()) {
                console.printError("Значение y не может быть пустым. Повторите ввод.");
                continue;
            }
            if (line.contains(",")) {
                line = line.replace(',', '.');
            }
            int dotCount = 0;
            int start = 0;
            final char first = line.charAt(0);
            if (first == '-' || first == '+') {
                if (line.length() == 1) {
                    console.printError("Некорректное значение для y. Введите число (double).");
                    continue;
                }
                start = 1;
            }
            boolean valid = true;
            for (int i = start; i < line.length(); i++) {
                final char ch = line.charAt(i);
                if (ch == '.') {
                    dotCount++;
                    if (dotCount > 1) {
                        valid = false;
                        break;
                    }
                } else if (!Character.isDigit(ch)) {
                    valid = false;
                    break;
                }
            }
            if (!valid) {
                console.printError("Некорректное значение для y. Введите число (double).");
                continue;
            }
            y = Double.parseDouble(line);
            if (y > MAX_Y) {
                console.printError("Значение y не должно превышать 932. Повторите ввод.");
                continue;
            }
            break;
        }
        return new Coordinates(x, y);
    }

    /**
     * Запрашивает у пользователя данные для создания объекта {@code Car}.
     *
     * <p>Поле {@code name} может быть пустым, тогда возвращается {@code null}.
     * Поле {@code cool} (Boolean) не может быть пустым.
     * Если пользователь вводит "exit", метод выбрасывает {@code AskBreak}.</p>
     *
     * @param console объект консоли для ввода и вывода.
     * @return объект {@code Car} с введенными данными или {@code null}, если имя пустое.
     * @throws AskBreak если пользователь вводит "exit".
     */
    public static Car askCar(final Console console) throws AskBreak {
        console.print("Car Name (String): ");
        String name = console.readln().trim();
        if (name.equalsIgnoreCase("exit")) {
            throw new AskBreak();
        }
        if (name.isEmpty()) {
            name = null;
        }
        Boolean cool = null;
        while (cool == null) {
            console.print("Is Car Cool? (Boolean; поле не может быть null): ");
            final String line = console.readln().trim();
            if (line.equalsIgnoreCase("exit")) {
                throw new AskBreak();
            }
            if (line.isEmpty()) {
                console.printError("Значение не может быть пустым. Введите true или false.");
                continue;
            }
            if (line.equalsIgnoreCase("true")) {
                cool = true;
            } else if (line.equalsIgnoreCase("false")) {
                cool = false;
            } else {
                console.printError("Некорректное значение. Введите true или false.");
            }
        }
        return new Car(name, cool);
    }

    /**
     * Запрашивает у пользователя значение для поля {@code Mood}.
     *
     * <p>Пользователь может вводить либо порядковый номер, либо строковое значение.
     * Если ввод пустой, возвращается {@code null}.
     * Если пользователь вводит "exit", метод выбрасывает {@code AskBreak}.</p>
     *
     * @param console объект консоли для ввода и вывода.
     * @return значение типа {@code Mood} или {@code null}, если ввод пуст.
     * @throws AskBreak если пользователь вводит "exit".
     */
    public static Mood askMood(final Console console) throws AskBreak {
        while (true) {
            console.print("Mood (" + Mood.getNames() + "): ");
            final String line = console.readln().trim();
            if (line.equalsIgnoreCase("exit")) {
                throw new AskBreak();
            }
            if (line.isEmpty()) {
                return null;
            }
            boolean isNumber = true;
            for (int i = 0; i < line.length(); i++) {
                if (!Character.isDigit(line.charAt(i))) {
                    isNumber = false;
                    break;
                }
            }
            if (isNumber) {
                int num = 0;
                for (int i = 0; i < line.length(); i++) {
                    final int digit = line.charAt(i) - '0';
                    num = num * DECIMAL_BASE + digit;
                }
                final Mood[] values = Mood.values();
                if (num >= 1 && num <= values.length) {
                    return values[num - 1];
                } else {
                    console.printError("Неверный порядковый номер. Введите число от 1 до "
                            + values.length + ", либо значение enum.");
                    continue;
                }
            } else {
                final String allowed = Mood.getNames();
                final String[] allowedValues = allowed.split(",");
                boolean valid = false;
                for (String value : allowedValues) {
                    if (value.trim().equalsIgnoreCase(line)) {
                        valid = true;
                        break;
                    }
                }
                if (valid) {
                    return Mood.valueOf(line.toUpperCase());
                } else {
                    console.printError("Некорректное значение для Mood. Допустимые значения: "
                            + allowed + ". Повторите ввод или оставьте пустым для null.");
                }
            }
        }
    }

    /**
     * Запрашивает у пользователя значение для поля {@code WeaponType}.
     *
     * <p>Пользователь может вводить либо порядковый номер, либо строковое значение.
     * Если ввод пустой, возвращается {@code null}.
     * Если пользователь вводит "exit", метод выбрасывает {@code AskBreak}.</p>
     *
     * @param console объект консоли для ввода и вывода.
     * @return значение типа {@code WeaponType} или {@code null}, если ввод пуст.
     * @throws AskBreak если пользователь вводит "exit".
     */
    public static WeaponType askWeaponType(final Console console) throws AskBreak {
        while (true) {
            console.print("WeaponType (" + WeaponType.getNames() + "): ");
            final String line = console.readln().trim();
            if (line.equalsIgnoreCase("exit")) {
                throw new AskBreak();
            }
            if (line.isEmpty()) {
                return null;
            }
            boolean isNumber = true;
            for (int i = 0; i < line.length(); i++) {
                if (!Character.isDigit(line.charAt(i))) {
                    isNumber = false;
                    break;
                }
            }
            if (isNumber) {
                final int num = Integer.parseInt(line);
                final WeaponType[] values = WeaponType.values();
                if (num >= 1 && num <= values.length) {
                    return values[num - 1];
                } else {
                    console.printError("Неверный порядковый номер. Введите число от 1 до "
                            + values.length + ", либо значение enum.");
                    continue;
                }
            } else {
                final String[] allowed = WeaponType.getNames().split(",");
                boolean validEnum = false;
                for (String name : allowed) {
                    if (name.trim().equalsIgnoreCase(line)) {
                        validEnum = true;
                        break;
                    }
                }
                if (validEnum) {
                    return WeaponType.valueOf(line.toUpperCase());
                } else {
                    console.printError("Некорректное значение для WeaponType. Допустимые значения: "
                            + WeaponType.getNames() + ". Повторите ввод или оставьте пустым.");
                }
            }
        }
    }
}
