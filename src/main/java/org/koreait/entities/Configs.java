package org.koreait.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Data
public class Configs {
    @Id
    @Column(name="code_", length=45)
    private String code;

    @Lob
    @Column(name="value_")
    private String value;

}
