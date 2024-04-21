
package acme.features.developer.trainingModule;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.TrainingModule;
import acme.roles.Developer;

@Controller
public class DeveloperTrainingModuleController extends AbstractController<Developer, TrainingModule> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private DeveloperTrainingModuleListMineService	listMineService;

	@Autowired
	private DeveloperTrainingModuleShowService		showService;

	@Autowired
	private DeveloperTrainingModuleCreateService	createService;

	@Autowired
	private DeveloperTrainingModuleUpdateService	updateService;

	@Autowired
	private DeveloperTrainingModuleDeleteService	deleteService;

	@Autowired
	private DeveloperTrainingModulePublishService	publishService;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listMineService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("update", this.updateService);
		super.addBasicCommand("delete", this.deleteService);

		super.addCustomCommand("publish", "update", this.publishService);
	}
}
