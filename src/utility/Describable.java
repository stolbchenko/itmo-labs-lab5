package utility;

/**
 * Интерфейс Describable предназначен для объектов, предоставляющих информацию о себе.
 *
 * <p>Реализующие классы должны реализовать {@link #getName()} и {@link #getDescription()}.</p>
 *
 * @author
 * @version 1.0
 */
public interface Describable {
    /**
     * Возвращает имя объекта.
     *
     * @return имя объекта.
     */
    String getName();

    /**
     * Возвращает описание объекта.
     *
     * @return описание объекта.
     */
    String getDescription();
}
