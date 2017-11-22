package ru.job4j.loop;

/**
 * Piramid paint.
 *
 * @author Pyotr Kukharenka
 * @since 23.11.2017
 */
public class Paint {

    /**
     * Piramid paint.
     *
     * @param h - height of piramid.
     * @return String result.
     */
    public String piramid(final int h) {
        StringBuffer sb = new StringBuffer();
        int count = 1;
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= h - i; j++) {
                sb.append(" ");
            }
            if (i == 1) {
                sb.append("^");
            } else {
                for (int j = 1; j <= count; j++) {
                    sb.append("^");
                }
            }
            for (int j = 1; j <= h - i; j++) {
                sb.append(" ");
            }
            sb.append("\n");
            count += 2;
        }
        final String result = sb.toString();
        return result;
    }
}
