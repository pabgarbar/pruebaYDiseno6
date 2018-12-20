
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.HandyWorkerService;
import services.SponsorService;
import domain.Customer;
import domain.HandyWorker;
import domain.Sponsor;

@Controller
@RequestMapping("/anonymous")
public class AnonymousController extends AbstractController {

	@Autowired
	CustomerService		customerService;

	@Autowired
	HandyWorkerService	handyWorkerService;

	@Autowired
	SponsorService		sponsorService;


	public AnonymousController() {
		super();
	}

	//-------------------------------------------------------------------------------------------	
	//-------------------------- HANDY WORKER ---------------------------------------------------		

	//Create
	@RequestMapping(value = "/createHandyWorker", method = RequestMethod.GET)
	public ModelAndView createHandyWorker() {
		ModelAndView result;
		HandyWorker handyworker;

		handyworker = this.handyWorkerService.createHandyWorker();
		result = this.createEditModelAndView(handyworker);

		return result;
	}

	@RequestMapping(value = "/createHandyWorker", method = RequestMethod.POST, params = "save")
	public ModelAndView saveHandyWorker(@Valid HandyWorker handyWorker, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(handyWorker);
		} else {
			try {
				this.handyWorkerService.save(handyWorker);
				result = new ModelAndView("redirect:login.do");
			} catch (Throwable oops) {
				result = this.createEditModelAndView(handyWorker, "handyWorker.commit.error");
			}
		}
		return result;
	}

	//CreateEditModelAndView
	protected ModelAndView createEditModelAndView(HandyWorker handyWorker) {
		ModelAndView result;

		result = this.createEditModelAndView(handyWorker, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(HandyWorker handyWorker, String messageCode) {
		ModelAndView result;

		result = new ModelAndView("register/createHandyWorker");
		result.addObject("handyWorker", handyWorker);
		result.addObject("message", messageCode);

		return result;
	}

	//------------------------ CUSTOMER -----------------------------------------------------	

	//Create
	@RequestMapping(value = "/createCustomer", method = RequestMethod.GET)
	public ModelAndView createCustomer() {
		ModelAndView result;
		Customer customer;

		customer = this.customerService.create();
		result = this.createEditModelAndView(customer);

		return result;
	}

	//Save
	@RequestMapping(value = "/createCustomer", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Customer customer, BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(customer);
		} else {
			try {
				this.customerService.save(customer);
				result = new ModelAndView("redirect:login.do");
			} catch (Throwable oops) {
				result = this.createEditModelAndView(customer, "customer.commit.error");
			}
		}
		return result;
	}

	//CreateEditModelAndView
	protected ModelAndView createEditModelAndView(Customer customer) {
		ModelAndView result;

		result = this.createEditModelAndView(customer, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Customer customer, String messageCode) {
		ModelAndView result;

		result = new ModelAndView("anonymous/createCustomer");
		result.addObject("customer", customer);
		result.addObject("message", messageCode);

		return result;
	}

	//---------------------- SPONSOR -------------------------------------------------------		

	//Create
	@RequestMapping(value = "/createSponsor", method = RequestMethod.GET)
	public ModelAndView createSponsor() {
		ModelAndView result;
		Sponsor sponsor;

		sponsor = this.sponsorService.create();
		result = this.createEditModelAndView(sponsor);

		return result;
	}

	//Save
	@RequestMapping(value = "/createSponsor", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Sponsor sponsor, BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(sponsor);
		} else {
			try {
				this.sponsorService.save(sponsor);
				result = new ModelAndView("redirect:/security/login.do");
			} catch (Throwable oops) {
				result = this.createEditModelAndView(sponsor, "sponsor.commit.error");
			}
		}
		return result;
	}

	//Create Model And View
	protected ModelAndView createEditModelAndView(Sponsor sponsor) {
		ModelAndView result;

		result = this.createEditModelAndView(sponsor, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(Sponsor sponsor, String messageCode) {

		ModelAndView result;

		result = new ModelAndView("anonymous/createSponsor");
		result.addObject("sponsor", sponsor);
		result.addObject("message", messageCode);

		return result;

	}

}