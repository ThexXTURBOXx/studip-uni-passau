package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A class representing a Subfolder.
 */
@SuppressWarnings("MemberName")
@NoArgsConstructor
@Getter
@ToString
public class SubFolder {

    private String id;
    private String user_id;
    private String parent_id;
    private String range_id;
    private String range_type;
    private String folder_type;
    private String name;
    private String description;
    private long mkdate;
    private long chdate;
    private boolean is_visible;
    private boolean is_readable;
    private boolean is_writable;

}
