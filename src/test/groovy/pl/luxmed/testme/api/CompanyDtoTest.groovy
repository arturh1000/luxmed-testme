package pl.luxmed.testme.api

import jakarta.validation.Validation
import jakarta.validation.Validator
import jakarta.validation.ValidatorFactory
import pl.luxmed.testme.api.dto.CompanyDto
import spock.lang.Shared
import spock.lang.Specification

class CompanyDtoTest extends Specification{

    @Shared
    private Validator validator

    def setupSpec() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory()
        validator = factory.getValidator()
    }

    def "Check negative for 'name' field, case empty not null name"() {
        given:
            var departments = []
        var tested = new CompanyDto(name: "", departments: departments)
        when:
            var violations = validator.validate(tested).sort()

        then:
            violations.size() == 3
            "Company name can't be empty".equals(violations[0].message)
            "Company name should start with a letter and should have only letters, digits and spaces".equals(violations[1].message)
            "Length must be between 2 and 128".equals(violations[2].message)
    }

    def "Check negative for 'name' field, case one letter name"() {
        given:
            var departments = []
            var tested = new CompanyDto(name: "a", departments: departments)
        when:
            var violations = validator.validate(tested)
        then:
            violations.size() == 1
            "Length must be between 2 and 128".equals(violations.getAt(0).message)
    }

    //etc.

    def "Field `name` should valid"() {
        given:
            var departments = []
            var tested = new CompanyDto(name: "Luxmed", departments: departments)
        when:
            var violations = validator.validate(tested)
        then:
            violations.size() == 0
    }

}
