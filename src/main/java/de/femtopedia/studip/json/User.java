package de.femtopedia.studip.json;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * A class representing a User.
 */
@SuppressWarnings({"MemberName", "JavadocVariable"})
@RequiredArgsConstructor
@Getter
@ToString
public class User {

    private final String user_id;
    private final String username;
    private final Name name;
    private final String perms;
    private final String email;
    private final String avatar_small;
    private final String avatar_medium;
    private final String avatar_normal;
    private final String avatar_original;
    private final String phone;
    private final String homepage;
    private final String privadr;
    private final List<DataField> datafields;

}
