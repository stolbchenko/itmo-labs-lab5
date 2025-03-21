package utility;

/**
 * Интерфейс Console предоставляет методы для вывода сообщений и
 * чтения ввода.
 *
 * <p>Методы включают {@link #print(Object)},
 * {@link #println(Object)}, {@link #readln()} и
 * {@link #printError(Object)}.</p>
 *
 * @author
 * @version 1.0
 */
public interface Console {
    /**
     * Выводит объект в стандартный поток вывода.
     *
     * @param obj объект для вывода.
     */
    void print(Object obj);

    /**
     * Выводит объект в стандартный поток вывода с переходом на новую строку.
     *
     * @param obj объект для вывода.
     */
    void println(Object obj);

    /**
     * Считывает строку из входного потока.
     *
     * @return считанная строка.
     */
    String readln();

    /**
     * Выводит сообщение об ошибке в стандартный поток ошибок.
     *
     * @param obj объект или сообщение об ошибке.
     */
    void printError(Object obj);
}
