package com.meche.security.model;

import lombok.Getter;

/**
 * @Author sidof
 * @Since 10/2/23
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Getter
public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER"),
    ROLE_MANAGER("ROLE_MANAGER");

    Role(String role) {
    }


}
