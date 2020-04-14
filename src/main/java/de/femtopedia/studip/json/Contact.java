package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * A class representing a Contact.
 */
@SuppressWarnings({"MemberName", "JavadocVariable"})
@RequiredArgsConstructor
@Getter
@ToString
public class Contact {

    private final String id;
    private final String href;
    private final Name name;
    private final String avatar_small;
    private final String avatar_medium;
    private final String avatar_normal;
    private final String avatar_original;

}
