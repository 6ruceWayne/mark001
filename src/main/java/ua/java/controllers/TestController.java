package ua.java.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.java.models.Question;
import ua.java.models.Test;
import ua.java.services.TestInterfaceService;

@Controller
@RequestMapping(value = "/tests")
public class TestController {

	private TestInterfaceService testService;

	@Autowired
	private MessageSource messageSource;

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
		model.addAttribute("ourTest", this.testService.getFullTestById(id));
		model.addAttribute("ourQuestion", new Question());
		return "review";
	}

	@RequestMapping(value = "/choise/{id}/{status}", method = RequestMethod.GET)
	public String choise(@PathVariable("id") long id, @PathVariable("status") String status, Model model) {
		Test test = this.testService.getTestById(id);
		test.setStatus(status);
		this.testService.updateTest(test);
		model.addAttribute("test", this.testService.getTestById(id));
		model.addAttribute("questions", this.testService.getListQuestionsById(id));
		return editTest(id, model);
	}
	/*
	 * @RequestMapping(value = "/choise/{id}/{name}", method =
	 * RequestMethod.GET) public String changeName(@PathVariable("id") long
	 * id, @PathVariable("name") String name, Model model) { Test test =
	 * this.testService.getTestById(id); test.setName(name);
	 * this.testService.updateTest(test); return editTest(id, model); }
	 */

}
