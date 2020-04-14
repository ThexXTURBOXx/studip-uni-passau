package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A class representing Modules.
 */
@SuppressWarnings({"MemberName", "JavadocVariable"})
@NoArgsConstructor
@Getter
@ToString
public class Modules {

    private String forum;
    private String documents;
    private String wiki;

}
