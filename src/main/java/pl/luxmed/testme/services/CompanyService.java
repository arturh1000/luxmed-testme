package pl.luxmed.testme.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.luxmed.testme.api.dto.CompanyDto;
import pl.luxmed.testme.infrastructure.CompanyRepository;
import pl.luxmed.testme.infrastructure.entities.Company;
import pl.luxmed.testme.commons.ResourceId;
import pl.luxmed.testme.mappers.CompanyMapper;

import java.util.Optional;

@Service
@Log4j2
public record CompanyService(CompanyRepository companyRepository) {
    public ResourceId addCompany(CompanyDto companyDto) {
        var company = CompanyMapper.INSTANCE.convertIntoCompany(companyDto);

        var resId = companyRepository.save(company);
        return ResourceId.builder().resourceId(resId.getId()).build();
    }

    public boolean deleteCompany(Long companyId) {
        var result = findCompanyById(companyId);
        if (result.isEmpty()) {
            log.info("Attempting to delete company using a non-existent identifier: {}", companyId);
            return false;
        }

        companyRepository.deleteById(companyId);
        return true;
    }

    public Optional<CompanyDto> findCompanyById(Long companyId) {
        var result = companyRepository.findById(companyId);
        return result.map(CompanyMapper.INSTANCE::convertIntoCompanyDto)
                .or(Optional::empty);
    }

    public boolean updateCompany(CompanyDto companyDto) {
        Company companyEntity = CompanyMapper.INSTANCE.convertIntoCompany(companyDto);
        var result = findCompanyById(companyEntity.getId());
        if (result.isEmpty()) {
            log.info("Attempting to update company using a non-existent identifier: {}", companyEntity.getId());
            return false;
        }

        companyRepository.save(companyEntity);
        return true;
    }
}