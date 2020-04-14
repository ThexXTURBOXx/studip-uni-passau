package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * A class representing a DataField.
 */
@SuppressWarnings({"MemberName", "JavadocVariable"})
@RequiredArgsConstructor
@Getter
@ToString
public class DataField {

    private final String type;
    private final String id;
    private final Object name;
    private final String value;

}
