
package acme.features.sponsor.sponsorDashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface SponsorSponsorDashboardRepository extends AbstractRepository {

	@Query("select count(s) from Sponsorship s where (s.sponsor.id = :sponsorId and s.draftMode = false)")
	int findNumberPublishedSponsorshipsBySponsorId(int sponsorId);

	@Query("select count(i) from Invoice i where (i.sponsorship.sponsor.id = :sponsorId and i.draftMode = false)")
	int findNumberPublishedInvoicesBySponsorId(int sponsorId);

	@Query("select count(i) from Invoice i where (i.sponsorship.sponsor.id = :sponsorId and i.draftMode = false and i.tax <= 21.0)")
	int numberOfInvoicesTaxLessEqual21(int sponsorId);

	@Query("select count(s) from Sponsorship s where (s.sponsor.id = :sponsorId and s.draftMode = false and s.link is not null)")
	int numberSponsorshipsWithLink(int sponsorId);

	@Query("select avg(s.amount.amount) from Sponsorship s where (s.sponsor.id = :sponsorId and s.draftMode = false)")
	Double averageAmountSponsorships(int sponsorId);

	@Query("select stddev(s.amount.amount) from Sponsorship s where (s.sponsor.id = :sponsorId and s.draftMode = false)")
	Double deviationAmountSponsorships(int sponsorId);

	@Query("select min(s.amount.amount) from Sponsorship s where (s.sponsor.id = :sponsorId and s.draftMode = false)")
	Double minimumAmountSponsorships(int sponsorId);

	@Query("select max(s.amount.amount) from Sponsorship s where (s.sponsor.id = :sponsorId and s.draftMode = false)")
	Double maximumAmountSponsorships(int sponsorId);

	@Query("select avg(i.quantity.amount) from Invoice i where (i.sponsorship.sponsor.id = :sponsorId and i.draftMode = false)")
	Double averageQuantityInvoices(int sponsorId);

	@Query("select stddev(i.quantity.amount) from Invoice i where (i.sponsorship.sponsor.id = :sponsorId and i.draftMode = false)")
	Double deviationQuantityInvoices(int sponsorId);

	@Query("select min(i.quantity.amount) from Invoice i where (i.sponsorship.sponsor.id = :sponsorId and i.draftMode = false)")
	Double minimumQuantityInvoices(int sponsorId);

	@Query("select max(i.quantity.amount) from Invoice i where (i.sponsorship.sponsor.id = :sponsorId and i.draftMode = false)")
	Double maximumQuantityInvoices(int sponsorId);

}
