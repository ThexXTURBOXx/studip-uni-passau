package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * A class representing a Subfolder.
 */
@SuppressWarnings({"MemberName", "JavadocVariable"})
@RequiredArgsConstructor
@Getter
@ToString
public class SubFolder {

    private final String id;
    private final String user_id;
    private final String parent_id;
    private final String range_id;
    private final String range_type;
    private final String folder_type;
    private final String name;
    private final String description;
    private final long mkdate;
    private final long chdate;
    private final boolean is_visible;
    private final boolean is_readable;
    private final boolean is_writable;

}
