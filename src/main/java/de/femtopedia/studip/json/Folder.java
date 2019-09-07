package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A class representing a set of Events.
 */
@SuppressWarnings("MemberName")
@NoArgsConstructor
@Getter
@ToString
public class Folder {

	private String id;
	private String user_id;
	private String parent_id;
	private String range_id;
	private String range_type;
	private String folder_type;
	private String name;

}
