package de.femtopedia.studip.json;

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
    private String color;
    private String type;

}
