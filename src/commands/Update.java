package commands;

import managers.AskManager;
import managers.CollectionManager;
import models.HumanBeing;
import utility.Console;

/**
 * Команда {@code Update} обновляет значение элемента коллекции,
 * id которого равен заданному.
 *
 * <p>Сначала проверяется наличие объекта с заданным id, затем запрашиваются новые
 * значения полей с помощью {@link AskManager}. Поля id и creationDate неизменные.
 * </p>
 *
 * @author Ilya Stolbchenko
 * @version 1.0
 */
public class Update extends Command {

    private final CollectionManager collectionManager;

    /**
     * Конструктор команды {@code Update}.
     */
    public Update(final CollectionManager collectionManager) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду update: сначала проверяет наличие объекта с заданным id,
     * затем запрашивает у пользователя новые значения полей с помощью {@link AskManager}.
     * При этом поля {@code id} и {@code creationDate} остаются неизменными.
     *
     * @param args              аргументы команды (ожидается один аргумент – id).
     * @param console           объект консоли для взаимодействия с пользователем.
     */
    @Override
    public void executeCommand(final String args, final Console console) {
        if (args == null || args.trim().isEmpty()) {
            console.printError("Формат команды: update <id>");
            return;
        }
        final String[] tokens = args.trim().split("\\s+");
        if (tokens.length != 1) {
            console.printError("Неверный формат команды. Ожидается: update <id>");
            return;
        }
        final Long id;
        try {
            id = Long.parseLong(tokens[0]);
        } catch (NumberFormatException e) {
            console.printError("id должен быть целым числом.");
            return;
        }

        final HumanBeing oldHb = collectionManager.findById(id);
        if (oldHb == null) {
            console.printError("Элемент с id " + id + " не найден.");
            return;
        }

        console.println("Обновление элемента с id = " + id + ".");
        console.println("Для полей, которые не хотите менять, введите текущее значение.");
        console.println("Для выхода введите 'exit' на любом запросе.");

        try {
            final HumanBeing updatedTemp = AskManager.askHumanBeing(console, id);
            if (updatedTemp == null) {
                console.printError("Объект не был обновлён.");
                return;
            }
            final HumanBeing updated = new HumanBeing(
                    id,
                    updatedTemp.getName(),
                    updatedTemp.getCoordinates(),
                    oldHb.getCreationDate(),
                    updatedTemp.getRealHero(),
                    updatedTemp.getHasToothpick(),
                    updatedTemp.getImpactSpeed(),
                    updatedTemp.getMinutesOfWaiting(),
                    updatedTemp.getWeaponType(),
                    updatedTemp.getMood(),
                    updatedTemp.getCar()
            );
            final boolean success = collectionManager.updateById(id, updated);
            if (success) {
                console.println("Элемент с id " + id + " успешно обновлён.");
            } else {
                console.printError("Ошибка обновления элемента с id " + id + ".");
            }
        } catch (final AskManager.AskBreak ex) {
            console.printError("Обновление элемента прервано пользователем.");
        }
    }
}
