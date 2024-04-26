
package acme.features.sponsor.invoice;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.Invoice;
import acme.entities.Sponsorship;

@Repository
public interface SponsorInvoiceRepository extends AbstractRepository {

	@Query("select i from Invoice i where i.sponsorship.id = :masterId")
	Collection<Invoice> findManyInvoicesByMasterId(int masterId);

	@Query("select i from Invoice i where i.id = :id")
	Invoice findOneInvoiceById(int id);

	@Query("select s from Sponsorship s where s.sponsor.id = :sponsorId")
	Collection<Sponsorship> findManySponsorshipsBySponsorId(int sponsorId);

	@Query("select s from Sponsorship s where s.id = :id")
	Sponsorship findOneSponsorshipById(int id);

	@Query("select i from Invoice i where i.code = :code")
	Invoice findOneInvoiceByCode(String code);

	@Query("select i.sponsorship from Invoice i where i.id = :invoiceId")
	Sponsorship findOneSponsorshipByInvoiceId(int invoiceId);

}
