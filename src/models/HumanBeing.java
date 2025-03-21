package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Класс {@code HumanBeing} представляет человека с различными характеристиками.
 *
 * <p>Объекты данного класса содержат следующие поля:
 * <ul>
 *   <li>{@code id} – уникальный идентификатор (не может быть {@code null}, должен
 *       быть больше 0, генерируется автоматически).</li>
 *   <li>{@code name} – имя (не может быть {@code null} или пустой строкой).</li>
 *   <li>{@code coordinates} – координаты, заданные объектом
 *       {@link Coordinates} (не может быть {@code null}).</li>
 *   <li>{@code creationDate} – дата создания (не может быть {@code null},
 *       генерируется автоматически).</li>
 *   <li>{@code realHero} – логическое значение, указывающее, является ли
 *       человек героем.</li>
 *   <li>{@code hasToothpick} – логическое значение, указывающее, имеет ли
 *       человек зубочистку.</li>
 *   <li>{@code impactSpeed} – скорость удара (тип long).</li>
 *   <li>{@code minutesOfWaiting} – минуты ожидания (тип float).</li>
 *   <li>{@code weaponType} – тип оружия, представленный перечислением
 *       {@link WeaponType} (может быть {@code null}).</li>
 *   <li>{@code mood} – настроение, представленное перечислением
 *       {@link Mood} (может быть {@code null}).</li>
 *   <li>{@code car} – автомобиль, представленный классом
 *       {@link Car} (может быть {@code null}).</li>
 * </ul>
 *
 *
 * <p>Все объекты должны быть валидными, что проверяется методом
 * {@link #validate()}.</p>
 *
 * @author Ilya
 * @version 1.0.
 */
public class HumanBeing implements Comparable<HumanBeing> {

    /**
     * Уникальный идентификатор. Не может быть {@code null} и должен быть больше 0.
     * Генерируется автоматически.
     */
    private Long id;

    /**
     * Имя. Не может быть {@code null} или пустой строкой.
     */
    private String name;

    /**
     * Координаты, заданные объектом {@link Coordinates}.
     * Не могут быть {@code null}.
     */
    private Coordinates coordinates;

    /**
     * Дата создания объекта. Не может быть {@code null}.
     * Генерируется автоматически.
     */
    private LocalDate creationDate;

    /**
     * Флаг, указывающий, является ли человек героем.
     */
    private boolean realHero;

    /**
     * Флаг, указывающий, имеет ли человек зубочистку.
     */
    private boolean hasToothpick;

    /**
     * Скорость удара.
     */
    private long impactSpeed;

    /**
     * Минуты ожидания.
     */
    private float minutesOfWaiting;

    /**
     * Тип оружия, представленный перечислением {@link WeaponType}. Может быть {@code null}.
     */
    private WeaponType weaponType;

    /**
     * Настроение, представленное перечислением {@link Mood}. Может быть {@code null}.
     */
    private Mood mood;

    /**
     * Автомобиль, представленный классом {@link Car}. Может быть {@code null}.
     */
    private Car car;

    /**
     * Конструктор для создания объекта {@code HumanBeing} с заданными параметрами.
     *
     * @param id               идентификатор; не может быть {@code null} и должен быть больше 0.
     * @param name             имя; не может быть {@code null} или пустым.
     * @param coordinates      координаты; не могут быть {@code null}.
     * @param creationDate     дата создания; не может быть {@code null}.
     * @param realHero         флаг, указывающий, является ли человек героем.
     * @param hasToothpick     флаг, указывающий, имеет ли человек зубочистку.
     * @param impactSpeed      скорость удара.
     * @param minutesOfWaiting минуты ожидания.
     * @param weaponType       тип оружия (enum {@link WeaponType}); может быть {@code null}.
     * @param mood             настроение (enum {@link Mood}); может быть {@code null}.
     * @param car              автомобиль (объект {@link Car}); может быть {@code null}.
     */
    @JsonCreator
    public HumanBeing(
            @JsonProperty("id") Long id,
            @JsonProperty("name") String name,
            @JsonProperty("coordinates") Coordinates coordinates,
            @JsonProperty("creationDate") LocalDate creationDate,
            @JsonProperty("realHero") boolean realHero,
            @JsonProperty("hasToothpick") boolean hasToothpick,
            @JsonProperty("impactSpeed") long impactSpeed,
            @JsonProperty("minutesOfWaiting") float minutesOfWaiting,
            @JsonProperty("weaponType") WeaponType weaponType,
            @JsonProperty("mood") Mood mood,
            @JsonProperty("car") Car car) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.minutesOfWaiting = minutesOfWaiting;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
    }

    /**
     * Возвращает уникальный идентификатор объекта.
     *
     * @return уникальный идентификатор.
     */
    public Long getId() {
        return id;
    }

    /**
     * Возвращает имя объекта.
     *
     * @return имя.
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает координаты объекта.
     *
     * @return объект {@link Coordinates}.
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Возвращает дату создания объекта.
     *
     * @return дата создания.
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Возвращает, является ли объект героем.
     *
     * @return {@code true}, если объект является героем; {@code false} иначе.
     */
    public boolean getRealHero() {
        return realHero;
    }

    /**
     * Возвращает, имеет ли объект зубочистку.
     *
     * @return {@code true}, если объект имеет зубочистку; {@code false} иначе.
     */
    public boolean getHasToothpick() {
        return hasToothpick;
    }

    /**
     * Возвращает скорость удара.
     *
     * @return скорость удара.
     */
    public long getImpactSpeed() {
        return impactSpeed;
    }

    /**
     * Возвращает минуты ожидания.
     *
     * @return минуты ожидания.
     */
    public float getMinutesOfWaiting() {
        return minutesOfWaiting;
    }

    /**
     * Возвращает тип оружия.
     *
     * @return тип оружия или {@code null}, если не задан.
     */
    public WeaponType getWeaponType() {
        return weaponType;
    }

    /**
     * Возвращает настроение.
     *
     * @return настроение или {@code null}, если не задано.
     */
    public Mood getMood() {
        return mood;
    }

    /**
     * Возвращает автомобиль.
     *
     * @return объект {@link Car} или {@code null}, если не задан.
     */
    public Car getCar() {
        return car;
    }

    /**
     * Проверяет, соответствует ли объект требованиям валидности.
     * <ul>
     *   <li>{@code id} не должно быть {@code null} и должно быть больше 0;</li>
     *   <li>{@code name} не должно быть {@code null} или пустой строкой;</li>
     *   <li>{@code coordinates} не должно быть {@code null} и должно быть валидным;</li>
     *   <li>{@code creationDate} не должно быть {@code null}.</li>
     * </ul>
     *
     * @return {@code true}, если объект валиден; {@code false} иначе.
     */
    public boolean validate() {
        if (id == null || id <= 0) {
            return false;
        }
        if (name == null || name.isEmpty()) {
            return false;
        }
        if (coordinates == null || !coordinates.validate()) {
            return false;
        }
        if (creationDate == null) {
            return false;
        }
        return true;
    }

    /**
     * Сравнивает данный объект с другим объектом {@code HumanBeing} по идентификатору.
     *
     * @param other объект {@code HumanBeing} для сравнения.
     * @return отрицательное число, если id меньше, положительное, если больше, иначе 0.
     */
    @Override
    public int compareTo(HumanBeing other) {
        return Long.compare(this.id, other.id);
    }

    /**
     * Определяет равенство двух объектов {@code HumanBeing} на основе их идентификатора.
     *
     * @param obj объект для сравнения.
     * @return {@code true}, если идентификаторы совпадают; {@code false} иначе.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final HumanBeing that = (HumanBeing) obj;
        return Objects.equals(id, that.id);
    }

    /**
     * Вычисляет хэш-код объекта на основе всех его полей.
     *
     * @return хэш-код объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, realHero, hasToothpick,
                impactSpeed, minutesOfWaiting, weaponType, mood, car);
    }

    /**
     * Возвращает строковое представление объекта {@code HumanBeing}.
     *
     * @return строка с информацией обо всех полях объекта.
     */
    @Override
    public String toString() {
        return new StringBuilder("HumanBeing{")
                .append("id=").append(id)
                .append(", name='").append(name).append('\'')
                .append(", coordinates=").append(coordinates)
                .append(", creationDate=").append(creationDate)
                .append(", realHero=").append(realHero)
                .append(", hasToothpick=").append(hasToothpick)
                .append(", impactSpeed=").append(impactSpeed)
                .append(", minutesOfWaiting=").append(minutesOfWaiting)
                .append(", weaponType=").append(weaponType)
                .append(", mood=").append(mood)
                .append(", car=").append(car)
                .append('}')
                .toString();
    }
}
