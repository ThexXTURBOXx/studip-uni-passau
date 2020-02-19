package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A class representing a Contact.
 */
@SuppressWarnings("MemberName")
@NoArgsConstructor
@Getter
@ToString
public class Contact {

    private String id;
    private String href;
    private Name name;
    private String avatar_small;
    private String avatar_medium;
    private String avatar_normal;
    private String avatar_original;

}
