package com.yudo.yudoAdmin.dao.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String slug;
    private String title;
    private String description;
    private String imageAlt;
    private Boolean visible = false;
    private Boolean publish;
    private String imagePath;
    private String imageUrl;
    private LocalDate date;
    private String videoUrl;
    @Lob
    @Column(name = "image")
    private byte[] image;
    private String content;
    private LocalDateTime publishedDate;

    @ManyToMany
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    @ManyToOne
    @JoinColumn(name = "storage_id", referencedColumnName = "id")
    private Storage storage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToMany
    @JoinTable(
            name = "product_likes",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UserEntity> likedByUsers = new HashSet<>();


    public void like(UserEntity user) {
        likedByUsers.add(user);
    }

    public void dislike(UserEntity user) {
        likedByUsers.remove(user);
    }

    private BigDecimal oldPrice;
    private BigDecimal newPrice;

    public String getFormattedPrice()  {
        if (oldPrice != null && oldPrice.compareTo(BigDecimal.ZERO) > 0 && newPrice != null) {
            double discount = getDiscountPercentage();
            return String.format("%.2f (%d%%)", newPrice, (int) discount);
        } else {
            return String.format("%.2f", newPrice);
        }
    }
    private double getDiscountPercentage() {
        if (oldPrice != null && oldPrice.compareTo(BigDecimal.ZERO) > 0 && newPrice != null) {
            BigDecimal discountAmount = oldPrice.subtract(newPrice);
            BigDecimal discountPercentage = discountAmount.divide(oldPrice, 10, BigDecimal.ROUND_HALF_UP) // Use legacy rounding mode
                    .multiply(BigDecimal.valueOf(100))
                    .setScale(2, BigDecimal.ROUND_HALF_UP); // Ensures 2 decimal places
            return discountPercentage.doubleValue();
        }
        return 0.0;
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageData> imageData;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VideoData> videoData;


}