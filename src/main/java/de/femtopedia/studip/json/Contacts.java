package de.femtopedia.studip.json;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * A class representing a set of Contacts.
 */
@SuppressWarnings("MemberName")
@NoArgsConstructor
@Getter
public class Contacts {

	private List<Contact> collection;
	private Pagination pagination;

}
