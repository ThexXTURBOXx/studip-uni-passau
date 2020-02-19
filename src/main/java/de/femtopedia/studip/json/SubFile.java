package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A class representing a Subfile.
 */
@SuppressWarnings("MemberName")
@NoArgsConstructor
@Getter
@ToString
public class SubFile {

    private String id;
    private String file_id;
    private String folder_id;
    private int downloads;
    private String description;
    private String content_terms_of_use_id;
    private String user_id;
    private String name;
    private long mkdate;
    private long chdate;
    private long size;
    private String mime_type;
    private String storage;
    private boolean is_readable;
    private boolean is_downloadable;
    private boolean is_editable;
    private boolean is_writable;

}
