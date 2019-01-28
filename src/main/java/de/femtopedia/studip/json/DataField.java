package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * A class representing a DataField.
 */
@SuppressWarnings("MemberName")
@NoArgsConstructor
@Getter
public class DataField {

	private String type;
	private String id;
	private String name;
	private String value;

}
