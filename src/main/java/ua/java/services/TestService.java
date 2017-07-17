package ua.java.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.java.models.Answer;
import ua.java.models.Question;
import ua.java.models.Test;
import ua.java.repository.TestRepository;

@Service
public class TestService implements TestInterfaceService {

	@Autowired
	private TestRepository testRep;

	@Autowired
	private QuestionInterfaceService questionServ;

	@Autowired
	private AnswerInterfaceService answerServ;

	@Override
	public void addTest(Test t) {
		// TODO Auto-generated method stub
		this.testRep.save(t);
	}

	@Override
	public void updateTest(Test t) {
		// TODO Auto-generated method stub
		this.testRep.save(t);
	}

	@Override
	public List<Test> listTests() {
		// TODO Auto-generated method stub
		return this.testRep.findAll();
	}

	@Override
	public Test getTestById(Long id) {
		return this.testRep.findOne(id);
	}

	@Override
	public void removeTest(long id) {
		// TODO Auto-generated method stub
		this.testRep.delete(id);
	}

	public void addQuestion(Question question, Test test) {
		// TODO Auto-generated method stub
		question.setqTest(test);
		questionServ.addQuestion(question);
	}

	@Override
	@Transactional
	public List<Question> getListQuestionsById(long id) {
		Test test = testRep.getOne(id);
		List<Question> list = test.getQuestions();
		return list;
	}

	@Override
	public Test getFullTestById(long id) {
		// TODO Auto-generated method stub
		Test test = this.getTestById(id);
		List<Question> list = questionServ.getListByTest(test);
		for (Question q : list) {
			List<Answer> listAnsw = answerServ.getListByQuestion(q);
			q.setAnswers(listAnsw);
		}
		test.setQuestions(list);
		return test;
	}

	@Override
	public List<Test> findAllByAuthor(String author) {
		return testRep.getListTestsByAuthor(author);
	}
}
