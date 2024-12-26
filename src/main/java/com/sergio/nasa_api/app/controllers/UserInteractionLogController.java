package com.sergio.nasa_api.app.controllers;

import com.sergio.nasa_api.app.entity.dto.UserInteractionLogResponse;
import com.sergio.nasa_api.app.services.UserInteractionLogService;
import com.sergio.nasa_api.app.springdoc.aud.UserInteractionLogControllerDoc;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users-interactions")
@RequiredArgsConstructor
public class UserInteractionLogController implements UserInteractionLogControllerDoc {

    private final UserInteractionLogService userInteractionLogService;

    @Override
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<UserInteractionLogResponse>> findAll(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit
    ) {
        Pageable pageable = buildPageable(offset, limit);
        return ResponseEntity.ok(userInteractionLogService.findAll(pageable));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{email}")
    @Override
    public ResponseEntity<Page<UserInteractionLogResponse>> findByEmail(
            @PathVariable String email,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit
    ) {
        Pageable pageable = buildPageable(offset, limit);
        return ResponseEntity.ok(userInteractionLogService.findByEmail(pageable, email));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/my-interactions/{email}")
    @Override
    public ResponseEntity<?> findMyInteractions(
            @PathVariable String email,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit
    ) {
        Pageable pageable = buildPageable(offset, limit);
        Page<UserInteractionLogResponse> pageFind = userInteractionLogService.findMyInteractions(pageable, email);
        if (pageFind == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(pageFind);
    }


    private Pageable buildPageable(int offset, int limit) {
        Pageable pageable = null;
        if (offset < 0) throw new IllegalArgumentException("El atributo offset no puede ser menor a cero ");
        if (limit <= 0) throw new IllegalArgumentException("El atributo limit no puede ser menor o igual cero");
        if (offset == 0) pageable = PageRequest.of(0, limit);
        else pageable = PageRequest.of(offset / limit, limit);
        return pageable;
    }

}
