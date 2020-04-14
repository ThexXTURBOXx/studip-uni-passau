package de.femtopedia.studip.json;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A class representing a set of Events.
 */
@SuppressWarnings({"MemberName", "JavadocVariable"})
@NoArgsConstructor
@Getter
@ToString
public class Events {

    private List<Event> collection;
    private Pagination pagination;

}
