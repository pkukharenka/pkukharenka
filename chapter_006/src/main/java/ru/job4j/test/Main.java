package ru.job4j.test;

import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Программа по поиску вакансий Java.
 *
 * @author Pyotr Kukharenka
 * @since 10.02.2018
 */

public class Main {
    /**
     * Инициализация запуска программы с задержкой.
     */
    public void init() {
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        Properties properties = Util.getProperties();
        int delay = Integer.parseInt(properties.getProperty("delay"));
        ses.scheduleWithFixedDelay(new JobOffer(), 0, delay, TimeUnit.HOURS);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.init();
    }

}
