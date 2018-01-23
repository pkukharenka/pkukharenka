package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.bind.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.sql.*;

/**
 * JDBC оптимизация.
 *
 * @author Pyotr Kukharenka
 * @since 23.01.2018
 */

public class JdbcParsing {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcParsing.class);

    /**
     * Основной метод выполнения программы. Выполнение состоит из 4 этапов:
     * 1. Записываем значения в базу данных.
     * 2. Извлекаем значения из базы и записываем их в xml
     * 3. Модифицируем xml, делая элементы атрибутами
     * 4. Парсим обновленный xml и подсчитываем сумму всех значений.
     *
     * @param url      - адресс для коннекта к базе данных
     * @param userName - имя пользователя базы
     * @param password - пароль от базы
     * @param size        - количество значений помещяемых в базу.
     */
    public void run(String url, String userName, String password, int size) {
        long start = System.currentTimeMillis();
        Entries entries = this.fillTable(url, userName, password, size);
        File xml = this.toXml(entries);
        File updatedXml = this.toNewXml(xml);
        this.fromXml(updatedXml);
        System.out.println(String.format("Время работы программы - %s", System.currentTimeMillis() - start));

    }

    /**
     * Метод заполняет базу данных N элементами. Если таблица в базе
     * создана, значит удаляем оттуда все значения и заполняем ее.
     *
     * @param url      - адресс для коннекта к базе данных
     * @param userName - имя пользователя базы
     * @param password - пароль от базы
     * @param size        - количество значений помещяемых в базу.
     * @return - объект типа Entries.
     */
    public Entries fillTable(String url, String userName, String password, int size) {
        Connection cn = null;
        Entries list = null;
        try {
            cn = DriverManager.getConnection(url, userName, password);
            cn.setAutoCommit(false);
            Statement st = cn.createStatement();
            st.addBatch("CREATE TABLE IF NOT EXISTS test (field INTEGER NOT NULL);");
            st.addBatch("TRUNCATE test");
            st.executeBatch();
            PreparedStatement pst = cn.prepareStatement("INSERT INTO test (field) VALUES (?);");
            for (int i = 1; i <= size; i++) {
                pst.setInt(1, i);
                pst.addBatch();
            }
            pst.executeBatch();
            cn.commit();

            ResultSet rs = st.executeQuery("SELECT * FROM test");
            list = new Entries();
            while (rs.next()) {
                list.getEntries().add(new Entry(rs.getInt("field")));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (cn != null) {
                try {
                    cn.rollback();
                    cn.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        return list;
    }

    /**
     * Возвращает валидный xml файл, заполненный данными из базы
     * данных. В качестве парсера использован JAXB.
     *
     * @param entries - объект типа Entries.
     * @return - валидный xml файл
     */
    public File toXml(Entries entries) {
        File file = new File("1.xml");
        try {
            JAXBContext jc = JAXBContext.newInstance(Entries.class);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(entries, file);
        } catch (JAXBException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return file;
    }

    /**
     * Метод преобразует один xml документ в другой xml документ.
     * Для преобразования используется XSLT шаблон.
     *
     * @param file - файл для обработки.
     * @return - обработанный xml
     */
    public File toNewXml(File file) {
        File newFile = new File("2.xml");
        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            Transformer transformer = tf.newTransformer(new StreamSource(ClassLoader.getSystemResourceAsStream("Entry.xsl")));
            transformer.transform(new StreamSource(file), new StreamResult(newFile));
        } catch (TransformerException e) {
            LOGGER.error(e.getMessage(), e);

        }
        return newFile;
    }

    /**
     * Метод извлекает данные из xml и подсчитывает сумму значений.
     * В качестве парсера используется SAX.
     *
     * @param file - файл для обработки.
     */
    public void fromXml(File file) {
        int res = 0;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        MyHandler handler = new MyHandler();
        InputStream is;
        SAXParser parser;
        try {
            is = new FileInputStream(file);
            parser = factory.newSAXParser();
            parser.parse(is, handler);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            LOGGER.error(e.getMessage(), e);
        }
        for (Integer i : handler.getList()) {
            res += i;
        }
        System.out.println(res);
    }

    /**
     * Запуск программы. Инициализация настроек подключения
     * к базе данных.
     *
     * @param args - аргументы.
     */
    public static void main(String[] args) {
        Bean bean = new Bean();
        JdbcParsing p = new JdbcParsing();
        bean.setUrl("jdbc:postgresql://localhost:5432/xml");
        bean.setUserName("postgres");
        bean.setPassword("");
        bean.setSize(1000000);
        p.run(bean.getUrl(), bean.getUserName(), bean.getPassword(), bean.getSize());
    }
}
