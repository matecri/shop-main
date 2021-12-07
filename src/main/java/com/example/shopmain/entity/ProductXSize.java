package com.example.shopmain.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_x_size")
public class ProductXSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(nullable = false)
    private long total_amount;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_idproduct")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "size_idsize")
    private Size size;

    public ProductXSize() {
    }

    public ProductXSize(long total_amount, Product product, Size size) {
        this.total_amount = total_amount;
        this.product = product;
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(long total_amount) {
        this.total_amount = total_amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
