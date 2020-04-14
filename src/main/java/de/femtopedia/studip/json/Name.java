package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * A class representing a Name.
 */
@SuppressWarnings({"MemberName", "JavadocVariable"})
@RequiredArgsConstructor
@Getter
@ToString
public class Name {

    private final String username;
    private final String formatted;
    private final String family;
    private final String given;
    private final String prefix;
    private final String suffix;

}
