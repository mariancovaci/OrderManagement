package siit.model;

public class Product {
    private Integer id;
    private String name;
    private Double weight;
    private Double price;
    private String url;

    public Product(Integer id, String name, Double weight, Double price) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.url = "";
        setUrl();


    }

    public String getUrl() {
        return url;
    }

    private void setUrl() {
        if (name == null) return;
     switch (name){
         case "Halogen lights":
             this.url = ProductsEnum.HalogenLights.getUrl();
             break;
         case "Roasted beans":
             this.url = ProductsEnum.RoastedBeans.getUrl();
             break;
         case "Cat food":
             this.url = ProductsEnum.CatFood.getUrl();
             break;
         case "Dog food":
             this.url = ProductsEnum.DogFood.getUrl();
             break;
         case "Logitech keyboard":
             this.url = ProductsEnum.LogitechKeyboard.getUrl();
             break;
         case "Logitech mouse":
             this.url = ProductsEnum.LogitechMouse.getUrl();
             break;
         default:
             this.url = "";
     }
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }

    public Double getWeight() {
        return weight;
    }

    public Double getPrice() {
        return price;
    }
}
