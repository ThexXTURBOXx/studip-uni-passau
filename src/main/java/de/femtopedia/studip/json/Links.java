package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * A class representing Links.
 */
@SuppressWarnings({"MemberName", "JavadocVariable"})
@RequiredArgsConstructor
@Getter
@ToString
public class Links {

    private final String first;
    private final String previous;
    private final String next;
    private final String last;

}
