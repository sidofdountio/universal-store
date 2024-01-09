package com.meche.security.model;

import lombok.Getter;

/**
 * @Author sidof
 * @Since 11/10/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Getter
public enum TokenType {
    BEARER("Bearer");
    TokenType(String bearer){}
}
