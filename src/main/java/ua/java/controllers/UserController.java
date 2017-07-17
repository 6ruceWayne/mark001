package ua.java.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.java.models.Answer;
import ua.java.models.Question;
import ua.java.models.Test;
import ua.java.models.User;
import ua.java.services.SecurityService;
import ua.java.services.TestInterfaceService;
import ua.java.services.UserService;
import ua.java.validator.UserValidator;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private TestInterfaceService testService;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		}

		userService.save(userForm);

		securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

		return "redirect:/personalOffice";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

	@RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
	public String welcome(Model model) {
		return "welcome";
	}

	@RequestMapping(value = { "/personalOffice" }, method = RequestMethod.GET)
	public String personalOffice(Model model, String logout) {
		if (securityService.getName() != null) {
			model.addAttribute("listTests", testService.findAllByAuthor(securityService.getName()));
			return "personalOffice";
		} else {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/changeTest", method = RequestMethod.GET)
	public String changeTest(Model model, Test test) {
		model.addAttribute("test", test);
		return "testCreate";
	}

	@RequestMapping(value = "/createTest", method = RequestMethod.GET)
	public String createTest(Model model) {
		model.addAttribute("testForm", new Test());
		return "testCreate";
	}

	@RequestMapping(value = "/createTest", method = RequestMethod.POST)
	public String createTest(@ModelAttribute("testForm") Test testForm, Model model) {
		testForm.settAuthor(securityService.getUser());
		testService.addTest(testForm);
		model.addAttribute("testForm", testForm);
		model.addAttribute("questionList", testService.getListQuestionsById(testForm.getId()));
		model.addAttribute("questionForm", new Question());
		model.addAttribute("answerForm", new Answer());

		return "questionsCreate";
	}

	@RequestMapping(value = "/changeTest/{id}", method = RequestMethod.GET)
	public String changeTest(@PathVariable("id") long id, Model model) {
		model.addAttribute("testForm", testService.getFullTestById(id));
		model.addAttribute("ourTest", new Test());
		model.addAttribute("questionForm", new Question());
		model.addAttribute("answerForm", new Answer());
		return "questionsCreate";
	}

	@RequestMapping(value = "/changeTest/{id}", method = RequestMethod.POST)
	public String changeTest(@ModelAttribute("testForm") Test testForm,
			@ModelAttribute("questionForm") Question questionForm, @ModelAttribute("answerForm") Answer answerForm,
			Model model) {
		testService.addQuestion(questionForm, testForm);
		model.addAttribute("testForm", testForm);
		model.addAttribute("questionList", testService.getListQuestionsById(testForm.getId()));
		model.addAttribute("questionForm", new Question());
		model.addAttribute("answerForm", new Answer());
		return "questionsCreate";
	}

	@RequestMapping(value = "/deleteTest/{id}")
	public String deleteTest(@PathVariable("id") long id, Model model) {
		testService.removeTest(id);
		return "redirect:/personalOffice";
	}

	@RequestMapping(value = "/createQuestions", method = RequestMethod.POST)
	public String createQuestions(@ModelAttribute("testForm") Test testForm,
			@ModelAttribute("questionForm") Question questionForm, @ModelAttribute("answerForm") Answer answerForm,
			Model model) {
		questionForm.setqTest(testForm);
		List<Question> list = testService.getListQuestionsById(testForm.getId());
		list.add(questionForm);
		testService.addTest(testForm);
		model.addAttribute("testForm", testForm);
		model.addAttribute("questionList", testService.getListQuestionsById(testForm.getId()));
		model.addAttribute("questionForm", new Question());
		model.addAttribute("answerForm", new Answer());
		return "questionsCreate";
	}
}
