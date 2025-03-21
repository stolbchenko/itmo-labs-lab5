package commands;

import java.io.IOException;
import managers.CollectionManager;
import managers.DumpManager;
import utility.Console;

/**
 * Команда Save сохраняет коллекцию в XML-файл.
 *
 * <p>При выполнении команды вызывается метод сохранения коллекции из {@link CollectionManager}.
 * Если при сохранении возникают ошибки, они обрабатываются DumpManager'ом.
 * При успешном сохранении выводится сообщение об успешном завершении операции.
 * </p>
 *
 * @author Ilya Stolbchenko
 * @version 1.0
 */
public class Save extends Command {

    private final CollectionManager collectionManager;
    private final DumpManager dumpManager;

    /**
     * Конструктор команды Save.
     *
     * @param collectionManager менеджер коллекции, из которого берётся коллекция объектов.
     * @param dumpManager менеджер для сохранения коллекции в файл.
     */
    public Save(final CollectionManager collectionManager, final DumpManager dumpManager) {
        super("save", "сохранить коллекцию в файл");
        this.collectionManager = collectionManager;
        this.dumpManager = dumpManager;
    }

    /**
     * Выполняет команду save, вызывая метод {@code save()} у менеджера коллекции.
     *
     * <p>Если сохранение прошло успешно, в консоль выводится сообщение об успешном сохранении.
     * </p>
     *
     * @param args              аргументы команды (не используются)
     * @param console           объект консоли для вывода сообщения
     */
    @Override
    public void executeCommand(final String args, final Console console) {
        try {
            dumpManager.saveCollection(collectionManager.getCollection());
        } catch (final IOException e) {
            console.printError("Ошибка сохранения коллекции: " + e.getMessage());
        }
    }
}
