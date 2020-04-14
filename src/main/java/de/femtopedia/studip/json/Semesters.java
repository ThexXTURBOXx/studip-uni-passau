package de.femtopedia.studip.json;

import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * A class representing a set of Semesters.
 */
@SuppressWarnings({"MemberName", "JavadocVariable"})
@RequiredArgsConstructor
@Getter
@ToString
public class Semesters {

    private final Map<String, Semester> collection;
    private final Pagination pagination;

}
