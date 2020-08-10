package com.diamond.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @Classname User
 * @Description TODO
 * @Date 2020/8/10 14:48
 * @Created by lrf
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="user")
@ToString
//@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @NotEmpty(message = "The user name should not be empty")
    private String username;
    private String password;
    private String salt;
    private String phone;
    @Email(message="please enter the right email adress")
    private String email;
    private boolean enabled;
    private String code;

}
