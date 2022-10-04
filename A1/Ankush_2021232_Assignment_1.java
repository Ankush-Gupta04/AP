import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Assignment 1 
//Future Builder
//Ankush Gupta
//2021232
//AP Section- A 
//Prof. Raghava sir


// declaration of student class of project

class input{
	static Scanner sc_int = new Scanner(System.in);
	static Scanner sc_double = new Scanner(System.in);
	static Scanner sc_string = new Scanner(System.in);
	public static Integer input_int(){
		Integer n = sc_int.nextInt();
		return n;
	}

	public static Double input_double(){
		Double n = sc_double.nextDouble();
		return n;
	}

	public static String input_str(){		String n = sc_string.nextLine();
		return n;
	}

	public static void close_sc(){
		sc_int.close();
		sc_double.close();
		sc_string.close();
	}
}


class Student{
	String Name;
	Integer Roll;
	Boolean placed;
	Double CGPA;
	Double CTC_hold;
	Boolean blocked;
	String branch;
	HashMap<Company,Double> offers;
	String status;
	Company Co;	

	public Student(String name, Integer roll, Double cgpa, String branch){
		this.Name = name;
		this.Roll = roll;
		this.placed = false;
		this.CGPA = cgpa;
		this.CTC_hold = 0.0;
		this.blocked = false;
		this.branch = branch;
		this.offers = new HashMap<Company,Double>();
		this.status = "not-offered";
		Company initial = new Company( "no-offer", "none", 0.0, 0.0);
		this.Co = initial;
	}

	public void  print(){
		System.out.println("Name: " + Name);
		System.out.println("Roll No: " + Roll);
		System.out.println("CGPA: " + CGPA);
		System.out.println("Branch: " + branch);
		System.out.println("Current CTC hold: " + CTC_hold);
		System.out.println("Status: " + status );
		System.out.println("Company offered" + Co.Name);
	}

	public void  register(){
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime myDateObj = LocalDateTime.now();  
		String formattedDate = myDateObj.format(myFormatObj); 
		String current_date = formattedDate.substring(0,10);
		String current_time = formattedDate.substring(11,16);
		boolean flag = true;

		if (Integer.parseInt(current_date.substring(6,10)) > Integer.parseInt(IIITD.close_stu_date.substring(6,10)) ){
			flag = false;
			// System.out.println(312);
		} // year
		if(Integer.parseInt(current_date.substring(6,10)) == Integer.parseInt(IIITD.close_stu_date.substring(6,10))){}
		else{
			if (Integer.parseInt(current_date.substring(3,5)) > Integer.parseInt(IIITD.close_stu_date.substring(3,5))){
				flag = false; // month
				// System.out.println(317);
			}
			if (Integer.parseInt(current_date.substring(3,5)) == Integer.parseInt(IIITD.close_stu_date.substring(3,5))){}
			else{
				if (Integer.parseInt(current_date.substring(0,2)) > Integer.parseInt(IIITD.close_stu_date.substring(0,2))){
					flag = false; // day
					// System.out.println(322);
				}
				if (Integer.parseInt(current_date.substring(0,2)) == Integer.parseInt(IIITD.close_stu_date.substring(0,2))){}
				else{
					if (Integer.parseInt(current_time.substring(0,2)) > Integer.parseInt(IIITD.close_stu_time.substring(0,2))){
						flag = false; // hour
						// System.out.println(327);
					}
					if (Integer.parseInt(current_time.substring(0,2)) == Integer.parseInt(IIITD.close_stu_time.substring(0,2))){}
					else{
						if (Integer.parseInt(current_time.substring(3,5)) > Integer.parseInt(IIITD.close_stu_time.substring(3,5))){
							flag = false; // minutes
							// System.out.println(332);
						}
					}
				}
			}
		}

		if (Integer.parseInt(current_date.substring(6,10)) < Integer.parseInt(IIITD.open_stu_date.substring(6,10))){
			flag = false;
			// System.out.println(341);
		}
		if(Integer.parseInt(current_date.substring(6,10)) == Integer.parseInt(IIITD.open_stu_date.substring(6,10))){}	
		else{
			if(Integer.parseInt(current_date.substring(3,5)) < Integer.parseInt(IIITD.open_stu_date.substring(3,5))){
				flag = false;
				// System.out.println(346);
			}
			if(Integer.parseInt(current_date.substring(3,5)) == Integer.parseInt(IIITD.open_stu_date.substring(3,5))){}
			else{
				if (Integer.parseInt(current_date.substring(0,2)) < Integer.parseInt(IIITD.open_stu_date.substring(0,2))){
					flag = false;
					// System.out.println(351);
				}
				if (Integer.parseInt(current_date.substring(0,2)) == Integer.parseInt(IIITD.open_stu_date.substring(0,2))){}
				else{
					if(Integer.parseInt(current_time.substring(0,2)) < Integer.parseInt(IIITD.open_stu_time.substring(0,2))){
						flag = false;
						// System.out.println(356);
					}
					if (Integer.parseInt(current_time.substring(0,2)) == Integer.parseInt(IIITD.open_stu_time.substring(0,2))){}
					else{
						if(Integer.parseInt(current_time.substring(3,5)) < Integer.parseInt(IIITD.open_stu_time.substring(3,5))){
							flag = false;
							// System.out.println(361);
						}
					}
				}
			}
		}

		if (flag == false){
			System.out.println("Registration is not open right now!!");
			return;
		}
		System.out.println(Name +" Registered for the Placement Drive at IIITD!!!!");
		System.out.println("Your details are:");
		System.out.println( "Name:"  + Name);
		System.out.println("RollNo: "+ Roll);
		System.out.println("CGPA: "+ CGPA);
		System.out.println("Branch: " + branch);
		IIITD.addStudent(this);
	}

	public void  regco(){
		if (IIITD.companies.isEmpty()){
			System.out.println("No companies Available");
			return;
		}
		System.out.println("Available Comapnies :");
		for (int count = 0; count < IIITD.companies.size() ; count++){
			System.out.println( (count + 1) +  " " + IIITD.companies.get(count).Name + " " + IIITD.companies.get(count).ctc_offer);
		}
		System.out.printf("%s", "Give index of Desired Company");
		Integer co_no = input.input_int() - 1;
		Boolean if_registered = IIITD.companies.get(co_no).if_reg(this);
		if (if_registered){
			System.out.println("You have already registerd this company");
		}
		else{
			IIITD.companies.get(co_no).register_stu(this);
		}
	}

	private Company get_key(Double value){
		Iterator<Entry<Company,Double>> iterate1 = offers.entrySet().iterator();
		while (iterate1.hasNext()){
			Company temp = iterate1.next().getKey();
			if (temp.ctc_offer == value){
				return temp;
			}
		}
		Company cc = new Company("none","none",0.0,0.0);
		return cc;
	}

	public void accept_offer(){
		Map<Company,Double> max_found = offers;
		if (max_found.isEmpty()){
			System.out.println("No, offers available");
			return;
		}
		Double max = Collections.max(max_found.values());
		Company temp =  get_key(max);
		offers.remove(temp);
		if (CTC_hold != 0.0){
			Co.selected.remove(this);
		}
		temp.select(this);
		System.out.println("Congratulations, You accepted the offer of " + CTC_hold + " LPA by" + temp.Name);

	}

	public void reject_offer(){
		Map<Company,Double> max_found = offers;
		if (max_found.isEmpty()){
			System.out.println("No, offers available");
			return;
		}
		System.out.println("All avaiable offers are: ");
		Iterator<Entry<Company,Double>> iterate1 = offers.entrySet().iterator();
		while (iterate1.hasNext()){
			System.out.println(iterate1.next().getKey().Name + " :" + iterate1.next().getKey().ctc_offer);
		}
		System.out.printf("%s","Give index of offer that has to be rejected");
		Integer re = input.input_int();
		int count = 1;
		Company temp;
		Iterator<Entry<Company,Double>> iterate2 = offers.entrySet().iterator();
		while (iterate2.hasNext()){
			if (count == re){
				temp = iterate1.next().getKey();
				offers.remove(temp);
				break;
			}
			count++;
		}
		if (offers.isEmpty() && CTC_hold == 0){
			System.out.println("You are not holding any offer and you also have reject all available offers");
			System.out.println("You are Blocked!!");
			blocked = true;
		}
	}
}

// declaration of company class of project
class Company{
	String Name;
	ArrayList<Student> registered;
	Double required_Cg;
	Double ctc_offer;
	String post;
	ArrayList<Student> selected;

	public Company(String name, String role, Double ctc, Double cgpa){
		this.Name = name;
		this.registered = new ArrayList<Student>();
		this.required_Cg = cgpa;
		this.ctc_offer = ctc;
		this.post = role;
		this.selected = new ArrayList<Student>();
	}

	public void print(){
		System.out.println("Name: " + Name);
		System.out.println("CTC offered: " + ctc_offer);
		System.out.println("CGPA required: " + required_Cg);
		System.out.println("Role offered: " + post);
		System.out.println("No of students offered: " + selected.size());
	}

	public Boolean if_reg(Student su){
		return registered.contains(su);
	}


	private Boolean check_eligible(Student su){
		Boolean flag = true;
		if (su.CGPA < required_Cg){
			flag = false;
		}
		if (su.CTC_hold > 3*ctc_offer){
			flag = false;
		}
		if (su.placed){
			flag = false;
		}
		if (su.blocked){
			flag = false;
		}
		return flag;
	}

	public void register_stu(Student su){

		Boolean eligible = check_eligible(su);
		if (eligible){
			registered.add(su);
			System.out.println("Succesfull, Registered");
			IIITD.offered.add(su);
			registered.add(su);
			su.offers.put(this, ctc_offer);
			return;
		}
		System.out.println("Sorry, not eligible");
	}

	public void select(Student su){
		selected.add(su);
		su.status = "Offered";
		su.CTC_hold = ctc_offer;
	}

	public void registertodrive(){
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime myDateObj = LocalDateTime.now();  
		String formattedDate = myDateObj.format(myFormatObj); 
		String current_date = formattedDate.substring(0,10);
		String current_time = formattedDate.substring(11,16);
		boolean flag = true;

		if (Integer.parseInt(current_date.substring(6,10)) > Integer.parseInt(IIITD.close_com_date.substring(6,10)) ){
			flag = false;
			// System.out.println(312);
		} // year
		if(Integer.parseInt(current_date.substring(6,10)) == Integer.parseInt(IIITD.close_com_date.substring(6,10))){}
		else{
			if (Integer.parseInt(current_date.substring(3,5)) > Integer.parseInt(IIITD.close_com_date.substring(3,5))){
				flag = false; // month
				// System.out.println(317);
			}
			if (Integer.parseInt(current_date.substring(3,5)) == Integer.parseInt(IIITD.close_com_date.substring(3,5))){}
			else{
				if (Integer.parseInt(current_date.substring(0,2)) > Integer.parseInt(IIITD.close_com_date.substring(0,2))){
					flag = false; // day
					// System.out.println(322);
				}
				if (Integer.parseInt(current_date.substring(0,2)) == Integer.parseInt(IIITD.close_com_date.substring(0,2))){}
				else{
					if (Integer.parseInt(current_time.substring(0,2)) > Integer.parseInt(IIITD.close_com_time.substring(0,2))){
						flag = false; // hour
						// System.out.println(327);
					}
					if (Integer.parseInt(current_time.substring(0,2)) == Integer.parseInt(IIITD.close_com_time.substring(0,2))){}
					else{
						if (Integer.parseInt(current_time.substring(3,5)) > Integer.parseInt(IIITD.close_com_time.substring(3,5))){
							flag = false; // minutes
							// System.out.println(332);
						}
					}
				}
			}
		}

		if (Integer.parseInt(current_date.substring(6,10)) < Integer.parseInt(IIITD.open_com_date.substring(6,10))){
			flag = false;
			// System.out.println(341);
		}
		if(Integer.parseInt(current_date.substring(6,10)) == Integer.parseInt(IIITD.open_com_date.substring(6,10))){}	
		else{
			if(Integer.parseInt(current_date.substring(3,5)) < Integer.parseInt(IIITD.open_com_date.substring(3,5))){
				flag = false;
				// System.out.println(346);
			}
			if(Integer.parseInt(current_date.substring(3,5)) == Integer.parseInt(IIITD.open_com_date.substring(3,5))){}
			else{
				if (Integer.parseInt(current_date.substring(0,2)) < Integer.parseInt(IIITD.open_com_date.substring(0,2))){
					flag = false;
					// System.out.println(351);
				}
				if (Integer.parseInt(current_date.substring(0,2)) == Integer.parseInt(IIITD.open_com_date.substring(0,2))){}
				else{
					if(Integer.parseInt(current_time.substring(0,2)) < Integer.parseInt(IIITD.open_com_time.substring(0,2))){
						flag = false;
						// System.out.println(356);
					}
					if (Integer.parseInt(current_time.substring(0,2)) == Integer.parseInt(IIITD.open_com_time.substring(0,2))){}
					else{
						if(Integer.parseInt(current_time.substring(3,5)) < Integer.parseInt(IIITD.open_com_time.substring(3,5))){
							flag = false;
							// System.out.println(361);
						}
					}
				}
			}
		}

		if (flag == false){
			System.out.println("Registration is not open right now!!");
			return;
		}

		IIITD.add_co(this);
	}

	public void result(){
		if (selected.size() == 0){
			System.out.println("No student is either selected or offered.");
			return;
		}
		Iterator<Student> iter = selected.iterator();
		System.out.println("Following students are selected by company:");
		while (iter.hasNext()) {
			System.out.println("\n");
        	iter.next().print();
        }
	}

}

// declaration of IIITD class of project
class IIITD{
	public static ArrayList<Student> registered = new ArrayList<Student>();
	public static ArrayList<Company> companies = new ArrayList<Company>();
	public static ArrayList<Student> offered = new ArrayList<Student>();
	public static ArrayList<Student> blocked = new ArrayList<Student>();
	public static String open_stu_date;
	public static String open_stu_time;
	public static String close_stu_date;
	public static String close_stu_time;
	public static String open_com_date;
	public static String open_com_time;
	public static String close_com_date;
	public static String close_com_time;


	public static void addStudent(Student su){
		registered.add(su);
		su.status = "Applied";
	}

	public static void add_co(Company su){
		companies.add(su);
	}

	public static void student_register(){
		System.out.println("Fill in the details:-");
		System.out.printf("%s","1) Set the Opening date (in format dd-mm-yyyy) and time (in 24 hrs format)for company registrations : ");
		String dat = input.input_str();
		open_stu_date = dat.substring(0,10);
		open_stu_time = dat.substring(11, 16);
		System.out.print("2) Set the Closing time for student registrations: ");
		String dat1 = input.input_str();
		close_stu_date = dat1.substring(0,10);
		close_stu_time = dat1.substring(11,16);
	}


	public static void co_register(){
		System.out.println("Fill in the details:-");
		System.out.printf("%s","1) Set the Opening date (in format dd-mm-yyyy) and time (in 24 hrs format)for company registrations : ");
		String dat = input.input_str();
		open_com_date = dat.substring(0,10);
		open_com_time = dat.substring(11, 16);
		System.out.print("2) Set the Closing time for company registrations: ");
		String dat1 = input.input_str();
		close_com_date = dat1.substring(0,10);
		close_com_time = dat1.substring(11, 16);
	}

	public static void av_package(){
		if (registered.size() == 0){
			System.out.println("No students registered");
			return;
		}
		Iterator<Student> iter = registered.iterator();
		Double total = 0.0;
		while (iter.hasNext()) {
            total += iter.next().CTC_hold;
        }
		System.out.println("The avearge package of clg is : " + total/(registered.size()) + "LPA");
	}

	public static void process_result(Company co){
		String name;
		name= input.input_str();
		Iterator<Company> iter = companies.iterator();
		while (iter.hasNext()) {
            if (name == iter.next().Name){
				iter.next().result();
				return;
			}
        }
		System.out.println("No company with this name registered.");
	}
}





// declaration of main class of project
public class Ankush_2021232_Assignment_1 {
	// Creating ArrayList and hashtable of objects
	static HashMap<Student, Integer> student_hash = new HashMap<>();
	static HashMap<Company, String> company_array = new HashMap<>();

	public static void print_available(){
		System.out.println("All available Companies are:- ");
		for (int count = 0; count < IIITD.companies.size(); count++ ){
			System.out.println((count + 1));
			System.out.println( "Company Name: " + IIITD.companies.get(count).Name);
			System.out.println( "Company role offering: " + IIITD.companies.get(count).post);
			System.out.println( "Company Package: " + IIITD.companies.get(count).ctc_offer + " LPA");
			System.out.println( "Company CGPA criteria: " + IIITD.companies.get(count).required_Cg);
		}
	}
	
	public static void open_student(Student test_stu){
		while (true){	
			System.out.println("Welcome, " + test_stu.Name);
			System.out.println("1) Register For Placement Drive");
			System.out.println("2) Register For Company");
			System.out.println("3) Get All available companies");
			System.out.println("4) Get Current Status");
			System.out.println("5) Update CGPA");
			System.out.println("6) Accept offer");
			System.out.println("7) Reject offer");
			System.out.println("8) Back");
			System.out.printf("Give input: ");
			Integer process_stu = input.input_int();
			if (process_stu == 1){
				if (IIITD.registered.contains(test_stu)){
					System.out.println("Already Registered");
				}
				else{
					test_stu.register();
				}
			}
			else if (process_stu == 2){
				test_stu.regco();
			}
			else if (process_stu == 3){
				print_available();
			}
			else if (process_stu == 4){
				System.out.println(test_stu.status);
			}
			else if (process_stu == 5){
				test_stu.CGPA = input.input_double();
			}
			else if (process_stu == 6){
				test_stu.accept_offer();
			}
			else if (process_stu == 7){
				test_stu.reject_offer();
			}
			else if (process_stu == 8){
				break;
			}
		}

	}

	public static void add_company(){
		String name;
		String role;
		Double ctc; 
		Double cgpa;
		System.out.println("Give Company details: ");
		System.out.printf("%s", "Name: ");
		name = input.input_str();
		System.out.printf("%s", "Role: ");
		role = input.input_str();
		System.out.printf("%s", "CTC offer: ");
		ctc = input.input_double();
		System.out.printf("%s", "CGPA Required: ");
		cgpa = input.input_double();
		Company cc = new Company(name,role,ctc,cgpa);
		company_array.put(cc, name);
	}

	public static void open_co(Company comp){
		while (true){
			System.out.println("Welcome, " + comp.Name);
			System.out.println("1) Update Role");
			System.out.println("2) Update Package");
			System.out.println("3) Update CGPA criteria");
			System.out.println("4) Register To Institute Drive");
			System.out.println("5) Back");
			int index = input.input_int();
			if (index == 1){
				comp.post = input.input_str();
			}
			if (index == 2){
				comp.ctc_offer = input.input_double();
			}
			if (index == 3){
				comp.required_Cg = input.input_double();
			}
			if (index == 4){
				comp.registertodrive();
			}
			if (index == 5){
				break;
			}
		}

	}

	public static  void choose_company(){
		Boolean flag = get_available();
		if (flag == false){
			return;
		}
		System.out.printf("%s","Give index ");
		int index = input.input_int();
		int count = 1;
		Company temp;
		Company temp1;
		Iterator<Entry<Company,String>> iterate1 = company_array.entrySet().iterator();
		while (iterate1.hasNext()){
			if (count == index){
				temp = iterate1.next().getKey();
				open_co(temp);
				break;
			}
			count++;
			temp1 = iterate1.next().getKey();
		}
	}

	public static Boolean get_available(){
		if (company_array.isEmpty()){
			System.out.println("No companies registered");
			return false;
		}

		Iterator<Entry<Company,String>> iterate1 = company_array.entrySet().iterator();
		while (iterate1.hasNext()){
			System.out.println(iterate1.next().getKey().Name);
		}
		return true;
	}

	public static Boolean get_available_stu(){
		if (student_hash.isEmpty()){
			System.out.println("No student registered");
			return false;
		}

		Iterator<Entry<Student,Integer>> iterate1 = student_hash.entrySet().iterator();
		while (iterate1.hasNext()){
			System.out.println(iterate1.next().getKey().Name);
		}
		return true;
	}

	public static void enter(){
		// infinte loop
		while (true){
			// print all available modes
			System.out.println("Choose The mode you want to Enter in:-");
			System.out.println("1) Enter as Student Mode");
			System.out.println("2) Enter as Company Mode");
			System.out.println("3) Enter as Placement Cell Mode");
			System.out.println("4) Return To Main Application");
			int x1 = input.input_int();
			System.out.println(x1);
			if (x1 == 1){
				// Student mode
				while (true){
					System.out.println("Choose the Student Query to perform-");
					System.out.println("1) Enter as a Student(Give Student Name, and Roll No.)");
					System.out.println("2) Add students");
					System.out.println("3) Back");
					int x2 = input.input_int();
					if (x2 == 1){
						Boolean flag = get_available_stu();
						if (flag == false){
							return;
						}
						System.out.printf("%s","Give index ");
						int index = input.input_int();
						System.out.println(index);
						int count = 1;
						Student temp;
						Student temp1;
						Iterator<Entry<Student,Integer>> iterate1 = student_hash.entrySet().iterator();
						while (iterate1.hasNext()){
							if (count == index){
								temp = iterate1.next().getKey();
								System.out.println(temp.Name);
								open_student(temp);
								break;
							}
							temp1 = iterate1.next().getKey();
							count++;
						}
					}
					else if (x2 == 2){
						Integer count;
						count = input.input_int();
						for (Integer i = 0; i < count; i++){
							System.out.println("Give details:");
							System.out.printf("%s","Name: ");
							String Name;
							Integer Roll;
							Name = input.input_str();
							System.out.printf("%s","Roll: ");
							Roll = input.input_int();
							boolean flag = student_hash.values().contains(Roll);
							if (flag){
								System.out.println("Already Registered to portal");
							}
							else{
								System.out.printf("%s","CGPA: ");
								String branch;
								Double cgpa;
								cgpa = input.input_double();
								System.out.printf("%s","Branch: ");
								branch = input.input_str();
								Student p1 = new Student(Name,Roll,cgpa,branch);
								student_hash.put(p1, Roll);
							}
						}
					}
					else{
						break;
					}
				}
			}
			else if (x1 == 2){
				// company mode
				while (true)
				{	System.out.println("Choose the Company Query to perform-");
					System.out.println("1) Add Company and Details");
					System.out.println("2) Choose Company");
					System.out.println("3) Get Available Companies");
					System.out.println("4) Back");
					int x3 = input.input_int();
					if (x3 == 1){
						add_company();
					}
					else if (x3 == 2){
						choose_company();
					}
					else if (x3 == 3){
						Boolean flag = get_available();
					}
					else{
						break;
					}}
			}
			else if (x1 == 3){
				// IIITD mode
				while (true){
					System.out.println("Welcome to IIITD Placement Cell");
					System.out.println("1) Open Student Registrations");
					System.out.println("2) Open Company Registrations");
					System.out.println("3) Get Number of Student Registrations");
					System.out.println("4) Get Number of Company Registrations");
					System.out.println("5) Get Number of Offered/Unoffered/Blocked Students");
					System.out.println("6) Get Student Details");
					System.out.println("7) Get Company Details");
					System.out.println("8) Get Average Package");
					System.out.println("9) Get Company Process Results");
					System.out.println("10) Back");
					System.out.printf("Input : ");
					int x4 = input.input_int();
					if (x4 == 1){
						IIITD.student_register();
					}
					if (x4 == 2){
						IIITD.co_register();
					}
					if (x4 == 3){
						System.out.println(IIITD.registered.size());
					}
					if (x4 == 4){
						System.out.println(IIITD.companies.size());
					}
					if (x4 == 5){
						System.out.printf("%s","offered : ");
						System.out.println(IIITD.offered.size());

						System.out.printf("%s","unoffered : ");
						System.out.println(IIITD.registered.size() - IIITD.offered.size() - IIITD.blocked.size());

						System.out.printf("%s","blocked : ");
						System.out.println(IIITD.blocked.size());
					}
					if (x4 == 6){
						Integer roll = input.input_int();
						Iterator<Entry<Student,Integer>> iterate2 = student_hash.entrySet().iterator();
						while (iterate2.hasNext()){
							if (iterate2.next().getKey().Roll == roll){
								iterate2.next().getKey().print();
							}
						}
					}
					if (x4 == 7){
						String name = input.input_str();
						Iterator<Entry<Company,String>> iterate2 = company_array.entrySet().iterator();
						while (iterate2.hasNext()){
							if (iterate2.next().getKey().Name == name){
								iterate2.next().getKey().print();
							}
						}
					}
					if (x4 == 8){
						IIITD.av_package();
					}
					if (x4 == 9){
						String name = input.input_str();
						Iterator<Entry<Company,String>> iterate2 = company_array.entrySet().iterator();
						while (iterate2.hasNext()){
							if (iterate2.next().getKey().Name == name){
								IIITD.process_result(iterate2.next().getKey());
							}
						}
			
					}
					if (x4 == 10){
						break;
					}
				}
			}
			else{
				// break this loop
				break;
			}
		}
	}
	
	// welcome
	public static void welcome() {
		// infinite loop till breaked
		while (true){
			System.out.println("Welcome to Future Builder ");
			System.out.println("1: Enter the Application");
			System.out.println("2: Exit the Application");
			int x = input.input_int();
			if (x == 1){
				enter();
			}
			else{
				System.out.println("Thanks for choosing Future Builder!!");
				input.close_sc();
				break;
			}
		}
	}

	//    declaration of main fn
	public static void main(String[] args) {
		// welcome
		welcome();
		return;
	}
}
