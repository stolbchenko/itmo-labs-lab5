package commands;

import java.util.Collection;
import utility.Console;

/**
 * Команда {@code Help} выводит справку по доступным командам.
 *
 * <p>Команда выводит список всех зарегистрированных команд и их описание.
 * </p>.
 *
 * @author Ilya Stolbchenko
 * @version 1.0
 */
public class Help extends Command {

    /**
     * Коллекция доступных команд.
     */
    private final Collection<Command> commands;

    /**
     * Конструктор команды {@code Help}.
     *
     * @param commands коллекция всех зарегистрированных команд для вывода справки.
     */
    public Help(final Collection<Command> commands) {
        super("help", "вывести справку по доступным командам");
        this.commands = commands;
    }

    /**
     * Выполняет команду {@code Help}, выводя в консоль список команд и их описание.
     *
     * @param args              аргументы команды (не используются).
     * @param console           объект консоли для вывода информации.
     */
    @Override
    public void executeCommand(final String args, final Console console) {
        console.println("Список доступных команд:");
        for (final Command cmd : commands) {
            console.println(cmd.getName() + " : " + cmd.getDescription());
        }
    }
}
