package commands;

import managers.CollectionManager;
import models.HumanBeing;
import utility.Console;

/**
 * Команда {@code MinByCoordinates} выводит любой объект из коллекции,
 * значение поля {@code coordinates} которого является минимальным.
 *
 * <p>Если коллекция пуста, выводится сообщение "Коллекция пуста.".
 * </p>
 *
 * @author Ilya Stolbchenko
 * @version 1.0
 */
public class MinByCoordinates extends Command {

    private final CollectionManager collectionManager;

    /**
     * Конструктор команды {@code MinByCoordinates}.
     */
    public MinByCoordinates(final CollectionManager collectionManager) {
        super("min_by_coordinates", "вывести любой объект из коллекции, значение поля "
                + "coordinates которого является минимальным");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду {@code min_by_coordinates}.
     * Вызывает метод {@link CollectionManager#minByCoordinates()} у менеджера коллекции
     * и выводит результат в консоль.
     *
     * @param args              аргументы команды (не используются)
     * @param console           объект консоли для вывода результата
     */
    @Override
    public void executeCommand(String args, Console console) {
        final HumanBeing min = collectionManager.minByCoordinates();
        if (min == null) {
            console.println("Коллекция пуста.");
        } else {
            console.println("Объект с минимальными координатами:");
            console.println(min.toString());
        }
    }
}
