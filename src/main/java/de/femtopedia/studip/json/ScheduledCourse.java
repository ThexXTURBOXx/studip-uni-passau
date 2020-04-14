package de.femtopedia.studip.json;

import de.femtopedia.studip.util.colors.Color;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * A class representing a Scheduled Course.
 */
@SuppressWarnings({"MemberName", "JavadocVariable"})
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class ScheduledCourse {

    private int start;
    private int end;
    private String content;
    private String title;
    private Color color;
    private String type;

}
