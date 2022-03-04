package com.example.shopmain.dto;

public class ProductXSizeDto {
    private Long total_amount;
    private Long product_idproduct;
    private Long size_idsize;

    public ProductXSizeDto() {
    }

    public ProductXSizeDto(Long total_amount, Long product_idproduct, Long size_idsize) {
        this.total_amount = total_amount;
        this.product_idproduct = product_idproduct;
        this.size_idsize = size_idsize;
    }

    public Long getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Long total_amount) {
        this.total_amount = total_amount;
    }

    public Long getProduct_idproduct() {
        return product_idproduct;
    }

    public void setProduct_idproduct(Long product_idproduct) {
        this.product_idproduct = product_idproduct;
    }

    public Long getSize_idsize() {
        return size_idsize;
    }

    public void setSize_idsize(Long size_idsize) {
        this.size_idsize = size_idsize;
    }
}
