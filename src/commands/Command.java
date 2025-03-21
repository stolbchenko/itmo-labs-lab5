package commands;

import java.util.Objects;
import utility.Console;
import utility.Describable;

/**
 * Абстрактный класс {@code Command} представляет команду, которая может быть выполнена.
 *
 * <p>Каждый подкласс должен реализовывать метод
 * {@link #executeCommand(String, Console)} для выполнения конкретной команды.
 * </p>
 *
 * @author Ilya Stolbchenko
 * @version 1.0
 */
public abstract class Command implements Describable {

    private final String name;
    private final String description;

    /**
     * Конструктор для создания команды.
     *
     * @param name        имя команды.
     * @param description описание команды.
     */
    public Command(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Возвращает имя команды.
     *
     * @return имя команды.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Возвращает описание команды.
     *
     * @return описание команды.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Выполняет команду с указанными аргументами.
     *
     * @param args    аргументы команды.
     * @param console объект консоли для ввода/вывода.
     */
    public abstract void executeCommand(final String args, final Console console);

    /**
     * Сравнивает этот объект с указанным объектом для определения равенства.
     *
     * @param obj объект для сравнения.
     * @return {@code true}, если объекты равны, иначе {@code false}.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Command command = (Command) obj;
        return name.equals(command.name)
                && description.equals(command.description);
    }

    /**
     * Вычисляет хэш-код команды на основе её имени и описания.
     *
     * @return хэш-код объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    /**
     * Возвращает строковое представление команды.
     *
     * @return строка, содержащая имя и описание команды.
     */
    @Override
    public String toString() {
        return "Command{"
                + "name='" + name + '\''
                + ", description='" + description + '\''
                + '}';
    }
}
