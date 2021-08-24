package de.femtopedia.studip.util.colors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Class representing a color in the Stud.IP schedule.
 */
@Getter
@ToString
@RequiredArgsConstructor
public final class Color {

    /**
     * Translation table for Color IDs used in StudIP.
     */
    private static final long[] COLOR_TABLE = {
            0xff000000, 0xff008512, 0xff682c8b, 0xffb02e7c,
            0xff129c94, 0xfff26e00, 0xffa85d45, 0xff6ead10,
            0xffd60000, 0xffffbd33, 0xff66b671, 0xffa480b9,
            0xffd082b0, 0xff71c4bf, 0xfff7a866, 0xffcb9e8f
    };

    /**
     * The ID of the current color.
     */
    private final String id;

    /**
     * Translates the current color into its argb equivalent.
     *
     * @return the argb equivalent color.
     */
    public long getColor() {
        int id = Integer.parseInt(this.id);
        if (id < 0 || id >= COLOR_TABLE.length) {
            throw new IllegalArgumentException("Illegal id!");
        } else {
            return COLOR_TABLE[id];
        }
    }

}
