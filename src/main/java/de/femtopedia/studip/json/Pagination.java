package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * A class representing a Pagination.
 */
@SuppressWarnings("MemberName")
@NoArgsConstructor
@Getter
public class Pagination {

	private int total;
	private int offset;
	private int limit;
	private Links links;

}
