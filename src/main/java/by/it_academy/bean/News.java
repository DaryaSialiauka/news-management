package by.it_academy.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class News implements Serializable{

	private static final long serialVersionUID = 250656662810895313L;
	
	private int id;
	private String title;
	private String brief;
	private String content;
	private Calendar date_create;
	private int id_author;

	public News() {
		id = 0;
		title = "";
		brief = "";
		content = "";
		date_create = new GregorianCalendar();
		id_author = 0;
	}

	public News(int id, String title, String brief, String content, Calendar date, int id_author) {
		this.id = id;
		this.title = title;
		this.brief = brief;
		this.content = content;
		this.date_create = date;
		this.id_author = id_author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Calendar getDate_create() {
		return date_create;
	}

	public void setDate_create(Calendar date_create) {
		this.date_create = date_create;
	}

	public int getId_author() {
		return id_author;
	}

	public void setId_author(int id_author) {
		this.id_author = id_author;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brief == null) ? 0 : brief.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((date_create == null) ? 0 : date_create.hashCode());
		result = prime * result + id;
		result = prime * result + id_author;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		if (brief == null) {
			if (other.brief != null)
				return false;
		} else if (!brief.equals(other.brief))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (date_create == null) {
			if (other.date_create != null)
				return false;
		} else if (!date_create.equals(other.date_create))
			return false;
		if (id != other.id)
			return false;
		if (id_author != other.id_author)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", brief=" + brief + ", content=" + content + ", date_create="
				+ date_create + ", id_author=" + id_author + "]";
	}
	
	

}
