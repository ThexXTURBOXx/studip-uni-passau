package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Semester {

	private String id;
	private String title;
	private String description;
	private long begin;
	private long end;
	private long seminars_begin;
	private long seminars_end;

}
