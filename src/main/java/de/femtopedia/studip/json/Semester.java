package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * A class representing a Semester.
 */
@SuppressWarnings({"MemberName", "JavadocVariable"})
@RequiredArgsConstructor
@Getter
@ToString
public class Semester {

    private final String id;
    private final String title;
    private final String description;
    private final long begin;
    private final long end;
    private final long seminars_begin;
    private final long seminars_end;

}
