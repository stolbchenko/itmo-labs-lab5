package commands;

import managers.AskManager;
import managers.CollectionManager;
import models.HumanBeing;
import utility.Console;

/**
 * Команда {@code add} добавляет новый объект {@link HumanBeing} в коллекцию.
 *
 * <p>При выполнении команды пользователь пошагово вводит поля нового объекта.
 * Если команда выполняется в режиме интерактивного ввода или из скрипта,
 * ввод производится с подробными приглашениями. Если пользователь вводит
 * "exit" на любом этапе, операция прерывается.
 * </p>
 *
 * @author Ilya Stolbchenko
 * @version 1.0
 */
public class Add extends Command {

    private final CollectionManager collectionManager;

    /**
     * Конструктор команды {@code add}.
     */
    public Add(final CollectionManager collectionManager) {
        super("add", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду {@code add}, запрашивая у пользователя данные для создания
     * объекта {@link HumanBeing} с помощью {@link AskManager}. Если пользователь вводит
     * "exit" на любом этапе, операция прерывается.
     *
     * @param args              аргументы команды (не используются).
     * @param console           объект консоли для взаимодействия с пользователем.
     */
    @Override
    public void executeCommand(final String args, final Console console) {
        console.println("Добавление нового объекта HumanBeing. "
                + "Для выхода введите 'exit' при любом запросе.");
        try {
            final Long id = collectionManager.generateId();
            final HumanBeing newHuman = AskManager.askHumanBeing(console, id);
            if (newHuman != null) {
                collectionManager.add(newHuman);
                console.println("Объект успешно добавлен в коллекцию!");
            } else {
                console.printError("Объект не был создан.");
            }
        } catch (final AskManager.AskBreak ex) {
            console.printError("Добавление нового объекта прервано пользователем.");
        }
    }
}
