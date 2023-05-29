package com.example.rentACar.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
    @Id
    private String id;
    private LocalDateTime createDate = LocalDateTime.now();
    @UpdateTimestamp
    private LocalDateTime updateDate;
}
