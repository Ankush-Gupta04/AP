import java.util.*;
import java.io.Console;

// input class to restrict no of scanners and easy usage of scanner and close them at the end of programme
class input{
    static Scanner integ = new Scanner(System.in);
    static Scanner stri = new Scanner(System.in);
    static Scanner doub = new Scanner(System.in);

    static int input_int(){
        int x = integ.nextInt();
        return x;
    }

    static Double input_doube(){
        Double x = doub.nextDouble();
        return x;
    }

    static String input_str(){
        String x = stri.nextLine();
        return x;
    }

    static String passward(){
        Console cons;
        if ((cons = System.console()) != null){
            char [] inp = cons.readPassword();
            return new String (inp);
        }
        else{
            System.out.println("No Console found....");
            return "No Console";
        }
    }

    static void close(){
        integ.close();
        stri.close();
        doub.close();
    }
}


class normal{
    double balance;
    String name;
    String passward;
    ArrayList<product> cart_product;
    ArrayList<deal> cart_deal;
    Integer age; 
    Integer phone_number; 
    String email_id;
    ArrayList<Coupon> coupons;

    public normal(String name, String passward,Integer age, Integer phone_no, String email_id){
        this.name = name;
        this.passward = passward;
        this.balance = 1000.0;
        this.cart_product = new ArrayList<product>();
        this.cart_deal = new ArrayList<deal>();
        this.age = age;
        this.phone_number = phone_no;
        this.email_id = email_id;
        this.coupons =  new ArrayList<Coupon>();
    }

    public void common(){        
        while(true)
        {    System.out.println("Welcome" + this.name + " !!");
            System.out.println("1) browse products");
            System.out.println("2) browse deals");
            System.out.println("3) add a product to cart");
            System.out.println("4) add products in deal to cart");
            System.out.println("5) view coupons");
            System.out.println("6) check account balance");
            System.out.println("7) view cart");
            System.out.println("8) empty cart");
            System.out.println("9) checkout cart");
            System.out.println("10) upgrade customer status");
            System.out.println("11) Add amount to wallet");
            System.out.println("12) back");
            System.out.print("Input: ");
            int fourth = input.input_int();
            if (fourth == 1){
                System.out.println("All avaiable products are:- ");
                for (int i = 0; i < catalog.categories.size(); i++){
                    catalog.categories.get(i).print();
                }
            }
            else if (fourth == 2){
                System.out.println("All avaiable deals are:- ");
                for (int i = 0; i < catalog.deal_list.size(); i++){
                    catalog.deal_list.get(i).print();
                }
            }
            else if (fourth == 3){
                add_to_cart();
            }
            else if (fourth == 4){
                add_deals();
            }
            else if (fourth == 6){
                System.out.println("Rs " + this.balance);
            }
            else if (fourth == 5){
                if (this.coupons.size() == 0){
                    System.out.println("No coupons available");
                }
                else{
                    System.out.println("All available coupons are:- ");
                    for (int i = 0 ; i < this.coupons.size(); i++){
                        this.coupons.get(i).print();
                    }
                }
            }
            else if (fourth == 7){
                cart_print();
            }
            else if (fourth == 8){
                cart_deal.clear();
                cart_product.clear();
            }
            else if (fourth == 9){
                chekout();
            }
            else if (fourth == 10){
                upgrade_status();
            }
            else if (fourth == 11){
                System.out.print("Enter amount that has to be added in wallet:- ");
                double extra = input.input_doube();
                this.balance += extra;
            }
            else if (fourth == 12){
                break;
            }
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    void upgrade_status(){
        System.out.println("Possible upgradations are:- ");
        System.out.println("1) Prime");
        System.out.println(("2) Elite"));
        System.out.print("Input: ");
        int last = input.input_int();
        if (last == 1){
            if (this.balance < 200.0){
                System.out.println("Insufficent Balance");
                return;
            }
            boolean check = this.make_payment();
            if (!check){
                System.out.println("Wrong Credentials !!!");
                return;
            }
            this.balance -= 200.0;
            Main.normal_customer.remove(this);
            prime newp = (prime) this;
            Main.prime_customer.add(newp);
            System.out.println(newp.getClass() + newp.name);
        }
        else if (last == 2){
            if (this.balance < 300.0){
                System.out.println("Insufficent Balance");
                return;
            }
            boolean check = this.make_payment();
            if (!check){
                System.out.println("Wrong Credentials !!!");
                return;
            }
            this.balance -= 300.0;
            Main.normal_customer.remove(this);
            elite newp = (elite) this;
            Main.elite_customer.add(newp);
            System.out.println(newp.getClass() + newp.name);
        }
        else{
            System.out.println("Wrong input");
        }
    }

    void add_to_cart(){
        System.out.println("All avaiable products are:- ");
        for (int i = 0; i < catalog.categories.size(); i++){
            catalog.categories.get(i).print();
        }
        System.out.print("Enter id of product:- ");
        double id_c = input.input_doube();
        int temp = (int) (id_c); 
        if (!catalog.cat_id.contains(temp)){
            System.out.println("Wrong id!!");
            return;
        }
        category cc = catalog.categories.get(0);
        for (int i = 0; i < catalog.categories.size(); i++){
            if (catalog.categories.get(i).id == temp){
                cc = catalog.categories.get(i);
            }
        }
        if (!cc.pro_id.contains(id_c)){
            System.out.println("Wrong Id !!!");
            return;
        }
        product pp = cc.products.get(0);
        for (int i = 0; i < cc.products.size(); i++){
            if (cc.products.get(i).id == id_c){
                pp = cc.products.get(i);
            } 
        }
        this.cart_product.add(pp);
        System.out.println("Product added Succesfully !!!");
        
    }
    void add_deals(){
        System.out.println("All available deals are:- ");
        for (int i = 0; i < catalog.deal_list.size(); i++){
            catalog.deal_list.get(i).print();
        }
        System.out.print("Enter the id of deal you want to add:- ");
        int deal_id = input.input_int();
        if (!catalog.deal_id.contains(deal_id)){
            System.out.println("Invalid Id !!!");
        } 
        deal dd = catalog.deal_list.get(0);
        for (int i = 0; i < catalog.deal_list.size(); i++){
            if (catalog.deal_list.get(i).id == deal_id){
                dd = catalog.deal_list.get(i);
            }
        }
        this.cart_deal.add(dd);
    }
    boolean make_payment(){
        System.out.print("Enter passward:- ");
        String check = input.passward();
        if (this.passward.equals(check)){
            return true;
        }
        return false;
    }
    void chekout(){
        this.cart_print();
        double amount = amount_calculate();
        boolean check = make_payment();
        double del = 100 + amount*(5/100);
        double max = 0;
        if (this.coupons.size() != 0){
            for (int i = 0; i < this.coupons.size(); i++){
                if (max < this.coupons.get(i).amount){
                    max = this.coupons.get(i).amount;
                }
            }
        }
        amount = amount*(1-(max/100));
        amount += del;
        if (!check){
            System.out.println("Wrong credentials !!!");
            return;
        }
        if (this.balance < amount){
            System.out.println("Insufficent Balance");
            return;
        }
        this.balance -= amount;
        this.cart_deal.clear();
        this.cart_product.clear();
        System.out.println("Total cost = Rs " + amount);
        System.out.println("Your order will be delivered within 7 days");
    }

    double amount_calculate(){
        double amount = 0.0;
        for (int i = 0; i < this.cart_product.size(); i++){
            double pa = this.cart_product.get(i).price;
            amount += pa*(1-((this.cart_product.get(i).normal_dis)/100));
            
        }
        for (int i = 0; i < this.cart_deal.size(); i++){
            amount += this.cart_deal.get(i).price;
        }
        return amount;
    }

    void cart_print(){
        System.out.println(this.name);
        System.out.println("Products in cart are:");
        if (cart_product.size() == 0){
            System.out.println("  ---- No Product added in the cart ---  ");
        }
        else{
            for (int i = 0; i < cart_product.size(); i++){
                cart_product.get(i).print();
            }
        }
        if (cart_deal.size() == 0){
            System.out.println("  ---- No Deal added in the cart ---  ");
        }
        else{
            for (int i = 0; i < cart_deal.size(); i++){
                cart_deal.get(i).print();
            }
        }
        System.out.println("Total amount:- Rs " + this.amount_calculate());
    } 
}

class elite extends normal{


    public elite(String name, String passward, String Passward, Integer age, Integer phone_no, String email_id) {
        super(name, passward, phone_no, phone_no, email_id);
    }

    @Override
    double amount_calculate(){
        double amount = 0.0;
        for (int i = 0; i < this.cart_product.size(); i++){
            double pa = this.cart_product.get(i).price;
            amount += pa*(1-((this.cart_product.get(i).elite_dis)/100));
            
        }
        for (int i = 0; i < this.cart_deal.size(); i++){
            amount += this.cart_deal.get(i).price;
        }
        return amount;
    }

    @Override
    void chekout() {
        this.cart_print();
        double amount = amount_calculate();
        boolean check = make_payment();
        double del = 100;
        double max = 0;
        if (this.coupons.size() != 0){
            for (int i = 0; i < this.coupons.size(); i++){
                if (max < this.coupons.get(i).amount){
                    max = this.coupons.get(i).amount;
                }
            }
        }
        amount = amount*(1-((max + 10)/100));
        Random rand = new Random();
        if (amount >= 5000){
            int no = rand.nextInt(3,5);
            for (int i = 0 ; i < no; i++){
                Coupon cc = new Coupon("cc",rand.nextDouble(5,11));
                this.coupons.add(cc);
            }
        }
        amount += del;
        if (!check){
            System.out.println("Wrong credentials !!!");
            return;
        }
        if (this.balance < amount){
            System.out.println("Insufficent Balance");
            return;
        }
        this.balance -= amount;
        this.cart_deal.clear();
        this.cart_product.clear();
        System.out.println("Total cost = Rs " + amount);
        System.out.println("Your order will be delivered within 1 day");
    }
   
    @Override
    void upgrade_status(){
        System.out.println("No further upgradation available");
    }
}

class prime extends normal{


    public prime(String name, String passward, String Passward, Integer age, Integer phone_no, String email_id) {
        super(name, passward, phone_no, phone_no, email_id);
    }

    @Override
    double amount_calculate(){
        double amount = 0.0;
        for (int i = 0; i < this.cart_product.size(); i++){
            double pa = this.cart_product.get(i).price;
            amount += pa*(1-((this.cart_product.get(i).prime_dis)/100));
            
        }
        for (int i = 0; i < this.cart_deal.size(); i++){
            amount += this.cart_deal.get(i).price;
        }
        return amount;
    }

    @Override
    void upgrade_status(){
        if (this.balance < 100.0){
            System.out.println("Insufficent Balance");
            return;
        }
        boolean check = this.make_payment();
        if (!check){
            System.out.println("Wrong Credentials !!!");
            return;
        }
        this.balance -= 100.0;
        Main.prime_customer.remove(this);
        normal newp = (normal) this;
        elite newe = (elite) newp;
        Main.elite_customer.add(newe);
        System.out.println(newe.getClass() + newe.name);
    }

    @Override
    void chekout() {
        this.cart_print();
        double amount = amount_calculate();
        boolean check = make_payment();
        double del = 100 + (2*amount);
        double max = 0;
        if (this.coupons.size() != 0){
            for (int i = 0; i < this.coupons.size(); i++){
                if (max < this.coupons.get(i).amount){
                    max = this.coupons.get(i).amount;
                }
            }
        }
        amount = amount*(1-((max + 5)/100));
        Random rand = new Random();
        if (amount >= 5000){
            int no = rand.nextInt(3,5);
            for (int i = 0 ; i < no; i++){
                Coupon cc = new Coupon("cc",rand.nextDouble(5,11));
                this.coupons.add(cc);
            }
        }
        amount += del;
        if (!check){
            System.out.println("Wrong credentials !!!");
            return;
        }
        if (this.balance < amount){
            System.out.println("Insufficent Balance");
            return;
        }
        this.balance -= amount;
        this.cart_deal.clear();
        this.cart_product.clear();

        System.out.println("Total cost = Rs " + amount);
        System.out.println("Your order will be delivered within 2 days");
    }
}

class admin{
    private static String pass_beff = "ankush04";
    private static String pass_gill = "04ankush";
    private static String name;

    // main fn of login which is static and accessible from outside
    static void login(){
        String pass;
        System.out.println("Welcome Admin,");
        System.out.print("Give name: ");
        String nam = input.input_str();
        System.out.print("Give passward: ");
        pass = input.passward(); // console is used for masking passward as asked in question 
        boolean flag = false;
        if (nam.equalsIgnoreCase("Beff Jezos")){ // ignore case sensitive 
            if (pass.equals(pass_beff)){
                name = "Beff Jezos"; // setting name only when input is valid 
                flag = true;
            }
        }
        else if (nam.equalsIgnoreCase("Gill Bates")){ // ignore case sensitive 
            if (pass.equals(pass_gill)){
                name = "Gill Bates"; // setting name only when input is valid 
                flag = true;
            }
        }
        if (flag == false){
            System.out.println("Wrong Credentials!!!");
            return;
        }
        while(true){
            System.out.println("Welcome " + name + ",");
            System.out.println("Please choose any one of the following actions:");
            System.out.println("1) Add category");
            System.out.println("2) Delete category");
            System.out.println("3) Add Product");
            System.out.println("4) Delete Product");
            System.out.println("5) Set Discount on Product");
            System.out.println("6) Add giveaway deal");
            System.out.println("7) Back");
            System.out.print("Input: ");
            int second = input.input_int();
            if (second == 1){
                add_category();
            }
            else if (second == 2){
                delete_category();
            }
            else if (second == 3){
                add_product();
            }
            else if (second == 4){
                delete_product();
            }
            else if (second == 5){
                set_dicount();
            }
            else if (second == 6){
                add_giveaway();
            }
            else if (second == 7){
                break;
            }
        }
    }

    private static void add_category(){
        System.out.print("Id: ");
        int id = input.input_int();
        if (catalog.cat_id.contains(id)){
            System.out.println("Id is already there.");
            return;
        }
        System.out.print("Name: ");
        String name = input.input_str();
        category temp_cat =  new category(name,id);
        System.out.println("Give details of 1st product of this range");
        System.out.print("Product Id: ");
        Double pro_id = input.input_doube();
        System.out.print("Product Name: ");
        String pro_name = input.input_str();
        System.out.print("Product Price: ");
        Double pro_price = input.input_doube();
        new product(pro_name, pro_id, pro_price,temp_cat);
        catalog.categories.add(temp_cat);
    }

    private static void delete_category(){
        int id = input.input_int();
        if (catalog.cat_id.contains(id)){
            category temp = catalog.categories.get(0);
            for (int i = 0; i < catalog.categories.size(); i++){
                if (catalog.categories.get(i).id == id){
                    temp = catalog.categories.get(i);
                    break;
                }
            }
            catalog.categories.remove(temp);
        }
    }

    private static void add_product(){
        System.out.print("Id of category: ");
        int id = input.input_int();
        if (catalog.cat_id.contains(id)){
            category temp = catalog.categories.get(0);
            for (int i = 0; i < catalog.categories.size(); i++){
                if (catalog.categories.get(i).id == id){
                    temp = catalog.categories.get(i);
                    break;
                }
            }
            temp.addproduct();
        }
    }

    private static void delete_product(){
        System.out.print("Id of category: ");
        int id = input.input_int();
        if (catalog.cat_id.contains(id)){
            category temp = catalog.categories.get(0);
            for (int i = 0; i < catalog.categories.size(); i++){
                if (catalog.categories.get(i).id == id){
                    temp = catalog.categories.get(i);
                    break;
                }
            }
            temp.removeproduct();
        }
    }

    private static void set_dicount(){
        System.out.println("Dear Admin give the Product ID you want to add discount for");
        System.out.print("Enter the Product ID :");
        double id = input.input_doube();
        int cat_id = (int) id;
        if (!catalog.cat_id.contains(cat_id)){
            System.out.println("Invalid Id");
            return;
        }
        category categ = catalog.categories.get(0);  
        for (int i = 0; i < catalog.categories.size(); i++){
            if (catalog.categories.get(i).id == cat_id){
                categ = catalog.categories.get(i);
            }
        }
        if (!categ.pro_id.contains(id)){
            System.out.println("Invalid ID");
            return;
        }
        product temp = categ.products.get(0);
        for (int i = 0; i < categ.products.size(); i++){
            if (categ.products.get(i).id == id){
                temp = categ.products.get(i);
            }
        }
        System.out.print("Enter discount for Elite, Prime and Normal customers respectively (in % terms)");
        temp.elite_dis = input.input_int();
        temp.prime_dis = input.input_int();
        temp.normal_dis = input.input_int();
        }

    private static void add_giveaway(){
        System.out.print("Give id of deal");
        int deal_id = input.input_int();
        if (catalog.deal_id.contains(deal_id)){
            System.out.println("Id already exist");
            return;
        }
        System.out.print("ID of product 1: ");
        double id1 = input.input_doube();
        System.out.print("ID of product 2: ");
        double id2 = input.input_doube();
        int cat_id = (int) id1;
        if (!catalog.cat_id.contains(cat_id)){
            System.out.println("Invalid Id");
            return;
        }
        category categ = catalog.categories.get(0);  
        for (int i = 0; i < catalog.categories.size(); i++){
            if (catalog.categories.get(i).id == cat_id){
                categ = catalog.categories.get(i);
            }
        }
        if (!categ.pro_id.contains(id1)){
            System.out.println("Invalid ID");
            return;
        }
        product p1 = categ.products.get(0);
        for (int i = 0; i < categ.products.size(); i++){
            if (categ.products.get(i).id == id1){
                p1 = categ.products.get(i);
            }
        }

        cat_id = (int) id2;
        if (!catalog.cat_id.contains(cat_id)){
            System.out.println("Invalid Id");
            return;
        }
        categ = catalog.categories.get(0);  
        for (int i = 0; i < catalog.categories.size(); i++){
            if (catalog.categories.get(i).id == cat_id){
                categ = catalog.categories.get(i);
            }
        }
        if (!categ.pro_id.contains(id2)){
            System.out.println("Invalid ID");
            return;
        }
        product p2 = categ.products.get(0);
        for (int i = 0; i < categ.products.size(); i++){
            if (categ.products.get(i).id == id2){
                p2 = categ.products.get(i);
            }
        }
        System.out.print("Give new combined price: ");
        double price = input.input_doube();
        deal d = new deal(deal_id, p1, p2, price);
        catalog.deal_list.add(d);
    }
}

class product{
    String name;
    Double id;
    Double price;
    category cat;
    HashMap<String, String> properties;
    int normal_dis;
    int prime_dis;
    int elite_dis;

    public product(String name, Double id, Double price, category cat){
        this.cat = cat;
        this.id = id;
        this.name = name;
        this.price = price;
        this.cat.products.add(this);
        this.cat.pro_id.add(this.id);
        this.properties = new HashMap<String,String>();
        System.out.print("Enter no of extra properties: ");
        int  no = input.input_int();
        for (int i = 0; i < no; i++){
            String prop = input.input_str();
            int n = prop.indexOf(":");
            String prop_id = prop.substring(0, n);
            String prop_p = prop.substring(n+2, prop.length() + 1);
            this.properties.put(prop_id, prop_p);
        }

    }

    void print(){
        System.out.println(this.name + "Rs" + this.price  + " " + this.id);
    }

}

class category{
    int id;
    String name;
    ArrayList<product> products;
    ArrayList<Double> pro_id;

    public category(String name, int id){
        catalog.cat_id.add(id);
        this.id = id;
        this.name = name;
        products = new ArrayList<product>();
        pro_id = new ArrayList<Double>();
    }

    void print(){
        System.out.println(this.name);
        System.out.println("Product in this range are:- ");
        for (int i = 0; i < this.products.size(); i++){
            this.products.get(i).print();
        }
    }

    void addproduct(){
        System.out.print("Product Id: ");
        Double pro_id = input.input_doube();
        if (this.pro_id.contains(pro_id)){
            System.out.println("Id already exist!!");
            return;
        }
        System.out.print("Product Name: ");
        String pro_name = input.input_str();
        System.out.print("Product Price: ");
        Double pro_price = input.input_doube();
        new product(pro_name, pro_id, pro_price,this);
    }

    void removeproduct(){
        System.out.print("Product Id: ");
        Double id = input.input_doube();
        if (!this.pro_id.contains(id)){
            System.out.println("Id doesnot exist!!");
            return;
        }
        product temp = this.products.get(0);
            for (int i = 0; i < this.products.size(); i++){
                if (this.products.get(i).id == id){
                    temp = this.products.get(i);
                    break;
                }
            }
        this.products.remove(temp); 
    }
}

class Coupon{
    double amount;
    String name;

    public Coupon(String name,double amount){
        this.amount = amount;
        this.name = name;
    }
    void print(){
        System.out.println(this.name + " Rs " + this.amount);
    }
}

class deal{
    int id;
    product p1;
    product p2;
    Double price;
    public deal(int id, product p1,product p2,Double price){
        this.id = id;
        this.p1 = p1;
        this.p2 = p2;
        this.price = price;
    }

    void print(){
        System.out.println("Combined prices for " + this.p1 + " " + this.p2 + "is Rs" + this.price);
    }
}

class catalog{
    static ArrayList<category> categories = new ArrayList<category>();
    static ArrayList<Integer> cat_id = new ArrayList<Integer>();
    static ArrayList<Integer> deal_id = new ArrayList<Integer>();
    static ArrayList<deal> deal_list = new ArrayList<deal>();
    static void print(){
        System.out.println("Categories: ");
        for (int i = 0; i < categories.size(); i ++ ){
            categories.get(i).print();
        }
        for (int i = 0; i < deal_list.size(); i ++ ){
            deal_list.get(i).print();
        }
    }
}


interface terminal{
    private static void login_normal(normal ankush,String passward){
        if (!ankush.passward.equals(passward)){
            System.out.println("Wrong Passward");
            return;
        }
        ankush.common();
    }

    private static void login_prime(prime ankush,String passward){
        if (!ankush.passward.equals(passward)){
            System.out.println("Wrong Passward");
            return;
        }
        ankush.common();
    }

    private static void login_elite(elite ankush,String passward){
        if (!ankush.passward.equals(passward)){
            System.out.println("Wrong Passward");
            return;
        }
        ankush.common();
    }

    private static void login_in(){
        System.out.print("Enter name");
        String name = input.input_str();
        System.out.print("Enter password");
        String passward = input.passward();
        boolean flag = false; 
        for (int i = 0; i < Main.normal_customer.size(); i++){
            if (Main.normal_customer.get(i).name.equals(name)){
                login_normal(Main.normal_customer.get(i),passward);
                flag = true;
                break;
            }
        }
        for (int i = 0; i < Main.prime_customer.size(); i++){
            if (Main.prime_customer.get(i).name.equals(name)){
                login_prime(Main.prime_customer.get(i),passward);
                flag = true;
                break;
            }
        }
        for (int i = 0; i < Main.elite_customer.size(); i++){
            if (Main.elite_customer.get(i).name.equals(name)){
                login_elite(Main.elite_customer.get(i),passward);
                flag = true;
                break;
            }
        }
        if (flag == false){
            System.out.println("Wrong Name !!!");
        }
    }

    private static void enter(){
        while(true){
            System.out.println("1) Sign up");
            System.out.println("2) Log in");
            System.out.println("3) Back");
            System.out.print("Input: ");
            int third = input.input_int();
            if (third == 1){
                System.out.print("Enter name: ");
                String name = input.input_str();
                System.out.print("Enter Passward: ");
                String passward = input.passward();
                System.out.print("Enter age: ");
                Integer age = input.input_int(); 
                System.out.print("Enter Phone No: ");
                Integer phone_number = input.input_int(); 
                System.out.print("Enter Email-Id: ");
                String email_id = input.input_str();
                if (Main.email.contains(email_id)){
                    System.out.println("Already Registered !!!");
                    return;
                }
                normal ankush = new normal(name, passward, age, phone_number, email_id);
                System.out.println("Registraion Successful !!!!");
                Main.normal_customer.add(ankush);            
            }
            else if (third == 2){
                login_in();
            }
            else if (third == 3){
                break;
            }
        }
    }

    static void welcome(){
        while(true){
            System.out.println("WELCOME TO FLIPZON");
            System.out.println("1) Enter as Admin"); // done
            System.out.println("2) Explore the Product Catalog");
            System.out.println("3) Show Available Deals");
            System.out.println("4) Enter as Customer");
            System.out.println("5) Exit the Application");
            System.out.print("Input: ");
            int first = input.input_int();
            if (first == 1){
                admin.login();
            }
            else if (first == 2){
                catalog.print();
            }
            else if (first == 3){
                System.out.println("All avaiable deals are:- ");
                for (int i = 0; i < catalog.deal_list.size(); i++){
                    catalog.deal_list.get(i).print();
                }
            }
            else if (first == 4){
                enter();
            }
            else if (first == 5){
                System.out.println("Thanks for using FLIPZON");
                input.close();
                break;
            }
        }
    }
}


public class Main{
    static ArrayList<normal> normal_customer = new ArrayList<normal>();
    static ArrayList<elite> elite_customer = new ArrayList<elite>();
    static ArrayList<prime> prime_customer = new ArrayList<prime>();
    static ArrayList<String> email = new ArrayList<String>();
    public static void main(String[] args){
        terminal.welcome();
    }
}