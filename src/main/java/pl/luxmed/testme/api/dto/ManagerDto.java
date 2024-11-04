package pl.luxmed.testme.api.dto;

import jakarta.validation.constraints.Email;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ManagerDto {
    @NonNull
    @Email
    private String email;
}
