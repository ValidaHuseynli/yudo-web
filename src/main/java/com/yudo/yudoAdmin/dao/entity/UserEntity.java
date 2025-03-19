package com.yudo.yudoAdmin.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinTable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Email
        @Column(unique = true)
        private String email;

        @Column(unique = true)
        private String phone;

        @NotNull
        private String password;

        @Column(name = "is_verified")
        private boolean isVerified;

        @ManyToMany(mappedBy = "likedByUsers")
        private Set<Product> likedProducts = new HashSet<>();

        @JsonIgnore
        @OneToOne(mappedBy = "user")
        private AdminEntity admin;

        @JsonIgnore
        @OneToOne(mappedBy = "user")
        private ClientEntity client;

        @JsonIgnore
        @OneToMany(mappedBy = "user")
        private List<Product> products;

        @OneToOne(mappedBy = "user")
        @JsonIgnore
        private Store stores;
        @JsonIgnore
        @OneToMany(mappedBy = "user")
        private List<PostEntity> posts;


        @JsonIgnore
        @ManyToMany
        @JoinTable(name = "user_authorities",
                joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
                inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
        private Set<AuthorityEntity> authorities = new HashSet<>();


        public UserEntity(String email, String password, String phone) {
            this.email = email;
            this.password = password;
            this.phone = phone;
        }

        public UserEntity(String email, String password) {
            this.email = email;
            this.password = password;
        }
}