package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * A class representing a Event.
 */
@SuppressWarnings({"MemberName", "JavadocVariable"})
@RequiredArgsConstructor
@Getter
@ToString
public class Event {

    private final String event_id;
    private final String course;
    private final long start;
    private final long end;
    private final String title;
    private final String description;
    private final String categories;
    private final String room;
    private final String canceled;

}
