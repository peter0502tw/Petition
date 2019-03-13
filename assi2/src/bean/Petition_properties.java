package bean;

import java.sql.Date;

public class Petition_properties {

	private String title;
	private Date date;
	private String content;
	private int sign;

	public String get_title() {
		return title;
	}

	public void set_title(String title) {
		this.title = title;
	}

	public Date get_date() {
		return date;
	}

	public void set_date(Date date) {
		this.date = date;
	}

	public String get_content() {
		return content;
	}

	public void set_content(String content) {
		this.content = content;
	}

	public int get_sign() {
		return sign;
	}

	public void set_sign(int sign) {
		this.sign = sign;
	}

	public Petition_properties(String title, Date date, String content, int sign) {
		this.title = title;
		this.sign = sign;
		this.content = content;
		this.date = date;
	}
}
