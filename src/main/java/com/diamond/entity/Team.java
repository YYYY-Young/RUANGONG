package com.diamond.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * @Classname Team
 * @Description TODO
 * @Date 2020/8/11 13:40
 * @Created by lrf
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="team")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @NotEmpty(message = "The team name should not be empty")
    private String teamname;
    private String des;
    private int leaderid;
}
