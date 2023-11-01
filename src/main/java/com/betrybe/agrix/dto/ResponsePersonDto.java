package com.betrybe.agrix.dto;

import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * ResponsePersonDto record.
 */
public record ResponsePersonDto(Long id, String username, Role role) {
}