package ru.job4j.tests;

/**
 * Search string in another string.
 *
 * @author Pyotr Kukharenka
 * @since 24.11.2017
 */

public class StringContain {

    /**
     * String search.
     *
     * @param origin - origin string.
     * @param sub    - containing string.
     * @return result.
     */
    public boolean contains(final String origin, final String sub) {
        boolean result = false;
        int count = 0;
        char[] originArr = origin.toCharArray();
        char[] subArr = sub.toCharArray();
        char first = subArr[0];
        for (int i = 0; i < originArr.length; i++) {
            if (originArr[i] == first) {
                int j = i + 1; //номер символа после первого найденного.
                int end = j + subArr.length - 1; //номер потенциально последнего символа искомой строки в оригинальной
                for (int k = 1; j < end; k++) {
                    if (originArr[j] == subArr[k]) {
                        j++;
                    }
                }
                //Переменная j будет увеличиваться каждый раз, когда символ искомой строки будет совпадать с символом
                //оригинальной. Если все символы подряд совпадут, то j == end -->> искомая строка найдена.
                if (j == end) {
                    result = true;
                }
            }
        }
        return result;
    }

}
