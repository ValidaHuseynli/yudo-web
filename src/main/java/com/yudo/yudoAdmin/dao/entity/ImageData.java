package com.yudo.yudoAdmin.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Lob;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "image_data")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String fileName;
    String fileType;
    String link;

    @Lob
    @Column(name = "image_data", length = 100000)
    byte[] imageData1;

    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    Product product;

    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    PostEntity post;


    String filePath;
    String mediaType;
}