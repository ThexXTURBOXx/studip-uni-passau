package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Pagination {

	private int total;
	private int offset;
	private int limit;
	private Links links;

}
