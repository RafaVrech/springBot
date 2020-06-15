package com.vrechbot.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PriceAction")
@Data
@ToString
public class PriceAction {

    @EmbeddedId
    private PriceActionID id;

    @Column(name = "par")
    private String par;

    @Column(name = "ordem")
    private String ordem;

    @Column(name = "preco")
    private String preco;

    @Column(name = "tp")
    private String tp;

    @Column(name = "sl")
    private String sl;

    @Column(name = "percentage")
    private Integer percentage;
}
