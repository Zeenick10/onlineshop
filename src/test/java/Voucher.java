public class Voucher {
    private int id;
    private int price;
    private int quantity;
    private int totalPrice;
    private String userName;
    private String itemName;

    public Voucher(int id,String userName,String itemName,int price,int quantity,int totalPrice){
        this.setId(id);
        this.setUserName(userName);
        this.setItemName(itemName);
        this.setPrice(price);
        this.setQuantity(quantity);
        this.setTotalPrice(totalPrice);
    }

    public Voucher(String userName,String itemName,int price,int quantity,int totalPrice){
        this.setUserName(userName);
        this.setItemName(itemName);
        this.setPrice(price);
        this.setQuantity(quantity);
        this.setTotalPrice(totalPrice);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
