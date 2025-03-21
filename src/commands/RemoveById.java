package commands;

import managers.AskManager;
import managers.CollectionManager;
import utility.Console;

/**
 * Команда {@code RemoveById} удаляет элемент из коллекции по его id.
 *
 * <p>Ожидается один аргумент — идентификатор элемента (тип long). Если аргумент не
 * передан или имеет неверный формат, выводится сообщение об ошибке. Если элемент
 * найден и удалён, выводится сообщение об успехе, иначе сообщается, что элемент не найден.
 * </p>
 *
 * @author Ilya Stolbchenko
 * @version 1.0
 */
public class RemoveById extends Command {

    private final CollectionManager collectionManager;

    /**
     * Конструктор команды {@code RemoveById}.
     */
    public RemoveById(final CollectionManager collectionManager) {
        super("remove_by_id", "удалить элемент из коллекции по его id");
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду remove_by_id.
     *
     * <p>Метод проверяет наличие аргумента (идентификатора), пытается преобразовать его
     * к типу long, и затем вызывает метод {@link CollectionManager#removeById(Long)} для
     * удаления элемента. В случае успеха выводится сообщение об удалении, иначе —
     * сообщение о том, что элемент не найден.
     * </p>
     *
     * @param args              аргументы команды (ожидается один аргумент — id).
     * @param console           объект консоли для взаимодействия с пользователем.
     */
    @Override
    public void executeCommand(String args, Console console) {
        final Long id = AskManager.parseLong(
                args,
                console,
                "remove_by_id <id>",
                "id"
        );
        if (id == null) {
            // Ошибка парсинга уже выведена в AskManager, прерываем выполнение команды
            return;
        }
        final boolean removed = collectionManager.removeById(id);
        if (removed) {
            console.println("Элемент с id " + id + " успешно удалён из коллекции.");
        } else {
            console.printError("Объект с id " + id + " не найден в коллекции.");
        }
    }
}
