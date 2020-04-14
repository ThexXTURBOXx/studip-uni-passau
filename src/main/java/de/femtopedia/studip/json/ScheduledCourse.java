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

    private final int start;
    private final int end;
    private final String content;
    private final String title;
    private final Color color;
    private final String type;

}
