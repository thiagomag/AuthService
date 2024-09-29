package br.com.thiagomagdalena.authservice.controller.dto;

import br.com.thiagomagdalena.authservice.annotations.ValidCPF;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRequest {

    private String name;
    @NotNull
    @ValidCPF
    private String cpf;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String gender;
    private List<AddressRequest> addressRequest;
    private String telephone;
    private String email;
    private String bloodType;
    private String allergies;
    private String preExistingConditions;
    private String councilRegister;
    private String healthProfessionalType;
    private String speciality;
}
