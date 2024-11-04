package pl.luxmed.testme.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import pl.luxmed.testme.api.dto.CompanyDto;
import pl.luxmed.testme.infrastructure.entities.Company;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper( CompanyMapper.class );

    CompanyDto convertIntoCompanyDto(Company company);
    Company convertIntoCompany(CompanyDto company);
}
