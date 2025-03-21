package managers;


import static com.fasterxml.jackson.core.JsonGenerator.Feature.AUTO_CLOSE_TARGET;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Stack;
import models.HumanBeing;

/**
 * Класс {@code DumpManager} отвечает за сериализацию (сохранение) и десериализацию
 * (загрузку) коллекции объектов {@code HumanBeing} в/из XML-файла.
 *
 * <p>Чтение данных осуществляется с помощью {@link InputStreamReader} (UTF-8),
 * запись — с помощью {@link OutputStreamWriter} (UTF-8). Путь к файлу задаётся в конструкторе,
 * и методы loadCollection() и saveCollection() используют его напрямую.
 * </p>
 *
 * @author Ilya Stolbchenko
 * @version 1.0
 */
public class DumpManager {

    /**
     * Экземпляр XmlMapper для (де)сериализации объектов в/из XML.
     */
    private final XmlMapper xmlMapper;

    /**
     * Путь к файлу, из которого загружается/в который сохраняется коллекция.
     */
    private final String filePath;

    /**
     * Конструктор DumpManager.
     *
     * @param filePath путь к XML-файлу, в котором будет храниться коллекция.
     */
    public DumpManager(final String filePath) {
        this.filePath = filePath;
        this.xmlMapper = new XmlMapper();
        xmlMapper.getFactory().disable(AUTO_CLOSE_TARGET);
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Загружает коллекцию объектов {@link HumanBeing} из указанного XML-файла.
     *
     * <p>Если файл не существует или пуст, возвращается пустой {@code Stack}.
     * При загрузке XML десериализуется как {@code List}, затем его элементы переносятся
     * в {@code Stack}.
     * </p>
     *
     * @return загруженный {@code Stack<HumanBeing>} или пустой, если возникли ошибки.
     * @throws IOException если возникает ошибка при чтении или десериализации.
     */
    public Stack<HumanBeing> loadCollection() throws IOException {
        final File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("Предупреждение: файл \"" + filePath
                    + "\" не найден. Возвращаем пустую коллекцию.");
            return new Stack<>();
        }
        if (!file.canRead()) {
            throw new IOException("Нет прав для чтения файла: " + filePath);
        }
        // Чтение содержимого файла в строку с помощью InputStreamReader
        final StringBuilder sb = new StringBuilder();
        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8)) {
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + filePath);
            return new Stack<>();
        } catch (IOException e) {
            throw new IOException("Ошибка при чтении файла: " + e.getMessage(), e);
        }
        final String xml = sb.toString().trim();
        if (xml.isEmpty()) {
            System.err.println("Файл пуст. Коллекция будет пустой.");
            return new Stack<>();
        }
        try {
            // Десериализуем XML напрямую в Stack<HumanBeing>
            return xmlMapper.readValue(new StringReader(xml),
                    new TypeReference<Stack<HumanBeing>>() {});
        } catch (IOException e) {
            throw new IOException("Ошибка при загрузке XML из файла: " + e.getMessage(), e);
        }
    }


    /**
     * Сохраняет коллекцию объектов {@link HumanBeing} в указанный XML-файл.
     *
     * <p>Перед записью {@code Stack<HumanBeing>} преобразуется в
     * {@code ArrayList<HumanBeing>} для сериализации, чтобы избежать потенциальных
     * проблем с десериализацией.
     * </p>
     *
     * @param collection коллекция объектов для сохранения.
     * @throws IOException если возникает ошибка записи в файл.
     */
    public void saveCollection(final Stack<HumanBeing> collection) throws IOException {
        final File file = new File(filePath);
        final File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            System.err.println("Директория " + parentDir.getAbsolutePath()
                    + " не существует. Сохранение невозможно.");
            return;
        }
        if (!file.exists()) {
            if (!file.createNewFile()) {
                System.err.println("Не удалось создать файл: " + filePath);
                return;
            }
        }
        if (!file.canWrite()) {
            System.err.println("Нет прав для записи в файл: " + filePath);
            return;
        }
        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(file), StandardCharsets.UTF_8)) {
            xmlMapper.writeValue(writer, collection);
            writer.flush();
            System.out.println("Коллекция успешно сохранена в файл: " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
            throw e;
        }
    }
}
