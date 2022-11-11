public class Food {
    private int id;
    private String name;
    private int price;
    private int quantity;

    public Food(int id,String name,int price,int quantity){
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
        this.setQuantity(quantity);
    }

    public Food(String name,int price,int quantity){
        this.setName(name);
        this.setPrice(price);
        this.setQuantity(quantity);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
