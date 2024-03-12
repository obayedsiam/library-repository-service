package com.example.library.entity;

import com.example.library.enums.RecordStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {

    @Column(name = "RECORD_STATUS")
    private RecordStatus recordStatus;

    @Temporal(TemporalType.TIME)
    @Column(name = "CREATED_AT")
    private Date createdAt;

    @Temporal(TemporalType.TIME)
    @Column(name = "UPDATED_AT")
    private Date updatedAt;
}
