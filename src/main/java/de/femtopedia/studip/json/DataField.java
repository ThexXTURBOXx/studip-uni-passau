package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A class representing a DataField.
 */
@SuppressWarnings("MemberName")
@NoArgsConstructor
@Getter
@ToString
public class DataField {

    private String type;
    private String id;
    private String name;
    private String value;

}
