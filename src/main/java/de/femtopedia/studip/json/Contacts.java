package de.femtopedia.studip.json;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A class representing a set of Contacts.
 */
@SuppressWarnings({"MemberName", "JavadocVariable"})
@NoArgsConstructor
@Getter
@ToString
public class Contacts {

    private List<Contact> collection;
    private Pagination pagination;

}
