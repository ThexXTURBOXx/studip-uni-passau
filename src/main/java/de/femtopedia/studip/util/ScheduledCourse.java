package de.femtopedia.studip.util;

import java.awt.Color;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class ScheduledCourse {

	String event_id;
	int start;
	int end;
	String title;
	String content;
	Color color;

}
