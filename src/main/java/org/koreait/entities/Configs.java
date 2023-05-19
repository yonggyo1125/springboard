package org.koreait.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Data
public class Configs {
    @Id
    @Column(length=45)
    private String code;

    @Lob
    private String value;

}
