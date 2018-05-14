package ru.job4j.condition.bot;

/**
 * Bot that answer for simple questions.
 *
 * @author Pyotr Kukharenka
 * @since 21.11.2017
 */

public class DummyBot {

    /**
     * Bot answer method for 3 questions.
     *
     * @param question - our question for bot.
     * @return - bot answer.
     */

    public String answer(final String question) {
        final String result;
        if (question.equals("Привет, Бот.")) {
            result = "Привет, умник.";
        } else if (question.equals("Пока.")) {
            result = "До скорой встречи.";
        } else {
            result = "Это ставит меня в тупик. Спросите другой вопрос.";
        }
        return result;
    }
}
