package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * A class representing a Subfile.
 */
@SuppressWarnings({"MemberName", "JavadocVariable"})
@RequiredArgsConstructor
@Getter
@ToString
public class SubFile {

    private final String id;
    private final String file_id;
    private final String folder_id;
    private final int downloads;
    private final String description;
    private final String content_terms_of_use_id;
    private final String user_id;
    private final String name;
    private final long mkdate;
    private final long chdate;
    private final long size;
    private final String mime_type;
    private final String storage;
    private final boolean is_readable;
    private final boolean is_downloadable;
    private final boolean is_editable;
    private final boolean is_writable;

}
