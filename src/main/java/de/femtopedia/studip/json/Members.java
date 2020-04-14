package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * A class representing Members.
 */
@SuppressWarnings({"MemberName", "JavadocVariable"})
@RequiredArgsConstructor
@Getter
@ToString
public class Members {

    private final String user;
    private final int user_count;
    private final String autor;
    private final int autor_count;
    private final String tutor;
    private final int tutor_count;
    private final String dozent;
    private final int dozent_count;

}
