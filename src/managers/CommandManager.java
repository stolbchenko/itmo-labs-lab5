package managers;

import commands.Command;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import utility.Console;

/**
 * Класс CommandManager отвечает за регистрацию и выполнение команд.
 * Он хранит команды в виде отображения, где ключом является имя команды,
 * а значением — объект команды. Команды можно регистрировать с помощью метода
 * {@link #register(String, Command)}, а затем выполнять их с помощью метода
 * {@link #executeCommand(String, Console)}.
 *
 * @author Ilya Stolbchenko
 * @version 1.0
 */
public class CommandManager {

    /**
     * Максимальный размер истории команд (чтобы избежать «магического числа»).
     */
    private static final int HISTORY_LIMIT = 9;

    /**
     * Отображение для хранения зарегистрированных команд.
     */
    private final Map<String, Command> commands = new LinkedHashMap<>();

    /**
     * Хранит историю команд.
     */
    private final List<String> commandHistory = new ArrayList<>();

    /**
     * Регистрирует команду под указанным именем.
     *
     * @param commandName имя команды.
     * @param command объект команды, реализующий интерфейс
     *                {@link Command}.
     */
    public void register(String commandName, Command command) {
        commands.put(commandName, command);
    }

    /**
     * Возвращает отображение зарегистрированных команд.
     *
     * @return Map с именами команд и объектами команд.
     */
    public Map<String, Command> getCommands() {
        return commands;
    }

    /**
     * Выполняет команду, полученную в виде строки.
     * Строка разбивается на имя команды и аргументы.
     * Если строка пуста, выводится сообщение: "Введите команду.".
     * Если команда не зарегистрирована, выводится сообщение об ошибке;
     * иначе вызывается метод для выполнения команды.
     *
     * @param input строка с именем команды и её аргументами.
     * @param console объект консоли для ввода и вывода.
     */
    public void executeCommand(String input, Console console) {
        if (input == null || input.trim().isEmpty()) {
            console.println("Введите команду.");
            return;
        }
        final String[] parts = input.trim().split("\\s+", 2);
        final String commandName = parts[0];
        final String args = parts.length > 1 ? parts[1] : "";
        final Command command = commands.get(commandName);
        if (command == null) {
            console.printError("Неизвестная команда: " + commandName);
        } else {
            command.executeCommand(args, console);
            if (!commandName.equalsIgnoreCase("history")
                    && !commandName.equalsIgnoreCase("execute_script")) {
                addCommandToHistory(commandName);
            }
        }
    }

    /**
     * Возвращает историю последних {@value HISTORY_LIMIT} выполненных команд.
     *
     * @return список команд.
     */
    public List<String> getHistory() {
        return new ArrayList<>(commandHistory);
    }

    /**
     * Добавляет команду в историю.
     * Если количество команд превышает {@value HISTORY_LIMIT}, удаляет самую старую.
     *
     * @param command строка с выполненной командой.
     */
    public void addCommandToHistory(final String command) {
        commandHistory.add(command);
        if (commandHistory.size() > HISTORY_LIMIT) {
            commandHistory.remove(0);
        }
    }
}
