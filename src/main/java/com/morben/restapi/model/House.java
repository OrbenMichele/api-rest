package com.morben.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "house")
public class House {

    @Id
    @Column(updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @Column(name = "json", nullable = false)
    private String house;

//    @Column(name = "house_log", updatable = false, nullable = false)
//    @Type(type = "org.hibernate.type.PostgresUUIDType")
//    private UUID houseLog;

}
