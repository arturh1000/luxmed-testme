package pl.luxmed.testme.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TeamDto {
    @JsonIgnore
    @Null(message = "Id parameter cannot be set")
    private Long id;

    @Size(min = 2, max = 128)
    @NotBlank(message = "Team name can't be empty")
    @Pattern(regexp = "[a-zA-Z].+", message = "Team name should start with a letter")
    private String name;

    private ProjectDto project;
}
