package de.femtopedia.studip.json;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * A class representing a set of Events.
 */
@SuppressWarnings("MemberName")
@NoArgsConstructor
@Getter
public class Events {

	private List<Event> collection;
	private Pagination pagination;

}
