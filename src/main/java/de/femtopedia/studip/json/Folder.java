package de.femtopedia.studip.json;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * A class representing a Folder.
 */
@SuppressWarnings({"MemberName", "JavadocVariable"})
@RequiredArgsConstructor
@Getter
@ToString
public class Folder {

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
    private final List<SubFolder> subfolders;
    private final List<SubFile> file_refs;

}
