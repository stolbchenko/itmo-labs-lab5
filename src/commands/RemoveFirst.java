package commands;

import managers.CollectionManager;
import utility.Console;

/**
 * Команда RemoveFirst предназначена для удаления первого элемента коллекции.
 *
 * <p>При выполнении команды вызывается метод {@code removeFirst()} у менеджера коллекции.
 * Если удаление прошло успешно, в консоль выводится сообщение об успехе,
 * иначе – сообщение об ошибке (если коллекция пуста).
 * </p>
 *
 * @author Ilya Stolbchenko
 * @version 1.0
 */
public class RemoveFirst extends Command {

    private final CollectionManager collectionManager;

    /**
     * Конструктор команды RemoveFirst.
     */
    public RemoveFirst(final CollectionManager collectionManager) {
        super("remove_first", "удалить первый элемент из коллекции");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду remove_first.
     *
     * <p>Вызывает метод {@code removeFirst()} у менеджера коллекции.
     * Если метод возвращает {@code true}, выводится сообщение об успешном удалении,
     * иначе – сообщение о том, что коллекция пуста.
     * </p>
     *
     * @param args              аргументы команды (не используются).
     * @param console           объект консоли для вывода результатов.
     */
    @Override
    public void executeCommand(String args, Console console) {
        final boolean removed = collectionManager.removeFirst();
        if (removed) {
            console.println("Первый элемент успешно удалён.");
        } else {
            console.printError("Коллекция пуста, нечего удалять.");
        }
    }
}
