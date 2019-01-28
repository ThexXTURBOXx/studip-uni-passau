package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * A class representing a Name.
 */
@SuppressWarnings("MemberName")
@NoArgsConstructor
@Getter
public class Name {

	private String username;
	private String formatted;
	private String family;
	private String given;
	private String prefix;
	private String suffix;

}
