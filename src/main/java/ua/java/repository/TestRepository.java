package ua.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.java.models.Question;
import ua.java.models.Test;

public interface TestRepository extends JpaRepository<Test, Long> {
	Test findTestByAuthor(String author);

	List<Test> findAll();

	public Test getTestById(long id);

	public List<Question> getListQuestionsById(long id);

	@Query("SELECT q FROM Question q WHERE q.qTest = ?1")
	List<Question> findByTestId(@Param("qTest") Test qTest);

	List<Test> getListTestsByAuthor(String author);
}
