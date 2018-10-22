package de.femtopedia.studip.json;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class User {

	private String user_id;
	private String username;
	private Name name;
	private String perms;
	private String email;
	private String avatar_small;
	private String avatar_medium;
	private String avatar_normal;
	private String avatar_original;
	private String phone;
	private String homepage;
	private String privadr;
	private List<DataField> datafields;

}
