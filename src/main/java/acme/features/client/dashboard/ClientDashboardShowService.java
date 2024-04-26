
package acme.features.client.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Principal;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.ClientDashboard;
import acme.roles.Client;

@Service
public class ClientDashboardShowService extends AbstractService<Client, ClientDashboard> {

	@Autowired
	protected ClientDashboardRepository repository;


	@Override
	public void authorise() {
		boolean status;
		Principal principal = super.getRequest().getPrincipal();
		int id = principal.getAccountId();

		Client client = this.repository.findOneClientByUserAccountId(id);
		status = client != null && principal.hasRole(Client.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Client client;
		ClientDashboard dashboard;
		Principal principal;
		int id;

		principal = super.getRequest().getPrincipal();
		id = principal.getAccountId();
		client = this.repository.findOneClientByUserAccountId(id);

		int totalLogLessThan25;
		int totalLogLessBetween25And50;
		int totalLogLessBetween50And75;
		int totalLogAbove75;
		Double averageBudgetContracts;
		Double deviationBudgetContracts;
		Double minimunBudgetContracts;
		Double maximumBudgetContracts;

		totalLogLessThan25 = this.repository.findTotalLogLessThan25(client);
		totalLogLessBetween25And50 = this.repository.findTotalLogLessBetween25And50(client);
		totalLogLessBetween50And75 = this.repository.findTotalLogLessBetween50And75(client);
		totalLogAbove75 = this.repository.findTotalLogAbove75(client);

		averageBudgetContracts = this.repository.findAverageBudgetContracts(client);
		deviationBudgetContracts = this.repository.findDeviationBudgetContracts(client);
		minimunBudgetContracts = this.repository.findMinimunBudgetContracts(client);
		maximumBudgetContracts = this.repository.findMaximumBudgetContracts(client);

		dashboard = new ClientDashboard();

		dashboard.setTotalLogsLessThan25(totalLogLessThan25);
		dashboard.setTotalLogsLessBetween25And50(totalLogLessBetween25And50);
		dashboard.setTotalLogsLessBetween50And75(totalLogLessBetween50And75);
		dashboard.setTotalLogsAbove75(totalLogAbove75);

		dashboard.setAverageBudgetContract(averageBudgetContracts);
		dashboard.setDeviationBudgetContract(deviationBudgetContracts);
		dashboard.setMinimumBudgetContract(minimunBudgetContracts);
		dashboard.setMaximumBudgetContract(maximumBudgetContracts);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final ClientDashboard object) {
		assert object != null;
		Dataset dataset;

		dataset = super.unbind(object, "totalLogLessThan25", "totalLogLessBetween25And50", "totalLogLessBetween50And75", "totalLogAbove75", "averageBudgetContracts", "deviationBudgetContracts", "minimunBudgetContracts", "maximumBudgetContracts");
		super.getResponse().addData(dataset);
	}

}
