package siit.model;

public enum ProductsEnum {
    HalogenLights("https://media.istockphoto.com/photos/ro/bec-pe-un-fundal-alb-id907928762"),
    RoastedBeans("https://media.istockphoto.com/photos/ro/boabe-de-cafea-%C3%AEn-lingur%C4%83-izolate-pe-fundal-alb-id602336684?s=612x612"),
    CatFood("https://media.istockphoto.com/photos/ro/pisica-m%C4%83n%C3%A2nc%C4%83-hran%C4%83-uscat%C4%83-pentru-pisici-id505521812?s=612x612"),
    DogFood("https://media.istockphoto.com/photos/ro/c%C3%A2ine-care-prinde-un-biscui%C8%9Bi-id1152663825?s=612x612"),
    LogitechKeyboard("https://media.istockphoto.com/photos/ro/tastatura-computerului-id458611795?s=612x612"),
    LogitechMouse("https://media.istockphoto.com/photos/ro/mouse-wireless-cu-trei-butoane-id503781430?s=612x612");

    private final String url;

    ProductsEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
