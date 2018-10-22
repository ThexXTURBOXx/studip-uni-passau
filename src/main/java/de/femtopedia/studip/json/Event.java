package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Event {

	private String event_id;
	private String course;
	private int start;
	private int end;
	private String title;
	private String description;
	private String categories;
	private String room;
	private boolean canceled;

}
