package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Semester {

	private String id;
	private String title;
	private String description;
	private int begin;
	private int end;
	private int seminars_begin;
	private int seminars_end;

}
