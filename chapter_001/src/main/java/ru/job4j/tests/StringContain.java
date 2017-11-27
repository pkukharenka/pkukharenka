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
        boolean flag = false;
        char[] outer = origin.toCharArray();
        char[] inner = sub.toCharArray();
        for (int out = 0; out < outer.length; out++) {
            int count = 0;
            for (int in = 0; in < inner.length; in++) {
                if ((out + in) < outer.length && outer[out + in] == inner[in]) {
                    count++;
                }
            }
            if (count == inner.length) {
                flag = true;
                break;
            }
        }
        return flag;
    }

}
