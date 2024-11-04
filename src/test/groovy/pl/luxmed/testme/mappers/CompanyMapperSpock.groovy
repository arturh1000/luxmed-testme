package pl.luxmed.testme.mappers

import pl.luxmed.testme.api.dto.CompanyDto
import pl.luxmed.testme.api.dto.DepartmentDto
import pl.luxmed.testme.api.dto.ManagerDto
import pl.luxmed.testme.api.dto.ProjectDto
import pl.luxmed.testme.api.dto.TeamDto
import pl.luxmed.testme.infrastructure.entities.Company
import pl.luxmed.testme.infrastructure.entities.Department
import pl.luxmed.testme.infrastructure.entities.Team
import spock.lang.Specification

class CompanyMapperSpock extends Specification {

    def "Should convert Company entity into CompanyDto"() {
        given:
            var departament = Mock(Department);
            departament.getTeams() >> Collections.emptyList()
            var departments = [departament, departament]
            var entity = new Company(name: "Zedweb", departments: departments)
        when:
            var dto = CompanyMapper.INSTANCE.convertIntoCompanyDto(entity)
        then:
            dto.getName() == entity.getName()
            entity.getDepartments().size() == 2
            dto.getDepartments().size() == entity.getDepartments().size()
    }

    def "Convert convert CompanyDto into Company entity"() {
        given:
            var departments = [Mock(DepartmentDto)]
            var companyDto = new CompanyDto(id: 13, name: "Zedweb Rock", departments: departments)
        when:
            var company = CompanyMapper.INSTANCE.convertIntoCompany(companyDto)
        then:
            company.getId() == companyDto.getId()
            company.getName() == companyDto.getName()
            company.getDepartments().size() == 1
            company.getDepartments().size() == companyDto.getDepartments().size()
    }

    def "Should mapper map email address from deep dependence"() {
        given:
            String email = "arturh1000@gmail.com"
            ManagerDto managerDto = ManagerDto.builder().email(email).build()
            ProjectDto projectDto = ProjectDto.builder().manager(managerDto).build()
            TeamDto teamDto = TeamDto.builder().name("alfa").project(projectDto).build()
            DepartmentDto departmentDto = DepartmentDto.builder().name("Dept01").team(teamDto).build()
            CompanyDto companyDto = CompanyDto.builder().name("Name101").department(departmentDto).build()
        when:
            var company = CompanyMapper.INSTANCE.convertIntoCompany(companyDto)
            var companyDtoDoubleMapping = CompanyMapper.INSTANCE.convertIntoCompanyDto(company)
        then:
            company.getDepartments().getFirst().teams.getFirst().getProject().getManager().getEmail() == companyDto.getDepartments().getFirst().teams.getFirst().getProject().getManager().getEmail()
            companyDto.getDepartments().getFirst().teams.getFirst().getProject().getManager().getEmail() == companyDtoDoubleMapping.getDepartments().getFirst().teams.getFirst().getProject().getManager().getEmail()
            email == companyDtoDoubleMapping.getDepartments().getFirst().teams.getFirst().getProject().getManager().getEmail()
    }
}
