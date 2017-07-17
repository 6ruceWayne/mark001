package ua.java.services;

import java.util.List;

import ua.java.models.Question;
import ua.java.models.Test;

public interface TestInterfaceService {
	public void addTest(Test p);

	public void updateTest(Test p);

	public List<Test> listTests();

	public Test getTestById(Long id);

	public void removeTest(long id);

	public List<Question> getListQuestionsById(long id);

	public Test getFullTestById(long id);

	List<Test> findAllByAuthor(String author);

	public void addQuestion(Question q, Test t);
}
