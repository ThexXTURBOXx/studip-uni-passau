package de.femtopedia.studip.json;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Contacts {

	private List<Contact> collection;
	private Pagination pagination;

}
