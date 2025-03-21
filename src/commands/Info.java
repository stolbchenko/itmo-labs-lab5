package commands;

import managers.CollectionManager;
import utility.Console;

/**
 * Команда {@code Info} выводит информацию о коллекции.
 *
 * <p>Команда выводит тип коллекции, дату инициализации, а также количество элементов.
 * </p>
 *
 * @author
 * @version 1.0
 */
public class Info extends Command {

    private final CollectionManager collectionManager;

    /**
     * Конструктор команды {@code Info}.
     */
    public Info(final CollectionManager collectionManager) {
        super("info",
                "вывести в стандартный поток вывода информацию о коллекции "
                        + "(тип, дата инициализации, количество элементов и т.д.)");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду {@code Info}, выводя информацию о коллекции.
     *
     * @param args              аргументы команды (не используются).
     * @param console           объект консоли для вывода информации.
     */
    @Override
    public void executeCommand(final String args, final Console console) {
        final String info = collectionManager.getInfo();
        console.println(info);
    }
}
