package ua.java.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Tests")
public class Test {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;
	private boolean free;
	@Column(name = "statusT")
	private String status;
	private String author;
	private String section;
	@OneToMany(mappedBy = "qTest", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private List<Question> questions = new ArrayList<Question>();
	private String commentToAdmin;

	public Test() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public String getCommentToAdmin() {
		return commentToAdmin;
	}

	public void setCommentToAdmin(String commentToAdmin) {
		this.commentToAdmin = commentToAdmin;
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", name=" + name + ", description=" + description + ", isFree=" + free + ", status="
				+ status + ", autor=" + author + ", section=" + section + ", commentToAdmin=" + commentToAdmin + "]";
	}
}
