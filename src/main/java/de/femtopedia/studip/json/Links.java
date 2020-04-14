package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A class representing Links.
 */
@SuppressWarnings({"MemberName", "JavadocVariable"})
@NoArgsConstructor
@Getter
@ToString
public class Links {

    private String first;
    private String previous;
    private String next;
    private String last;

}
