package commands;

import managers.CollectionManager;
import utility.Console;

/**
 * Команда {@code AverageOfImpactSpeed} выводит среднее значение поля
 * {@code impactSpeed} для всех элементов коллекции.
 *
 * <p>Команда вызывает метод
 * {@link CollectionManager#averageOfImpactSpeed()} менеджера коллекции
 * и выводит результат в консоль.
 * </p>
 *
 * @author Ilya Stolbchenko
 * @version 1.0
 */
public class AverageOfImpactSpeed extends Command {

    private final CollectionManager collectionManager;

    /**
     * Конструктор команды {@code AverageOfImpactSpeed}.
     */
    public AverageOfImpactSpeed(final CollectionManager collectionManager) {
        super("average_of_impact_speed",
                "вывести среднее значение поля impactSpeed для всех элементов коллекции");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду average_of_impact_speed.
     *
     * <p>Метод получает среднее значение {@code impactSpeed} из менеджера коллекции
     * и выводит его в консоль.
     * </p>
     *
     * @param args              аргументы команды (не используются).
     * @param console           объект консоли для вывода результата.
     */
    @Override
    public void executeCommand(String args, Console console) {
        final double average = collectionManager.averageOfImpactSpeed();
        console.println("Среднее значение impactSpeed: " + average);
    }
}
