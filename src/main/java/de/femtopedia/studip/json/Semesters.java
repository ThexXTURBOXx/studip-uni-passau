package de.femtopedia.studip.json;

import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A class representing a set of Semesters.
 */
@SuppressWarnings("MemberName")
@NoArgsConstructor
@Getter
@ToString
public class Semesters {

    private Map<String, Semester> collection;
    private Pagination pagination;

}
