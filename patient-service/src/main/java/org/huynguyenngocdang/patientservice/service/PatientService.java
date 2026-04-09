package org.huynguyenngocdang.patientservice.service;

import com.huynguyenngocdang.common.PageResponse;
import org.huynguyenngocdang.patientservice.dto.PatientRequestDto;
import org.huynguyenngocdang.patientservice.dto.PatientResponseDto;
import org.springframework.data.domain.Pageable;

public interface PatientService {
    PageResponse<PatientResponseDto> getAllPatients(Pageable pageable);

    PatientResponseDto getPatientById(String id);

    PatientResponseDto createPatient(PatientRequestDto requestDto);

    PatientResponseDto updatePatient(String id, PatientRequestDto requestDto);

    void deletePatient(String id);
}
