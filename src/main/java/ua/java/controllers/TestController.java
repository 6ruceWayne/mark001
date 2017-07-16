package ua.java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.java.models.Test;
import ua.java.models.TestStatus;
import ua.java.services.TestInterfaceService;

@Controller
@RequestMapping(value = "/tests")
public class TestController {

	private TestInterfaceService testService;

	@Autowired(required = true)
	@Qualifier(value = "testService")
	public void setTestService(TestInterfaceService testService) {
		this.testService = testService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listTests(Model model) {
		/*
		 * String welcome = messageSource.getMessage("label.registration", new
		 * Object[]{"John Doe"}, locale); model.addAttribute("message",
		 * welcome);
		 */
		model.addAttribute("test", new Test());
		model.addAttribute("listTests", this.testService.listTests());
		return "proposals";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addTest(@ModelAttribute("test") Test t) {
		if (t.getId() == 0) {
			this.testService.addTest(t);
		} else {
			// existing person, call update
			this.testService.updateTest(t);
		}
		return "redirect:/tests/list";
	}

	@RequestMapping(value = "/remove/{id}")
	public String removeTest(@PathVariable("id") long id) {
		this.testService.removeTest(id);
		return "redirect:/tests/list";
	}

	@RequestMapping("/review/{id}")
	public String editTest(@PathVariable("id") long id, Model model) {
		Test test = this.testService.getFullTestById(id);
		model.addAttribute("ourTest", test);
		model.addAttribute("ourQuestions", test.getQuestions());

		model.addAttribute("ourAnswers", test.getQuestions());
		return "review";
	}

	@RequestMapping(value = "/choise/{id}/{status}", method = RequestMethod.GET)
	public String choise(@PathVariable("id") long id, @PathVariable("status") String status, Model model) {
		Test test = this.testService.getTestById(id);
		test.setStatus(TestStatus.Developing.getStatus(status));
		this.testService.updateTest(test);
		model.addAttribute("test", this.testService.getTestById(id));
		model.addAttribute("questions", this.testService.getListQuestionsById(id));
		return editTest(id, model);
	}

	@RequestMapping("/create")
	public String createTest(Model model) {
		Test test = new Test();
		model.addAttribute("test", test);
		return "testCreate";
	}

	@RequestMapping(value = "/changeTest", method = RequestMethod.GET)
	public String changeTest(Model model, Test test) {
		model.addAttribute("test", test);
		return "testCreate";
	}

	@RequestMapping(value = "/saveTest", method = RequestMethod.GET)
	public String saveTest(Model model, Test test) {
		testService.addTest(test);
		return "createQuestions";
	}

}
