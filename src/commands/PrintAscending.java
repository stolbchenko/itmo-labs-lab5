package commands;

import java.util.List;
import managers.CollectionManager;
import models.HumanBeing;
import utility.Console;

/**
 * Команда {@code PrintAscending} выводит элементы коллекции в порядке
 * возрастания.
 *
 * <p>Команда получает отсортированный список объектов из менеджера коллекции и
 * выводит их в консоль. Если коллекция пуста, выводится сообщение
 * "Коллекция пуста.".
 * </p>
 *
 * @author Ilya Stolbchenko
 * @version 1.0
 */
public class PrintAscending extends Command {

    private final CollectionManager collectionManager;

    /**
     * Конструктор команды {@code PrintAscending}.
     */
    public PrintAscending(final CollectionManager collectionManager) {
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду print_ascending.
     * Получает отсортированный список объектов из менеджера коллекции
     * и выводит их в порядке возрастания.
     *
     * @param args              аргументы команды (не используются).
     * @param console           объект консоли для вывода результата.
     */
    @Override
    public void executeCommand(String args, Console console) {
        final List<HumanBeing> sortedList = collectionManager.getSortedCollection();
        if (sortedList.isEmpty()) {
            console.println("Коллекция пуста.");
        } else {
            console.println("Элементы коллекции в порядке возрастания:");
            for (HumanBeing hb : sortedList) {
                console.println(hb.toString());
            }
        }
    }
}
