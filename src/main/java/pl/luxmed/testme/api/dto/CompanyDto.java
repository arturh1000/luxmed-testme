package pl.luxmed.testme.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CompanyDto {

    @Null(message = "Id parameter cannot be set")
    private Long id;

    @Size(min = 2, max = 128, message = "Length must be between 2 and 128")
    @NotBlank(message = "Company name can't be empty")
    @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9 ]*", message = "Company name should start with a letter and should have only letters, digits and spaces")
    private String name;

    @Singular("department")
    private List<DepartmentDto> departments;
}
