package com.ductrungsl.identity_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
// Verify token
public class RoleRequest {
    String name;
    String description;
    Set<String> permissions;
}
