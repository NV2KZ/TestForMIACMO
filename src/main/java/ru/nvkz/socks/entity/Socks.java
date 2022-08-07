package ru.nvkz.socks.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Socks {
    @Id
    @Column(name = "socks_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "socks_color")
    @NotNull
    private String color;

    @Column(name = "socks_cp")
    private Integer cottonPart;

}
