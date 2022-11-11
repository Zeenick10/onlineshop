import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private final static Scanner SCANNER = new Scanner(System.in);
    Database db = new Database();
    private void databaseConnect(){
        try {
            db.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void adminOrUser(){
        boolean found = false;
        while (true) {
            System.out.println("\n1.Admin      2.User       3.Stop");
            int who = Integer.parseInt(SCANNER.nextLine());
            if (who == 1) {
                adminProcess();
                found = true;
            } else if (who == 2) {
                userProcess();
                found = true;
            } else if (who == 3) {
                found = false;
            }

            if (found == false) {
                break;
            }
        }
    }
    private void adminProcess(){
        System.out.print("Email : ");
        String email = SCANNER.nextLine();
        System.out.print("Password : ");
        String password = SCANNER.nextLine();
        try {
            List<User> user =db.getUser();
            for (User a : user) {
                if (email.equalsIgnoreCase("admin@gmail.com") && password.equals("123")) {
                    System.out.println("\nShow Lists\n-------------");
                    showList();
                    CRUDProcess();
                } else {
                    System.out.println("Login Failed...");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private void CRUDProcess() {
        boolean condition = false;
        while (true) {
            System.out.println("\n1.Add     2.Update    3.Delete    4.Stop");
            int checkNumber = Integer.parseInt(SCANNER.nextLine());
            if (checkNumber == 1) {
                adminAddNewData();
                condition = true;
            } else if (checkNumber == 2) {
                adminUpdateData();
                condition = true;
            } else if (checkNumber == 3) {
                adminDeleteData();
                showList();
                condition = true;
            } else if (checkNumber == 4) {
                condition = false;
            }

            if (condition == false) {
                break;
            }
        }
    }
    private List<Food> adminDeleteData() {
        System.out.print("Name : ");
        String deleteItemName = SCANNER.nextLine();
        List<Food> foods = null;
        try {
            foods = db.getFood();
            int position = 0;
            for (int i = 0; i < foods.size(); i++) {
                if (deleteItemName.equalsIgnoreCase(foods.get(i).getName())) {
                    position = i;
                    break;
                }
            }
            String delete = foods.get(position).getName();
            System.out.println(delete);
            db.deleteFoodByName(delete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return foods;
    }
    private List<Food> adminUpdateData() {
        System.out.println("1.Item Name     2.Price        3.Quantity");
        int check = Integer.parseInt(SCANNER.nextLine());
        List<Food> foods = null;
        try {
            foods = db.getFood();
            if (check == 1) {
                updateNewItemName();
            } else if (check == 2) {
                updateNewItemPrice();
            } else if (check == 3) {
                updateNewItemQuantity();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        showList();
        return foods;
    }
    private void updateNewItemQuantity() {
        System.out.println("Which Item Name's Quantity Do you want to update?");
        System.out.print("Name : ");
        String oldName = SCANNER.nextLine();
        System.out.print("New Item Quantity : ");
        int newItemQuantity = Integer.parseInt(SCANNER.nextLine());
        try {
            boolean flag = false;
            List<Food> foodList = db.getFood();
            for(int i=0;i<foodList.size();i++){
                if(oldName.equalsIgnoreCase(foodList.get(i).getName())){
                    flag = true;
                    break;
                }else{
                    flag = false;
                }
            }
            if(flag==true){
                try {
                    Food foods = db.getFoodByName(oldName);
                    foods.setQuantity(newItemQuantity);
                    db.updateFood(foods);
                } catch (SQLException e) {

                }
            }else{
                System.err.println("This item do not have in the list");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private void updateNewItemPrice() {
        System.out.println("Which Price Do you want to update?");
        System.out.print("Enter Name : ");
        String oldName = SCANNER.nextLine();
        System.out.print("Enter New Item Price : ");
        int newItemPrice = Integer.parseInt(SCANNER.nextLine());
        try {
            boolean flag = false;
            List<Food> foodList = db.getFood();
            for(int i=0;i<foodList.size();i++){
                if(oldName.equalsIgnoreCase(foodList.get(i).getName())){
                    flag = true;
                    break;
                }else{
                    flag = false;
                }
            }
            if(flag==true){
                try {
                    Food foods = db.getFoodByName(oldName);
                    foods.setPrice(newItemPrice);
                    db.updateFood(foods);
                } catch (SQLException e) {

                }
            }else{
                System.err.println("This item do not have in the list");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void updateNewItemName(){
        System.out.println("Which Item Do you want to update?");
        System.out.print("Enter Name : ");
        String oldName = SCANNER.nextLine();
        System.out.print("Enter New Name : ");
        String newItemName = SCANNER.nextLine();
        try {
            boolean flag = false;
            List<Food> foodList = db.getFood();
            for(int i=0;i<foodList.size();i++){
                if(oldName.equalsIgnoreCase(foodList.get(i).getName())){
                    flag = true;
                    break;
                }else{
                    flag = false;
                }
            }
            if(flag==true){
                try {
                    Food foods = db.getFoodByName(oldName);
                    foods.setName(newItemName);
                    db.updateFood(foods);
                } catch (SQLException e) {

                }
            }else{
                System.err.println("This item do not have in the list");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    private List<Food> adminAddNewData() {
        System.out.println("How many items want to add?");
        System.out.print("Type Here : ");
        int count = Integer.parseInt(SCANNER.nextLine());
        boolean flag = false;
        List<Food> foods = null;
        for (int i = 0; i < count; i++) {
            System.out.println("-----" + (i + 1) + "------");
            System.out.print("Name : ");
            String newItemName = SCANNER.nextLine();
            System.out.print("Price : ");
            int newItemPrice = Integer.parseInt(SCANNER.nextLine());
            System.out.print("Quantity : ");
            int newItemQuantity = Integer.parseInt(SCANNER.nextLine());
            try {
                foods = db.getFood();
                for (Food f : foods) {
                    if (newItemName.equalsIgnoreCase(f.getName())) {
                        flag = true;
                        break;
                    } else {
                        flag = false;
                    }
                }

                if (flag == true) {
                    System.out.println("This item have already had....");
                } else {
                    System.out.println("");
                    db.saveFood(new Food(newItemName,newItemPrice,newItemQuantity));
//                    foods.add(new Food(newItemName, newItemPrice, newItemQuantity));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }

        showList();
        return foods;
    }
    private void userProcess(){
        System.out.println("1.Login       2.Register");
        int check = Integer.parseInt(SCANNER.nextLine());
        if (check == 1) {
            loginProcess();
        } else if (check == 2) {
            registerProcess();
        }
    }
    private void registerProcess(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Name : ");
        String name = sc.nextLine();
        System.out.print("Email : ");
        String email = sc.nextLine();
        System.out.print("Password : ");
        String password =sc.nextLine();
        System.out.print("Confirm_Password : ");
        String confirm = sc.nextLine();

        System.out.println("------------------------------------------------------------------");
        boolean flag = false;
        while (true){
            if (email.endsWith("@gmail.com") || email.endsWith("@mail.org")) {
                if (!(password.equalsIgnoreCase(confirm))) {
                    System.out.println("Password and Confirm Password must be the same!!!!");
                } else {
                    try {
                        List<User> list = db.getUser();
                        for(User c  : list){
                            if(email.equalsIgnoreCase(c.getEmail())){
                                flag = true;
                                break;
                            }else{
                                flag = false;
                            }
                        }

                        if(flag==true){
                            System.out.println("This email account is already created...");
                        }else{
                            db.save(new User(name,email,password));
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    flag=true;
                }
            }else{
                System.out.println("Please fill valid mail(eg:example@gmail.com)");
                break;
            }

            if(flag=true){
                break;
            }

            userProcess();
        }

    }
    private void loginProcess(){
        System.out.print("Email : ");
        String email = SCANNER.nextLine();
        System.out.print("Password : ");
        String password = SCANNER.nextLine();
        try {
            List<User> list = db.getUser();

            String customerName = null;
            boolean flag = false;
            if (list.isEmpty()) {
                System.out.println("Sorry! You don't have already account, Please register first...");
                registerProcess();
            } else {
                for (int i = 0; i < list.size(); i++) {
                    customerName = list.get(i).getName();
                    if (email.equals(list.get(i).getEmail()) && password.equals(list.get(i).getPassword())) {
                        showList();
                        flag = true;
                        break;
                    } else {
                        flag = false;
                    }
                }
            }
            if (flag == true) {
                buyProcess(customerName);
                voucher(customerName);
            } else {
                System.out.println("Invalid Email and Password, try again....");
                loginProcess();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void showList(){
        try {
            List<Food> foods = db.getFood();
            System.out.printf("%-3s %-20s %-15s %-20s\n","No", "Item Name", "Prices", "Quantity");

            for(int i=0;i<foods.size();i++){
                System.out.printf("%-3s %-20s %-15s %4s\n", (i + 1), foods.get(i).getName(), foods.get(i).getPrice(), foods.get(i).getQuantity());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void buyProcess(String customerName){
        System.out.println("How many food do you want buy?");
        int count = Integer.parseInt(SCANNER.nextLine());
        int totalValue = 0;
        int finalQuantity = 0;
        for (int i = 0; i < count; i++) {
            System.out.print("Food Name : ");
            String buyFoodName = SCANNER.nextLine();
            System.out.print("Quantity : ");
            int buyItemQuantity = Integer.parseInt(SCANNER.nextLine());
            try {
                List<Food> foods = db.getFood();
                for (int j = 0; j < foods.size(); j++) {
                    if (buyFoodName.equalsIgnoreCase(foods.get(j).getName()) && buyItemQuantity <= foods.get(j).getQuantity()) {
                        totalValue = foods.get(j).getPrice() * buyItemQuantity;
//                        finalQuantity = foods.get(j).getQuantity() - buyItemQuantity;
//                        foods.add(new Voucher(customerName, foods.get(j).getName(), foods.get(j).getPrice(), buyItemQuantity, totalValue));
                        db.saveVoucher(new Voucher(customerName,foods.get(j).getName(),foods.get(j).getPrice(),buyItemQuantity,totalValue));
//                        foods.set(j, new Food(foods.get(j).getName(), foods.get(j).getPrice(), finalQuantity));
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }
    private void voucher(String customerName){
        System.out.println("\n" + customerName + "'s Voucher\n-------------------");
        int total = 0;
        System.out.printf("%-3s %-20s %-15s %-20s %-10s\n", "No", "Item Name", "Prices", "Quantity", "Total Value");
        try {
            List<Voucher> vouchers = db.getVouchers();
            for (int i = 0; i < vouchers.size(); i++) {
                if (vouchers.get(i).getUserName().equals(customerName)) {
                    System.out.printf("%-3s %-20s %-15s %4s %20s\n", (i + 1), vouchers.get(i).getItemName(),
                            vouchers.get(i).getPrice(), vouchers.get(i).getQuantity(),
                            vouchers.get(i).getTotalPrice());
                    total += vouchers.get(i).getTotalPrice();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("------------------------------------------------------------------------------------------");
        System.out.printf("%55s %3s", "Total", "=");
        System.out.printf("%7s", total);
        System.out.print("Ks\n");
    }
    private void databaseDisconnect(){
        try {
            db.disConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public App(){

        // connect database
        databaseConnect();

        // checking admin or user
        adminOrUser();


        // Disconnect Process
        databaseDisconnect();


    }

    public static void main(String[] args) {
        App app = new App();
//        try {
//            db.save(new Customer("Naung Naung","naung@gmail.com","1234"));
//            System.out.println("Success");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        try {
//            db.delete(3);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        try {
//            List<Customer> list = db.getCustomer();
//            for(Customer c : list){
//                System.out.println(c.getName()+" : "+c.getEmail()+" : "+c.getPassword());
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        try {
//            Customer customer = db.getCustomerById(3);
//            customer.setName("Kyaw Wana");
//            customer.setEmail("kyawwana@gmail.com");
//            customer.setPassword("321");
//
//            db.update(customer);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

    }

}
