package org.huynguyenngocdang.patientservice.service.impl;

import com.huynguyenngocdang.common.PageResponse;
import lombok.RequiredArgsConstructor;
import org.huynguyenngocdang.patientservice.dto.PatientRequestDto;
import org.huynguyenngocdang.patientservice.dto.PatientResponseDto;
import org.huynguyenngocdang.patientservice.exception.PatientException;
import org.huynguyenngocdang.patientservice.mapper.PatientMapper;
import org.huynguyenngocdang.patientservice.model.Patient;
import org.huynguyenngocdang.patientservice.repository.PatientRepository;
import org.huynguyenngocdang.patientservice.service.PatientService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.huynguyenngocdang.patientservice.constant.ExceptionConstant.PATIENT_EMAIL_DUPLICATED_CODE;
import static org.huynguyenngocdang.patientservice.constant.ExceptionConstant.PATIENT_EMAIL_DUPLICATED_MESSAGE;
import static org.huynguyenngocdang.patientservice.constant.ExceptionConstant.PATIENT_NOT_FOUND_CODE;
import static org.huynguyenngocdang.patientservice.constant.ExceptionConstant.PATIENT_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public PageResponse<PatientResponseDto> getAllPatients(Pageable pageable) {
        return PageResponse.of(patientRepository.findAll(pageable), patientMapper::toPatientResponseDto);
    }

    @Override
    public PatientResponseDto getPatientById(String id) {
        return patientRepository.findById(UUID.fromString(id)).map(patientMapper::toPatientResponseDto).orElseThrow(() -> new PatientException(PATIENT_NOT_FOUND_CODE, PATIENT_NOT_FOUND_MESSAGE));
    }

    @Override
    public PatientResponseDto createPatient(PatientRequestDto request) {
        validateEmailExist(request.getEmail());
        Patient patient = patientMapper.toPatient(request);
        return patientMapper.toPatientResponseDto(patientRepository.save(patient));
    }

    @Override
    public PatientResponseDto updatePatient(String id, PatientRequestDto request) {
        validateEmailExist(request.getEmail());
        Patient patient = patientRepository.findById(UUID.fromString(id)).orElseThrow(() -> new PatientException(PATIENT_NOT_FOUND_CODE, PATIENT_NOT_FOUND_MESSAGE));
        patientMapper.updatePatient(patient, request);
        return patientMapper.toPatientResponseDto(patientRepository.save(patient));
    }

    private void validateEmailExist(String email) {
        boolean isEmailExist = patientRepository.existsByEmail(email);
        if (isEmailExist) throw new PatientException(PATIENT_EMAIL_DUPLICATED_CODE, PATIENT_EMAIL_DUPLICATED_MESSAGE);
    }

    @Override
    public void deletePatient(String id) {
        Patient patient = patientRepository.findById(UUID.fromString(id)).orElseThrow(() -> new PatientException(PATIENT_NOT_FOUND_CODE, PATIENT_NOT_FOUND_MESSAGE));
        patientRepository.delete(patient);
    }


}
