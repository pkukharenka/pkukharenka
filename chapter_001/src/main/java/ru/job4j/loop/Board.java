package ru.job4j.loop;

/**
 * Painting board height*weight.
 *
 * @author Pyotr Kukharenka
 * @since 22.11.2017
 */
public class Board {

    /**
     * Painting board height*weight.
     *
     * @param weight - weight of board.
     * @param height - height of board.
     * @return String board.
     */
    public String paint(final int weight, final int height) {
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= weight; j++) {
                if ((i % 2 != 0 && j % 2 != 0) || i % 2 == 0 && j % 2 == 0) {
                    sb.append("X");
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
