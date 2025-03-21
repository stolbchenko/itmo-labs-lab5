package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import utility.Validatable;

/**
 * Класс {@code Coordinates} представляет координаты с полями: {@code xcoord} и {@code ycoord}.
 *
 * <p>Поле {@code xcoord} не может быть {@code null}. Поле {@code ycoord} не больше 932.</p>
 *
 * @author Ilya Stolbchenko
 * @version 1.0
 */
public class Coordinates implements Validatable, Comparable<Coordinates> {

    /**
     * Максимальное допустимое значение для {@code ycoord}.
     */
    private static final double MAX_Y = 932;

    /**
     * Координата X. Не может быть {@code null}.
     */
    private Long xcoord;

    /**
     * Координата Y. Максимальное значение поля: 932.
     */
    private double ycoord;

    /**
     * Создает новый объект {@code Coordinates} с заданными значениями.
     *
     * @param xcoord координата X (не может быть {@code null})
     * @param ycoord координата Y (не должна превышать 932)
     */
    @JsonCreator
    public Coordinates(@JsonProperty("xcoord") Long xcoord,
                       @JsonProperty("ycoord") double ycoord) {
        this.xcoord = xcoord;
        this.ycoord = ycoord;
    }

    /**
     * Возвращает значение координаты X.
     *
     * @return значение {@code xcoord}
     */
    public Long getXcoord() {
        return xcoord;
    }

    /**
     * Возвращает значение координаты Y.
     *
     * @return значение {@code ycoord}
     */
    public double getYcoord() {
        return ycoord;
    }

    /**
     * Проверяет, соответствует ли объект требованиям валидности.
     * <ul>
     *   <li>Поле {@code xcoord} не должно быть {@code null}.</li>
     *   <li>Поле {@code ycoord} не должно превышать 932.</li>
     * </ul>
     *
     * @return {@code true}, если объект валиден, иначе {@code false}.
     */
    @Override
    public boolean validate() {
        if (xcoord == null) {
            return false;
        }
        if (ycoord > MAX_Y) {
            return false;
        }
        return true;
    }

    /**
     * Сравнивает данный объект с другим объектом {@code Coordinates}.
     *
     * <p>Сначала сравниваются значения поля {@code xcoord}.</p>
     *
     * @param other объект {@code Coordinates} для сравнения.
     * @return отрицательное число, если объект меньше другого; положительное, если больше; иначе 0.
     */
    @Override
    public int compareTo(Coordinates other) {
        final int cmp = this.xcoord.compareTo(other.xcoord);
        if (cmp != 0) {
            return cmp;
        } else {
            return Double.compare(this.ycoord, other.ycoord);
        }
    }

    /**
     * Сравнивает этот объект с указанным объектом для определения равенства.
     *
     * @param obj объект для сравнения.
     * @return {@code true}, если объекты равны по значениям, иначе {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            final Coordinates that = (Coordinates) obj;
            return Objects.equals(xcoord, that.xcoord)
                    && Double.compare(ycoord, that.ycoord) == 0;
        }
    }

    /**
     * Вычисляет хэш-код этого объекта на основе полей {@code xcoord} и {@code ycoord}.
     *
     * @return хэш-код объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(xcoord, ycoord);
    }

    /**
     * Возвращает строковое представление объекта {@code Coordinates}.
     *
     * @return строка, содержащая значения {@code xcoord} и {@code ycoord}.
     */
    @Override
    public String toString() {
        return "Coordinates{"
                + "xcoord=" + xcoord
                + ", ycoord=" + ycoord
                + '}';
    }
}
