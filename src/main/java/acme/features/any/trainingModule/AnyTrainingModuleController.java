
package acme.features.any.trainingModule;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.client.data.accounts.Any;
import acme.entities.TrainingModule;

@Controller
public class AnyTrainingModuleController extends AbstractController<Any, TrainingModule> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyTrainingModuleListService	listService;

	@Autowired
	protected AnyTrainingModuleShowService	showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
	}

}
