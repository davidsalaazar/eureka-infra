package com.salazar.security.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "owner_app")
public class OwnerAppModel implements Serializable {

    private static final long serialVersionUID = -8622149575316918176L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<ApiModel> api;
}
