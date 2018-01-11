package ru.job4j.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Программа для поиска файлов, в которых содержится определенный текст.
 *
 * @author Pyotr Kukharenka
 * @since 05.01.2018
 */
@ThreadSafe
public class TextSearch {
    /**
     * Очередь, в которую добавляются файлы, удовлетворяющие
     * условию по расширению файла. (.txt, .doc и т.д.)
     */
    private Deque<File> queue;
    /**
     * Список с файлами, в которых содержится искомый текст.
     */
    @GuardedBy("this")
    private List<String> list;
    /**
     * Путь к папке в которой необходимо осуществить поиск.
     */
    private final String fileName;
    /**
     * Искомый текст в фалйах.
     */
    private final String text;
    /**
     * Расширение файлов, в которых будет производится поиск текста.
     */
    private final String ext;

    /**
     * Конструктор для полей параметров класса.
     *
     * @param fileName - путь к папке в которой необходимо осуществить поиск.
     * @param text     - искомый текст в фалйах.
     * @param ext      - расширение файлов, в которых будет производится поиск текста.
     */
    public TextSearch(String fileName, String text, String ext) {
        this.queue = new LinkedList<>();
        this.list = new ArrayList<>(100);
        this.fileName = fileName;
        this.text = text;
        this.ext = ext;
    }

    public synchronized void addQueue(File file) {
        this.queue.offer(file);
    }

    public synchronized File pollQueue() {
        return this.queue.poll();
    }

    public synchronized void addList(String fileName) {
        this.list.add(fileName);
    }
    public synchronized int size() {
        return this.queue.size();
    }

    //Геттеры для полей класса.
    public String getExt() {
        return ext;
    }

    public String getText() {
        return text;
    }

    /**
     * Возвращает список с файлами в которых был найден искомый текст.
     * Изначально запускается поток поиска файлов, удовлетворяющих
     * требуемому расширению. Затем для каждого найденного файла
     * запускается поток по поиску текста в файлах. Найденные файлы
     * выводятся на консоль.
     *
     * @return - список с файлами в которых был найден искомый текст.
     * @throws InterruptedException - выбрасывается при проблемах с потоком.
     */
    public List<String> result() throws InterruptedException {
        Thread find = new FileSearchThread(this, new File(this.fileName));
        find.start();
        find.join();
        while (!this.queue.isEmpty()) {
            new TextSearchThread(this).start();
            Thread.sleep(5);
        }
        for (String str : this.list) {
            System.out.println(str);
        }
        return this.list;
    }

    /**
     * Запсук программы.
     *
     * @param args - аргументы программы.
     * @throws InterruptedException - выбрасывается при проблемах с потоком.
     */
    public static void main(String[] args) throws InterruptedException {
        TextSearch search = new TextSearch("E:\\hello", "e", ".txt");
        search.result();
    }
}

/**
 * Поток поиска файлов с нужным расширением.
 */
class FileSearchThread extends Thread {
    /**
     * Объект поиска файлов.
     */
    private final TextSearch search;
    /**
     * Корневой файл.
     */
    private File root;

    /**
     * Конструктор.
     *
     * @param search - объект поиска файлов.
     * @param root   - корневой файл.
     */
    public FileSearchThread(TextSearch search, File root) {
        this.search = search;
        this.root = root;
    }

    /**
     * Метод поиска файлов с требуемым расширением. Если в
     * корневой папке есть другие папки, то для них будет
     * вызываться отдельный поток.
     */
    @Override
    public void run() {
        if (this.root.exists() && this.root.isDirectory()) {
            File[] files = this.root.listFiles();
            for (File file : files) {
                if (!file.isDirectory() && file.getName().contains(search.getExt())) {
                    this.search.addQueue(file);
                } else if (file.isDirectory()) {
                    new FileSearchThread(this.search, file).start();
                }
            }
        }
    }
}

/**
 * Поток поиска требуемого текста в найденных файлах.
 */
class TextSearchThread extends Thread {
    /**
     * Объект поиска файлов.
     */
    private final TextSearch search;

    /**
     * Конструктор.
     *
     * @param search - объект поиска файлов.
     */
    public TextSearchThread(TextSearch search) {
        this.search = search;
    }

    /**
     * Метод поиска текста в файле. Если требуемый текст
     * в файле найден, то записываем этот файл в список.
     */
    @Override
    public void run() {
        final File file = this.search.pollQueue();
        final String input = this.textFromFile(file);
        if (input.matches(".*" + this.search.getText() + "+.*")) {
            this.search.addList(file.getAbsolutePath());
        }
    }

    /**
     * Возвращает текст файла ввиде строки.
     *
     * @param file - файл для обработки
     * @return - текст файла ввиде строки.
     */
    private String textFromFile(File file) {
        String input = null;
        try {
            InputStream fis = new FileInputStream(file);
            byte[] str = new byte[fis.available()];
            fis.read(str);
            input = new String(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }
}

