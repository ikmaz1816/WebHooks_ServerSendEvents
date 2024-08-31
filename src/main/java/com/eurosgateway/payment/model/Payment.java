package com.eurosgateway.payment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name="payment")
public class Payment {
    @Id
    @Column(name="paymentid")
    private long paymentId;

    @Column(name="email")
    private String email;

    @JsonIgnore
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private Status status;
}
