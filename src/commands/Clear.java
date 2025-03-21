package commands;

import managers.CollectionManager;
import utility.Console;

/**
 * Команда {@code Clear} предназначена для очистки коллекции объектов.
 *
 * <p>При выполнении команды вызывается метод {@code clear()} у менеджера коллекции.
 * После успешного выполнения выводится сообщение "Коллекция очищена." в консоль.</p>
 *
 * @version 1.0
 */
public class Clear extends Command {

    private final CollectionManager collectionManager;

    /**
     * Конструктор команды {@code Clear}.
     *
     * @param collectionManager менеджер коллекции.
     */
    public Clear(final CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду очистки коллекции.
     *
     * <p>Вызывает метод {@code clear()} у менеджера коллекции и выводит сообщение
     * "Коллекция очищена." в консоль.</p>
     *
     * @param args    аргументы команды (не используются).
     * @param console объект консоли для вывода результата.
     */
    @Override
    public void executeCommand(String args, Console console) {
        collectionManager.clear();
        console.println("Коллекция очищена.");
    }
}
