package entity;
import com.example.ecommerce.dto.response.ResponseProductDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Builder
@NoArgsConstructor
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column
    private String productName;
    @Column
    private String description;
    @Column
    private BigDecimal price;
    @Column
    private String image;
    @Column
    private String productDetails;
    @Column
    private Date createdDate;
    @Column
    private Date updatedDate;
    

//    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "product")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Set<Image> images;
    @ManyToOne()
    @JoinColumn(name = "categoryId")
    private Category category;

    @OneToMany(mappedBy = "product")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<AttributeProduct> attributeProducts;

    @OneToMany(mappedBy = "product")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<Rating> ratings;


    public Product(String productName, String description, BigDecimal price, String image, String productDetails, Date createdDate, Date updatedDate, Category category, Set<AttributeProduct> attributeProducts, Set<Rating> ratings) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.image = image;
        this.productDetails = productDetails;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.category = category;
        this.attributeProducts = attributeProducts;
        this.ratings = ratings;
    }

    public static ResponseProductDTO convertToResponseProductDTO(Product product){
        return new ResponseProductDTO(
                product.getProductId(),
                product.getProductName(),
                product.getCreatedDate(),
                product.getUpdatedDate()
        );
    }
}
