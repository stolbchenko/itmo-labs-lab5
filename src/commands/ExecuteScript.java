package commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import managers.CommandManager;
import utility.Console;

/**
 * Команда {@code ExecuteScript} считывает и исполняет команды из указанного файла.
 *
 * <p>Поддерживает вложенные вызовы скриптов без жесткого ограничения глубины.
 * Если обнаруживается рекурсия (один и тот же файл уже выполняется),
 * выполнение команды приостанавливается, и выводится сообщение об ошибке.
 * Пустые строки в скрипте пропускаются.</p>
 *
 * @version 1.0
 */
public class ExecuteScript extends Command {

    private static final Set<String> executingScripts = new HashSet<>();
    private final CommandManager commandManager;

    /**
     * Конструктор команды ExecuteScript.
     *
     * @param commandManager менеджер команд.
     */
    public ExecuteScript(final CommandManager commandManager) {
        super("execute_script", "считать и исполнить скрипт из указанного файла");
        this.commandManager = commandManager;
    }

    @Override
    public void executeCommand(final String args, final Console console) {
        if (args == null || args.trim().isEmpty()) {
            console.printError("Не указан файл для скрипта.");
            return;
        }
        final String fileName = args.trim();
        final File scriptFile = new File(fileName);
        if (!scriptFile.exists()) {
            console.printError("Файл " + fileName + " не существует.");
            return;
        }
        if (!scriptFile.canRead()) {
            console.printError("Нет прав для чтения файла " + fileName + ".");
            return;
        }
        final String absolutePath = scriptFile.getAbsolutePath();
        if (executingScripts.contains(absolutePath)) {
            console.printError("Обнаружена рекурсия! Файл " + fileName
                    + " уже выполняется. Выполнение команды приостановлено.");
            return;
        }
        commandManager.addCommandToHistory("execute_script");
        executingScripts.add(absolutePath);
        try (final Scanner fileScanner = new Scanner(scriptFile)) {
            while (fileScanner.hasNextLine()) {
                final String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                console.println("Выполняется команда из скрипта: " + line);
                final String[] parts = line.split("\\s+", 2);
                final String cmdName = parts[0];
                commandManager.executeCommand(line, console);
            }
        } catch (final FileNotFoundException e) {
            console.printError("Ошибка чтения файла " + fileName + ": " + e.getMessage());
        } finally {
            executingScripts.remove(absolutePath);
        }
    }
}
