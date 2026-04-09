package org.huynguyenngocdang.patientservice.mapper;

import org.huynguyenngocdang.patientservice.dto.PatientRequestDto;
import org.huynguyenngocdang.patientservice.dto.PatientResponseDto;
import org.huynguyenngocdang.patientservice.model.Patient;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientMapper {

    PatientResponseDto toPatientResponseDto(Patient patient);
    Patient toPatient(PatientRequestDto patientRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePatient(Patient patient, @MappingTarget PatientRequestDto patientRequestDto);
}
