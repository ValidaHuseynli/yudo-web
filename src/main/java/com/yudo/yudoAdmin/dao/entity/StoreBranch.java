package com.yudo.yudoAdmin.dao.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "store_branches")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "filial_adi", nullable = false)
    private String branchName;

    @Column(name = "DN", nullable = false, unique = true)
    private String dn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id", nullable = false)
    @ToString.Exclude
    private CityEntity cityEntity;

    @OneToMany(mappedBy = "storeBranch", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Store> stores;


}