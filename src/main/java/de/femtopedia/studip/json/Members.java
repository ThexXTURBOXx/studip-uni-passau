package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A class representing Members.
 */
@SuppressWarnings("MemberName")
@NoArgsConstructor
@Getter
@ToString
public class Members {

    private String user;
    private int user_count;
    private String autor;
    private int autor_count;
    private String tutor;
    private int tutor_count;
    private String dozent;
    private int dozent_count;

}
