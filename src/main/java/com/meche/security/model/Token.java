package com.meche.security.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * @Author sidof
 * @Since 11/10/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Token {
    @Id
    @SequenceGenerator(name = "token_sequence_id", sequenceName = "token_sequence_id", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "token_sequence_id")
    private Long id;
    private String token;
    private boolean expired;
    private boolean revoked;
    @Enumerated(STRING)
    private TokenType tokenType;
    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "app_user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "token_app_user"))
    private Appuser appuser;
    private LocalDateTime generateAt;
}
