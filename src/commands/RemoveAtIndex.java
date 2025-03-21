package commands;

import managers.AskManager;
import managers.CollectionManager;
import utility.Console;

/**
 * Команда {@code RemoveAtIndex} удаляет элемент коллекции по заданному индексу.
 *
 * <p>Если индекс корректен, элемент удаляется, иначе выводится сообщение об ошибке.</p>
 *
 * @author Ilya Stolbchenko
 * @version 1.0
 */
public class RemoveAtIndex extends Command {

    private final CollectionManager collectionManager;

    /**
     * Конструктор команды {@code RemoveAtIndex}.
     */
    public RemoveAtIndex(final CollectionManager collecionManager) {
        super("remove_at",
                "удалить элемент, находящийся в заданной позиции коллекции (index)");
        this.collectionManager = collecionManager;
    }

    /**
     * Выполняет команду remove_at.
     * Ожидается один аргумент — индекс элемента, который нужно удалить.
     * Если индекс корректен и элемент удалён, выводится сообщение об успехе,
     * иначе выводится сообщение об ошибке.
     *
     * @param args              аргументы команды (ожидается: remove_at &lt;index&gt;).
     * @param console           объект консоли для вывода сообщений.
     */
    @Override
    public void executeCommand(final String args, final Console console) {
        final Integer index = AskManager.parseInt(
                args,
                console,
                "remove_at <index>",
                "index"
        );
        if (index == null) {
            return;
        }
        final boolean removed = collectionManager.removeAt(index);
        if (removed) {
            console.println("Элемент на позиции " + index + " успешно удалён.");
        } else {
            console.printError("Удаление не выполнено. Возможно, индекс " + index
                    + " выходит за пределы коллекции.");
        }
    }
}
