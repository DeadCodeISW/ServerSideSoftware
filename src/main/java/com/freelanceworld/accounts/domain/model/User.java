package com.freelanceworld.accounts.domain.model;


import com.freelanceworld.common.AccountType;
import com.freelanceworld.common.model.AuditModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;
import javax.validation.constraints.NotNull;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private  String password;
    @NotNull
    private AccountType accountType;
}
