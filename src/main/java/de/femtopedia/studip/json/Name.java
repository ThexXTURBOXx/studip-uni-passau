package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A class representing a Name.
 */
@SuppressWarnings({"MemberName", "JavadocVariable"})
@NoArgsConstructor
@Getter
@ToString
public class Name {

    private String username;
    private String formatted;
    private String family;
    private String given;
    private String prefix;
    private String suffix;

}
