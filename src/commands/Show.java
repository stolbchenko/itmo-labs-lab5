package commands;

import java.util.Stack;
import managers.CollectionManager;
import models.HumanBeing;
import utility.Console;

/**
 * Команда {@code Show} выводит все элементы коллекции в строковом представлении.
 *
 * <p>Если коллекция пуста, выводится сообщение "Коллекция пуста.".
 * Иначе для каждого объекта в коллекции вызывается метод
 * {@code toString()} и его результат выводится в консоль.
 * </p>
 *
 * @author Ilya Stolbchenko
 * @version 1.0
 */
public class Show extends Command {

    private final CollectionManager collectionManager;

    /**
     * Конструктор команды {@code Show}.
     */
    public Show(final CollectionManager collectionManager) {
        super("show",
                "вывести в поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду show.
     *
     * <p>Метод получает коллекцию объектов через менеджер коллекции и выводит
     * каждый элемент в консоль. Если коллекция пуста, выводится сообщение
     * "Коллекция пуста.".
     * </p>
     *
     * @param args              аргументы команды (не используются).
     * @param console           объект консоли для вывода информации пользователю.
     */
    @Override
    public void executeCommand(String args, Console console) {
        final Stack<HumanBeing> collection = collectionManager.getCollection();
        if (collection.isEmpty()) {
            console.println("Коллекция пуста.");
        } else {
            console.println("Элементы коллекции:");
            for (HumanBeing hb : collection) {
                console.println(hb.toString());
            }
        }
    }
}
