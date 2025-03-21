import commands.Add;
import commands.AverageOfImpactSpeed;
import commands.Clear;
import commands.ExecuteScript;
import commands.Exit;
import commands.Help;
import commands.History;
import commands.Info;
import commands.MinByCoordinates;
import commands.PrintAscending;
import commands.RemoveAtIndex;
import commands.RemoveById;
import commands.RemoveFirst;
import commands.Save;
import commands.Show;
import commands.Update;
import java.io.IOException;
import java.util.Stack;
import managers.CollectionManager;
import managers.CommandManager;
import managers.DumpManager;
import models.HumanBeing;
import utility.Console;
import utility.Runner;
import utility.StandartConsole;

/**
 * Главный класс приложения.
 *
 * <p>Запускает интерактивный режим работы с коллекцией объектов.
 * </p>
 *
 * @author
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        // Получение имени файла из переменной окружения
        final String fileNameEnv = System.getenv("FILE_NAME");
        if (fileNameEnv == null || fileNameEnv.trim().isEmpty()) {
            System.err.println("Переменная окружения не установлена. Завершаем выполнение.");
            System.exit(1);
        }
        String fileName = fileNameEnv.trim();
        if (fileName.startsWith("~")) {
            fileName = System.getProperty("user.home") + fileName.substring(1);
        }

        // Создаем объект консоли
        final Console console = new StandartConsole();

        // Создаем DumpManager и загружаем коллекцию из файла
        final DumpManager dumpManager = new DumpManager(fileName);
        Stack<HumanBeing> loadedCollection;
        try {
            loadedCollection = dumpManager.loadCollection();
        } catch (IOException e) {
            console.printError("Ошибка загрузки коллекции: " + e.getMessage());
            loadedCollection = new Stack<>();
        }

        // Создаем менеджер коллекции, передавая имя файла и объект консоли
        final CollectionManager collectionManager = new CollectionManager(loadedCollection);

        // Создаем менеджер команд и регистрируем команды
        final CommandManager commandManager = new CommandManager();
        commandManager.register("help", new Help(commandManager.getCommands().values()));
        commandManager.register("info", new Info(collectionManager));
        commandManager.register("show", new Show(collectionManager));
        commandManager.register("add", new Add(collectionManager));
        commandManager.register("update", new Update(collectionManager));
        commandManager.register("remove_by_id", new RemoveById(collectionManager));
        commandManager.register("clear", new Clear(collectionManager));
        commandManager.register("save", new Save(collectionManager, dumpManager));
        commandManager.register("execute_script", new ExecuteScript(commandManager));
        commandManager.register("exit", new Exit());
        commandManager.register("remove_at", new RemoveAtIndex(collectionManager));
        commandManager.register("remove_first", new RemoveFirst(collectionManager));
        commandManager.register("history", new History(commandManager));
        commandManager.register("average_of_impact_speed",
                new AverageOfImpactSpeed(collectionManager));
        commandManager.register("min_by_coordinates",
                new MinByCoordinates(collectionManager));
        commandManager.register("print_ascending",
                new PrintAscending(collectionManager));

        // Регистрируем shutdown hook через класс Exit
        Exit.registerShutdownHook(collectionManager, console, dumpManager);

        // Создаем объект Runner и запускаем интерактивный режим
        final Runner runner = new Runner(console, commandManager, collectionManager);
        runner.interactiveMode();
    }
}
