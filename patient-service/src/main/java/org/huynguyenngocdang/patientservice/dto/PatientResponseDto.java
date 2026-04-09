package org.huynguyenngocdang.patientservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientResponseDto {
    private UUID id;
    private String name;
    private String email;
    private String address;
    private LocalDate dateOfBirth;
    private LocalDate registeredDate;
}
