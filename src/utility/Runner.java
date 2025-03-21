package utility;

import managers.CollectionManager;
import managers.CommandManager;

/**
 * Класс {@code Runner} обеспечивает запуск интерактивного режима приложения.
 *
 * <p>Класс осуществляет чтение команд из консоли и передачу их менеджеру команд для
 * дальнейшего выполнения, а также управляет историей команд.</p>
 *
 * @author Ilya Stolbchenko
 * @version 1.0
 */
public class Runner {

    /**
     * Объект консоли для ввода и вывода данных.
     */
    private Console console;

    /**
     * Менеджер команд для регистрации и выполнения команд.
     */
    private final CommandManager commandManager;

    /**
     * Менеджер коллекции объектов.
     */
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса {@code Runner}.
     *
     * @param console           объект консоли для ввода и вывода данных.
     * @param commandManager    менеджер для регистрации и выполнения команд.
     * @param collectionManager менеджер коллекции объектов.
     */
    public Runner(Console console,
                  CommandManager commandManager,
                  CollectionManager collectionManager) {
        this.console = console;
        this.commandManager = commandManager;
        this.collectionManager = collectionManager;
    }

    /**
     * Запускает интерактивный режим приложения.
     *
     * <p>Метод читает команды из консоли, разбивает их на имя команды и аргументы,
     * затем передаёт их для выполнения через {@code CommandManager}. Если введённая команда
     * не равна "history", она добавляется в историю. Режим завершается при вводе команды
     * "exit" или при отсутствии пользовательского ввода.</p>
     */
    public void interactiveMode() {
        console.println("Type help if you don't know what to do.");
        while (true) {
            final String input = console.readln();
            if (input == null) { // Обработка Ctrl-D/Ctrl-C
                break;
            }
            final String[] userCommand = (input.trim() + " ").split(" ", 2);
            userCommand[1] = userCommand[1].trim();
            console.println("Введена команда: " + userCommand[0]);
            commandManager.executeCommand(input, console);
            if (userCommand[0].equalsIgnoreCase("exit")) {
                break;
            }
        }
    }
}
