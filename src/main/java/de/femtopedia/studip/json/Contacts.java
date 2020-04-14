package de.femtopedia.studip.json;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * A class representing a set of Contacts.
 */
@SuppressWarnings({"MemberName", "JavadocVariable"})
@RequiredArgsConstructor
@Getter
@ToString
public class Contacts {

    private final List<Contact> collection;
    private final Pagination pagination;

}
