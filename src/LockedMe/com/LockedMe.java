package LockedMe.com;

import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

public class LockedMe {
	
	public static void dirCreate() {
		Directory = System.getProperty("user.dir");
		createFile = new File(Directory+"/files");
		if(!createFile.exists()) 
			createFile.mkdirs();
		System.out.println("File Path " + Directory);
	}
	
	private static Scanner input;
	private static Scanner readDbFile;
	private static Scanner scar;
	private static UserDetails details;
	private static PrintWriter collect;
	private static File DBfile; 
	static String Directory;
	static File createFile;
	
	
	public static void main(String[] args) throws IOException {
		tools();
		welcomeMessage();
		mainMenu();
	    }

	public static void mainMenu() throws IOException {
		System.out.println();
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*         WELCOME TO MAIN MENU	        *");
		System.out.println("*					*");
		System.out.println("==========================================");
		
		System.out.println("\nSelect an option\n 1.Sign up\n 2.Sign in\n 3.Close the application\n");
	try {
		Scanner sc = new Scanner(System.in);
		int Option = sc.nextInt();
		
		switch(Option) {
		case 1: {signUp();}
		
		case 2: {signIn();}
		
		case 3:{System.exit(Option); break;}
		
		default:{ System.out.println("\n** Invalid Input, Try Again **\n"); mainMenu(); }
		
		}
		
		}catch(Exception e) {
		}
		System.out.println("\n** Invalid Input, Try Again **\n"); mainMenu(); 
	    }
	
	public static void signUp() throws IOException {
		
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   WELCOME TO REGISTRATION PAGE	*");
		System.out.println("*					*");
		System.out.println("==========================================");
	try {	
		System.out.println("\nEnter Username: \n");
		String username = input.next();
		while(readDbFile.hasNext()) {
			if(readDbFile.next().equals(username)) {
			System.out.println("\n** Username Already Exists **\n");
			signUp();
			}}
		details.setUserName(username);
		
		System.out.println("\nEnter Password : \n");
		String password = input.next();
		details.setPassword(password);
		
		System.out.println("\nUser registered successfully\n");
		
		collect.println(details.getUserName());
		collect.println(details.getPassword());
		
		collect.close();
		
		System.out.println("\n1.Return to Main Menu\n2.Close the application\n");
		Scanner sdk = new Scanner(System.in);
		int mt15 = sdk.nextInt();
		switch(mt15) {
		case 1: main(null);
		case 2: break;
		default: System.out.println("\n** Invalid Input **\n"); main(null);
		}
		}
	   catch(Exception e) {
		   signUp();
	   }
	   }
	   
	
	public static void signIn() throws IOException {
		System.out.println();
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*         WELCOME TO Login PAGE	        *");
		System.out.println("*					*");
		System.out.println("==========================================");
		try {	
			System.out.println("\nEnter Username : ");
			String inname = input.next();
			boolean found = false;
			while(readDbFile.hasNext() && !found) {
				if(readDbFile.next().equals(inname)) {
					System.out.println("\nEnter Pasword : ");
					String inpassword = input.next();
					if( readDbFile.next().equals(inpassword)) {
						System.out.println("\n----> User Logged in Successfully! <----\n");
					found = true;
	                dirCreate();
	                showMenu();
	                break;
					}}
				}
			if(!found) {
					System.out.println("\n** User not Found (or) Wrong Username or Password **\n");
					System.out.println("\n1.Return Main Menu\n2.Close the application\n");
					Scanner sc = new Scanner(System.in);
					int chance = sc.nextInt();
					switch(chance) {
					case 1:{ main(null);}break;
					case 2:{break; }
					default:{ System.out.println("\n** Invalid Input **");  main(null); break;}
					}
				}
		}catch (Exception e) {
		}
	}
	public static void showMenu() throws IOException {
		System.out.println("\n*******Entered Locker*******\n");
		System.out.println("\n1.List Files in Directory\n2.Add,Delete or Search File in Directory\n3.Close the application\n");
		try {
		Scanner sc = new Scanner(System.in);
		int option1 = sc.nextInt();
		switch(option1) {
		case 1:{ listFiles(); }
		try {
		Scanner sc1 = new Scanner(System.in);
		System.out.println("\n1.Return to Locker\n2.Close the application\n");
		int op = sc1.nextInt();
		switch(op) {
		case 1 :{ showMenu(); }
		case 2 :{ break; }
		default:{ System.out.println("\n** Invalid Input **\n"); showMenu();}
		}
		}catch(Exception e) {
			System.out.println("\n** Invalid Input **\n"); showMenu();
		}
		case 2:{ showOperations(); }
		
		case 3:{ System.exit(option1); }
		
		default:{ System.out.println("\n** Invalid Input **\n"); showMenu();}
		}}catch(Exception e) {
		}
		
	}
	
	public static void listFiles() {
	try {
		if(createFile.list().length==0) {
			System.out.println("\n** Folder is empty **\n");
		}else {
		System.out.println("\n**The Files Available in " + Directory + " are : \n");
		String[] lists = createFile.list();
		Arrays.sort(lists);
		for(String view : lists) {
			System.out.println(view);
		}
		}
	}catch(Exception e) {
		e.getMessage();
	}
	}
	
	public static void showOperations() throws IOException {
		
		System.out.println("\n1.Add New File\n2.Delete Existing File\n3.Search File\n4.Return to Locker\n");
		try {	
		Scanner sc = new Scanner(System.in);
		int option3 = sc.nextInt();
		
		switch(option3) {
		
		case 1:{ System.out.println("\nEnter File Name to Add : \n");
		Scanner sc2 = new Scanner(System.in);
		String Name = sc2.next().trim().toLowerCase(); 
		addFile(Name);
		showMenu();
		break;
		}
			case 2:{ System.out.println("\nEnter File Name to Delete : \n");
			Scanner sc4 = new Scanner(System.in);
			String name2 = sc4.next().trim();
			deleteFile(name2);
			showMenu();
			break;
			}
			case 3:{System.out.println("\nEnter the File Name to Check Status : \n");
			Scanner sc5 = new Scanner(System.in);
			String sc7 = sc5.next().trim();
			searchFile(sc7);
			showMenu();
			break;
			}
			case 4:{ showMenu();}
			default:{ System.out.println("\n** Invalid Input **\n");	}
			showOperations();
			break;
		}
	}catch(Exception e) {
		System.out.println("\n** Invalid Input **\n");
		showOperations();
	}
	}
	
	public static void addFile(String name) throws IOException {
		
		File filec = new File(createFile+"/"+name);
		String list1[] = createFile.list();
		for(String namecheck : list1) {
			if(name.equalsIgnoreCase(namecheck)) {
				System.out.println("\n** File already exists in the folder **\n");
			return;
			}
		}
		filec.createNewFile();
		boolean res = filec.createNewFile();
		if(!res) System.out.println("\n----> File Created Successfully <----\n");
		
	}
	
	public static void deleteFile(String name) {
		File file = new File(createFile+"/"+name);
		String[] list = createFile.list();
		for(String view1 : list) {
			if(name.equals(view1) && file.delete()) {
				System.out.println("\n----> File Deleted Successfully <----\n");
				return;
			}
		}
		System.out.println("\n** File Not Found **\n");
	}
	
	public static void searchFile(String Name) {
		File f2 = new File(createFile+"/"+Name); 
		String[] list = createFile.list();
		for(String str : list) {
			if(Name.equals(str)) {
				System.out.println("\n----> File Found <----");
				return;
			}
		}
		System.out.println("\n** File Not Found ))\n");
	} 
	
	public static void welcomeMessage() {
		System.out.println("*-----------------------------------*");
		System.out.println("||---------------------------------||");
		System.out.println("||********  LockedMe.com   ********||");
		System.out.println("||*****  Developer :- Sanjay  *****||");
		System.out.println("||---------------------------------||");
		System.out.println("*-----------------------------------*");
	    }
	
	public static void tools() throws IOException{
		DBfile = new File("UsersDB.txt");
		DBfile.createNewFile();
		
		try {	
			scar = new Scanner(System.in);
			input = new Scanner(System.in);
			readDbFile = new Scanner(DBfile);
			DBfile.createNewFile();
			collect = new PrintWriter(new FileWriter(DBfile,true));
			details = new UserDetails();
		}catch(Exception e) {
			System.out.println(e.getClass());
			System.out.println(e.getMessage());
		}
	    }
        }

