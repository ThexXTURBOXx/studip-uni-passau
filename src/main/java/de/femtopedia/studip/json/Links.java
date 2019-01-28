package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * A class representing Links.
 */
@SuppressWarnings("MemberName")
@NoArgsConstructor
@Getter
public class Links {

	private String first;
	private String previous;
	private String next;
	private String last;

}
