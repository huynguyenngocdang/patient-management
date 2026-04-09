package org.huynguyenngocdang.patientservice.controller;

import com.huynguyenngocdang.common.PageResponse;
import com.huynguyenngocdang.common.ResponseApi;
import lombok.RequiredArgsConstructor;
import org.huynguyenngocdang.patientservice.dto.PatientRequestDto;
import org.huynguyenngocdang.patientservice.dto.PatientResponseDto;
import org.huynguyenngocdang.patientservice.service.PatientService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/patients")
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<ResponseApi<PageResponse<PatientResponseDto>>> getAllPatients(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.ok(ResponseApi.success(patientService.getAllPatients(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi<PatientResponseDto>> getPatientById(@PathVariable String id) {
        return ResponseEntity.ok(ResponseApi.success(patientService.getPatientById(id)));
    }

    @PostMapping
    public ResponseEntity<ResponseApi<PatientResponseDto>> createPatient(@RequestBody PatientRequestDto request) {
        return ResponseEntity.ok(ResponseApi.success(patientService.createPatient(request)));
    }

    @PostMapping("/{id}")
    public ResponseEntity<ResponseApi<PatientResponseDto>> updatePatient(@PathVariable String id, @RequestBody PatientRequestDto request) {
        return ResponseEntity.ok(ResponseApi.success(patientService.updatePatient(id, request)));
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<ResponseApi<Void>> deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok(ResponseApi.success(null));
    }
}
