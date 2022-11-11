
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    String url = "jdbc:mysql://localhost:3306/store";
    String username = "root";
    String pw = "";

    Connection con;
    public void connect() throws SQLException {
        con = DriverManager.getConnection(url,username,pw);
    }
    public void disConnect() throws SQLException {
        if(con!=null){
            con.close();
        }
    }
    public void save(User user) throws SQLException {
        String name = user.getName();
        String email = user.getEmail();
        String password = user.getPassword();
        String sql = "insert into users (name,email,password) values ('"+name+"','"+email+"','"+password+"')";
        Statement stmt = con.createStatement();
        stmt.execute(sql);
    }
    public void saveVoucher(Voucher voucher) throws SQLException {
        int id  = voucher.getId();
        String userName = voucher.getUserName();
        String itemName = voucher.getItemName();
        int price = voucher.getPrice();
        int quantity = voucher.getQuantity();
        int totalPrice = voucher.getTotalPrice();
        String sql = "insert into voucher(userName,itemName,price,quantity,totalPrice) values ('"+userName+"','"+itemName+"','"+price+"','"+quantity+"','"+totalPrice+"')" ;
        Statement stmt = con.createStatement();
        stmt.execute(sql);
    }
    public List<Voucher> getVouchers() throws SQLException {
        String sql = "select * from voucher";
        Statement stmt = con.createStatement();
        ResultSet result = stmt.executeQuery(sql);
        List<Voucher> vouchers = new ArrayList<>();
        while (result.next()){
            int id = result.getInt("id");
            String userName = result.getString("userName");
            String itemName = result.getString("itemName");
            int price = result.getInt("price");
            int quantity = result.getInt("quantity");
            int totalPrice = result.getInt("totalPrice");
            vouchers.add(new Voucher(id,userName,itemName,price,quantity,totalPrice));
        }
        return vouchers;
    }


    // User Database
    public List<User> getUser() throws SQLException {
        String sql = "select * from users";
        Statement stmt = con.createStatement();
        ResultSet result = stmt.executeQuery(sql);
        List<User> userList = new ArrayList<>();
        while(result.next()){
            int id = result.getInt("id");
            String name = result.getString("name");
            String email = result.getString("email");
            String password = result.getString("password");
            userList.add(new User(id,name,email,password));
        }
        return userList;
    }

    public User getUserByName(String name) throws SQLException {
        String sql = "select * from users where name='"+name+"'";
        Statement stmt = con.createStatement();
        ResultSet result = stmt.executeQuery(sql);
        User user = null;
        while (result.next()){
            int id = result.getInt("id");
            String email =result.getString("email");
            String password = result.getString("password");
            user = new User(id,name,email,password);
        }
        return user;
    }

    public User getUserById(int id) throws SQLException {
        String sql = "select * from users where id="+id;
        Statement stmt = con.createStatement();
        ResultSet result = stmt.executeQuery(sql);
        User user = null;
        while (result.next()){
            String name = result.getString("name");
            String email =result.getString("email");
            String password = result.getString("password");
            user = new User(id,name,email,password);
        }

        return user;
    }

    public void update(User user) throws SQLException {
        int id = user.getId();
        String name = user.getName();
        String email = user.getEmail();
        String password = user.getPassword();
        String sql = "update users set name='"+name+"',email='"+email+"',password='"+password+"' where id="+id;
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
    }

    public void delete(int id) throws SQLException {
        String sql = "delete from users where id="+id;
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
    }


    // Food Database
    public List<Food> getFood() throws SQLException {
        String sql = "select * from foods";
        Statement stmt = con.createStatement();
        List<Food> foods = new ArrayList<>();
        ResultSet result = stmt.executeQuery(sql);
        while (result.next()){
            int id = result.getInt("id");
            String name = result.getString("name");
            int price = result.getInt("price");
            int quantity = result.getInt("quantity");
            foods.add(new Food(name,price,quantity));
        }
        return foods;
    }
    public void saveFood(Food food) throws SQLException {
        String name = food.getName();
        int price = food.getPrice();
        int quantity = food.getQuantity();
        String sql = "insert into foods (name,price,quantity) values ('"+name+"','"+price+"','"+quantity+"')";
        Statement stmt = con.createStatement();
        stmt.execute(sql);
    }

    public Food getFoodById(int id) throws SQLException {
        String sql = "select * from foods where id="+id;
        Statement stmt = con.createStatement();
        ResultSet result = stmt.executeQuery(sql);
        Food food = null;
        while (result.next()){
            String name = result.getString("name");
            int price = result.getInt("price");
            int quantity = result.getInt("quantity");
            food = new Food(id,name,price,quantity);
        }

        return food;

    }
    public Food getFoodByName(String name) throws SQLException {
        String sql = "select * from foods where name='"+name+"'";
        Statement stmt = con.createStatement();
        ResultSet result = stmt.executeQuery(sql);
        Food food = null;
        while (result.next()){
            int id = result.getInt("id");
            int price = result.getInt("price");
            int quantity = result.getInt("quantity");
            food = new Food(id,name,price,quantity);
        }
        return food;
    }
    public void updateFood(Food food) throws SQLException {
        int id = food.getId();
        String name = food.getName();
        int price = food.getPrice();
        int quantity = food.getQuantity();
        String sql = "update foods set name='"+name+"',price='"+price+"',quantity='"+quantity+"' where id="+id;
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
    }
    public void deleteFoodByName(String name) throws SQLException {
        String sql = "delete from foods where name='"+name+"'";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
    }
}
