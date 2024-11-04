package pl.luxmed.testme.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.luxmed.testme.infrastructure.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}