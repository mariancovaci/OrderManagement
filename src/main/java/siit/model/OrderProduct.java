package siit.model;

public class OrderProduct {
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private Double quantity;
    private Product product;
    private Double value;


    public OrderProduct(Integer id, Integer orderId, Integer productId, Double quantity) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.value = 0.0;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        if (this.product.getPrice() != null) {
            this.value = getQuantity() * this.product.getPrice();
        } else {
            this.value = 0.0;
        }
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Double getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", product=" + product +
                ", value=" + value +
                '}';
    }
}
