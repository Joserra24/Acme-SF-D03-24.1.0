
package acme.features.auditor.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.enumerated.Type;
import acme.roles.Auditor;

@Repository
public interface AuditorDashboardRepository extends AbstractRepository {

	@Query("select a from Auditor a where a.userAccount.id = :id")
	Auditor findOneAuditorByUserAccountId(int id);

	@Query("select count(ca) from CodeAudit ca where ca.type = :type AND ca.auditor.id = :id ")
	int totalCodeAuditsType(Type type, int id);

	/*
	 * @Query("select avg(select count(ar) from AuditingRecord ar where ar.audit.id = a.id) from Audit a where a.auditor.id = :id")
	 * double averageNumberOfAuditingRecordsPerAudit(int id);
	 * 
	 * @Query("select min(select count(ar) from AuditingRecord ar where ar.audit.id = a.id) from Audit a where a.auditor.id = :id")
	 * double minimumOfAuditingRecordsPerAudit(int id);
	 * 
	 * @Query("select max(select count(ar) from AuditingRecord ar where ar.audit.id = a.id) from Audit a where a.auditor.id = :id")
	 * double maximumOfAuditingRecordsPerAudit(int id);
	 * 
	 * //@Query("select a from Audit a where a.auditor.id = :id")
	 * //Collection<Audit> findManyAuditsByAuditor(int id);
	 * 
	 * @Query("select avg((((EXTRACT(YEAR FROM ar.endPeriod)-EXTRACT(YEAR FROM ar.startPeriod))*365)*24)+(((EXTRACT(MONTH FROM ar.endPeriod)-EXTRACT(MONTH FROM ar.startPeriod))*30)*24)+((EXTRACT(DAY FROM ar.endPeriod)-EXTRACT(DAY FROM ar.startPeriod))*24)+(EXTRACT(HOUR FROM ar.endPeriod)-EXTRACT(HOUR FROM ar.startPeriod))) from AuditingRecord ar where ar.audit.auditor.id = :id"
	 * )
	 * double averageDurationOfAuditingRecordsPerAudit(int id);
	 * 
	 * @Query("select max((((EXTRACT(YEAR FROM ar.endPeriod)-EXTRACT(YEAR FROM ar.startPeriod))*365)*24)+(((EXTRACT(MONTH FROM ar.endPeriod)-EXTRACT(MONTH FROM ar.startPeriod))*30)*24)+((EXTRACT(DAY FROM ar.endPeriod)-EXTRACT(DAY FROM ar.startPeriod))*24)+(EXTRACT(HOUR FROM ar.endPeriod)-EXTRACT(HOUR FROM ar.startPeriod))) from AuditingRecord ar where ar.audit.auditor.id = :id"
	 * )
	 * double maximumDurationOfAuditingRecordsPerAudit(int id);
	 * 
	 * @Query("select min((((EXTRACT(YEAR FROM ar.endPeriod)-EXTRACT(YEAR FROM ar.startPeriod))*365)*24)+(((EXTRACT(MONTH FROM ar.endPeriod)-EXTRACT(MONTH FROM ar.startPeriod))*30)*24)+((EXTRACT(DAY FROM ar.endPeriod)-EXTRACT(DAY FROM ar.startPeriod))*24)+(EXTRACT(HOUR FROM ar.endPeriod)-EXTRACT(HOUR FROM ar.startPeriod))) from AuditingRecord ar where ar.audit.auditor.id = :id"
	 * )
	 * double minimumDurationOfAuditingRecordsPerAudit(int id);
	 * 
	 * @Query("select stddev((((EXTRACT(YEAR FROM ar.endPeriod)-EXTRACT(YEAR FROM ar.startPeriod))*365)*24)+(((EXTRACT(MONTH FROM ar.endPeriod)-EXTRACT(MONTH FROM ar.startPeriod))*30)*24)+((EXTRACT(DAY FROM ar.endPeriod)-EXTRACT(DAY FROM ar.startPeriod))*24)+(EXTRACT(HOUR FROM ar.endPeriod)-EXTRACT(HOUR FROM ar.startPeriod))) from AuditingRecord ar where ar.audit.auditor.id = :id"
	 * )
	 * double deviationDurationOfAuditingRecordsPerAudit(int id);
	 */
}
