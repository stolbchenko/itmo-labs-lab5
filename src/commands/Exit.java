package commands;

import java.io.IOException;
import managers.CollectionManager;
import managers.DumpManager;
import utility.Console;

/**
 * Команда {@code Exit} завершает выполнение программы без сохранения коллекции.
 *
 * <p>При выполнении команды {@code exit} устанавливается флаг,
 * отключающий сохранение коллекции в shutdown hook,
 * и затем происходит явное завершение работы.
 * </p>
 *
 * @author Ilya Stolbchenko
 * @version 1.0
 */
public class Exit extends Command {

    /**
     * Флаг, определяющий, сохранять ли коллекцию при завершении работы.
     */
    public static boolean saveOnExit = true;

    /**
     * Статическое поле для хранения shutdown hook.
     */
    private static Thread shutdownHook;

    /**
     * Конструктор команды {@code Exit}.
     */
    public Exit() {
        super("exit", "завершить программу (без сохранения коллекции)");
    }

    /**
     * Регистрирует shutdown hook, который сохраняет коллекцию при аварийном завершении работы
     * (например, при нажатии CTRL-C/CTRL-D), если флаг {@code saveOnExit} равен {@code true}.
     *
     * @param collectionManager менеджер коллекции
     * @param console           объект консоли для вывода сообщений
     */
    public static void registerShutdownHook(final CollectionManager collectionManager,
                                            final Console console,
                                            final DumpManager dumpManager) {
        shutdownHook = new Thread(() -> {
            if (saveOnExit) {
                console.println(" Сохранение коллекции при завершении работы...");
                try {
                    dumpManager.saveCollection(collectionManager.getCollection());
                } catch (final IOException e) {
                    console.printError("Ошибка сохранения коллекции: " + e.getMessage());
                }
            }
        });
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }

    /**
     * Выполняет команду {@code exit}. При явном вызове команды установка флага
     * {@code saveOnExit} в {@code false} отключает сохранение коллекции в shutdown hook,
     * после чего hook удаляется и программа завершается.
     *
     * @param args              аргументы команды (не используются)
     * @param console           объект консоли для вывода сообщений
     */
    @Override
    public void executeCommand(final String args, final Console console) {
        saveOnExit = false;
        if (shutdownHook != null) {
            try {
                Runtime.getRuntime().removeShutdownHook(shutdownHook);
            } catch (final IllegalStateException e) {
                console.printError("Ошибка при удалении shutdown hook: " + e.getMessage());
            }
        }
        console.println("Программа завершилась без сохранения коллекции.");
        System.exit(0);
    }
}
