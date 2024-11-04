package pl.luxmed.testme.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.luxmed.testme.api.dto.CompanyDto;
import pl.luxmed.testme.commons.LuxmedTestAppResponseBody;
import pl.luxmed.testme.services.CompanyService;

@RestController
@RequestMapping(path = "/company", produces = MediaType.APPLICATION_JSON_VALUE)
public record CompanyController(CompanyService companyService) {

    @GetMapping("/hello")
    public ResponseEntity<String> getHello() {
        return ResponseEntity.ok("Hello World vIII");
    }

    @PostMapping
    public ResponseEntity<LuxmedTestAppResponseBody> addCompany(@RequestBody @Valid CompanyDto companyDto) {
        var resId = companyService.addCompany(companyDto);
        if (resId.hasResourceId()) {
            return ResponseEntity.ok(resId);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<String> deleteCompany(@PathVariable @NotBlank Long companyId) {
        if (companyService.deleteCompany(companyId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable Long companyId) {
        var company = companyService.findCompanyById(companyId);

        return company.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<LuxmedTestAppResponseBody> editCompany(@PathVariable Long companyId, @RequestBody @Valid CompanyDto companyDto) {
        companyDto.setId(companyId);
        if (companyService.updateCompany(companyDto)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}