package commands;

import java.util.List;
import managers.CommandManager;
import utility.Console;

/**
 * Команда {@code History} выводит последние 9 выполненных команд (без их аргументов).
 *
 * <p>Если история команд пуста, выводится сообщение "История команд пуста.".
 * После выполнения команда "history" добавляется в историю команд.</p>
 */
public class History extends Command {

    /**
     * Константа, определяющая максимальное количество команд, выводимых в истории.
     */
    private static final int HISTORY_LIMIT = 9;

    private final CommandManager commandManager;

    /**
     * Конструктор команды History.
     *
     * @param commandManager менеджер команд, предоставляющий историю команд.
     */
    public History(final CommandManager commandManager) {
        super("history", "вывести последние 9 команд (без их аргументов)");
        this.commandManager = commandManager;
    }

    /**
     * Выполняет команду history.
     *
     * <p>Метод получает историю команд из менеджера команд. Если история пуста,
     * выводит сообщение об этом. Иначе, выводит последние {@code HISTORY_LIMIT} команд.
     * После выполнения команда "history" добавляется в историю команд.</p>
     *
     * @param args    аргументы команды (не используются).
     * @param console объект консоли для вывода информации.
     */
    @Override
    public void executeCommand(final String args, final Console console) {
        final List<String> history = commandManager.getHistory();
        if (history.isEmpty()) {
            console.println("История команд пуста.");
        } else {
            console.println("История команд:");
            final int size = history.size();
            final int startIndex = (size > HISTORY_LIMIT) ? size - HISTORY_LIMIT : 0;
            for (int i = startIndex; i < size; i++) {
                final String commandLine = history.get(i);
                if (commandLine != null && !commandLine.trim().isEmpty()) {
                    final String[] tokens = commandLine.trim().split("\\s+");
                    if (tokens.length > 0) {
                        console.println(tokens[0]);
                    }
                }
            }
        }
        commandManager.addCommandToHistory("history");
    }
}
