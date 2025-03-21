package models;

import java.util.Objects;
import utility.Validatable;

/**
 * Класс {@code Car} представляет автомобиль.
 *
 * <p>Поле {@code name} может быть {@code null} (автомобиль может не иметь имени), а поле
 * {@code cool} не может быть {@code null} (определяет, является ли автомобиль крутым).
 * </p>
 *
 * @author Ilya Stolbchenko
 * @version 1.0
 */
public class Car implements Validatable {

    /**
     * Название автомобиля. Может быть {@code null}.
     */
    private String name;

    /**
     * Флаг, указывающий, является ли автомобиль крутым.
     * Это поле не может быть {@code null}.
     */
    private Boolean cool;


    /**
     * Создаем пустой конструктор для корректной работы Jackson.
     */
    public Car() {
    }

    /**
     * Создает новый объект {@code Car} с заданными параметрами.
     *
     * @param name название автомобиля; может быть {@code null}.
     * @param cool флаг крутости; не может быть {@code null}.
     */
    public Car(String name, Boolean cool) {
        this.name = name;
        this.cool = cool;
    }

    /**
     * Возвращает название автомобиля.
     *
     * @return название автомобиля или {@code null}, если оно не задано.
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает флаг крутости автомобиля.
     *
     * @return {@code true}, если автомобиль крутой, иначе {@code false}.
     */
    public Boolean getCool() {
        return cool;
    }

    /**
     * Проверяет, является ли объект {@code Car} валидным.
     *
     * <p>Объект считается валидным, если поле {@code cool} не {@code null}.
     * </p>
     *
     * @return {@code true}, если объект валиден, иначе {@code false}.
     */
    @Override
    public boolean validate() {
        return cool != null;
    }

    /**
     * Определяет равенство этого объекта с другим объектом {@code Car} на основе полей.
     *
     * @param obj объект для сравнения.
     * @return {@code true}, если объекты равны, иначе {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Car car = (Car) obj;
        return Objects.equals(name, car.name)
                && Objects.equals(cool, car.cool);
    }

    /**
     * Вычисляет хэш-код объекта на основе полей {@code name} и {@code cool}.
     *
     * @return хэш-код объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, cool);
    }

    /**
     * Возвращает строковое представление объекта {@code Car}.
     *
     * @return строка с информацией об автомобиле.
     */
    @Override
    public String toString() {
        return "Car{"
                + " name='" + name + '\''
                + ", cool=" + cool
                + " }";
    }
}
