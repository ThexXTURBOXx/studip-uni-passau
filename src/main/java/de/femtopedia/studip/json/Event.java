package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A class representing a Event.
 */
@SuppressWarnings("MemberName")
@NoArgsConstructor
@Getter
@ToString
public class Event {

	private String event_id;
	private String course;
	private long start;
	private long end;
	private String title;
	private String description;
	private String categories;
	private String room;
	private String canceled;

}
