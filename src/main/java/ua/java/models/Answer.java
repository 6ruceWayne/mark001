package ua.java.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Answers")
public class Answer {
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;
	@Column(name = "answer")
	private String answer;
	@Column(name = "correct")
	private boolean correct;
	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question aQuestion;

	public Answer() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public Question getaQuestion() {
		return aQuestion;
	}

	public void setaQuestion(Question aQuestion) {
		this.aQuestion = aQuestion;
	}

}
