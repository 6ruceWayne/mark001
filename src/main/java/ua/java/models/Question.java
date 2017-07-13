package ua.java.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Questions")
public class Question {
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;
	@Column(name = "question")
	private String text;
	@ManyToOne
	@JoinColumn(name = "testId")
	private Test qTest;
	@OneToMany(mappedBy = "aQuestion", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private List<Answer> answers = new ArrayList<Answer>();

	public Question() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Test getqTest() {
		return qTest;
	}

	public void setqTest(Test qTest) {
		this.qTest = qTest;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", question=" + text + ", qTest=" + qTest + ", answers=" + answers + "]";
	}
}
