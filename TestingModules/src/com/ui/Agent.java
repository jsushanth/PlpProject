package com.ui;

import java.io.BufferedReader;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import com.bean.AgentBean;
import com.bean.AgentDetails;
import com.bean.QuestionBean;

import com.bean.AgentViewPolicyBean;
import com.bean.Business;
import com.bean.CustomerDetails;
import com.bean.LoginBean;
import com.bean.NewPolicyBean;
import com.bean.PolicyDetails;
import com.bean.ProfileCreation;
import com.bean.AgentPolicyCreationBean;
import com.exception.AgentException;
import com.service.AgentService;
import com.service.AgentServiceIMPL;


public class Agent {

	static AgentService agentService=null;
	
	static QuestionBean questionBean=new QuestionBean();
	static AgentPolicyCreationBean agentPolicyCreationBean=new AgentPolicyCreationBean();
	static Scanner scanner=new Scanner(System.in);
	static AgentServiceIMPL agentServiceIMPL=new AgentServiceIMPL();
	public static void main(String[] args) {
	
		LoginBean loginBean=new LoginBean();
		AgentBean agentBean = null;
		String accountNumber=null;
		System.out.println("Welcome to Insurance Quote Generation Application");
		System.out.println("___________________________________________________\n");
		/*System.out.println("Enter the Username:");
		String username=scanner.next();
		loginBean.setUserName(username);
		System.out.println("Enter the Password");
		loginBean.setPassword(scanner.next());
		String role="";
		
				try {
					role=agentServiceIMPL.checkUser(loginBean);
				} catch (AgentException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
		System.out.println(role);*/
		String role="under";
		if(role.equals("Under") || role.equals("under"))
		{
			while(true)
			{
				System.out.println("______________________\n");
				System.out.println("Welcome Underwriter");
				System.out.println("______________________\n");
				System.out.println("1. New Profile Creation");
				System.out.println("2. Account Creation");
				System.out.println("3. Policy Creation");
				System.out.println("4. View Policy");
				System.out.println("5. Report Generation");
				System.out.println("6. Exit");
				System.out.println("Enter your choice");
				int adminChoice = scanner.nextInt();
				switch (adminChoice) {
				case 1:
					System.out.println("___________________________________\n");
					System.out.println("Enter Details for Creating profile");
					System.out.println("____________________________________\n");
					ProfileCreation profileCreation = null;

					while (profileCreation == null) {
						try {
							profileCreation = populateProfileCreation();
						} catch (AgentException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
						}

					}

					try {

						agentService = new AgentServiceIMPL();
						agentService.addProfile(profileCreation);
						System.out.println("Profile creation successfully completed!!");
						System.out.println("___________________________________________\n");

					} catch (Exception e) {

						e.printStackTrace();
					}
					break;
				case 2:
					while(agentBean==null)
					{
						try {
							try {
								populateAgentBean("ramana");
								agentService=new AgentServiceIMPL();
								agentService.accountCreation(agentBean);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								//e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								//e.printStackTrace();
							}
						} catch (AgentException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
						}
					}
					
					break;
				case 3:
					try {
						populateAgentBean("ramana");
					} catch (AgentException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					break;
				case 4:
					System.out.println("In view Policy");
					System.out.println("1. Check agent details");
					System.out.println("2. Check customer details");

						int option = 0;
						option = scanner.nextInt();

						switch (option) {

						case 1:

							 agentService = new AgentServiceIMPL();

							List<AgentDetails> arrayList = new ArrayList<>();
							try {
								arrayList = agentService.viewPolicy();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							int number = 0;

							for (AgentDetails agentDetails : arrayList) {

								number++;
								System.out.println(number + " " + agentDetails);
							}
							AgentDetails agent1 = new AgentDetails();
							System.out.println("enter the choice");
							int value = scanner.nextInt();
							List<CustomerDetails> customerArray = new ArrayList<>();
							for (int i = 0; i < arrayList.size(); i++) {
								if (i == (value - 1)) {
									System.out.println(arrayList.get(i));
									
									agent1 = arrayList.get(i);
									System.out.println(agent1.getAgentName());
									try {
										customerArray = agentService.viewCustomers();
										System.out.println(customerArray);
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
								}
							}
						
							break;
						case 2:
							agentService = new AgentServiceIMPL();

							
							List<CustomerDetails> arrayList1 = new ArrayList<CustomerDetails>();
							try {
								arrayList1 = agentService.customerDetails();
								for (CustomerDetails customerdetails : arrayList1) {

									System.out.println(" " + customerdetails);
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							
						}
					break;
				case 5:
					System.out.println("Report Generation");
					
					break;
				case 6:
					System.out.println("Welcome Admin");
					System.out.println("New Scheme Creation Page");
					NewPolicyBean newPolicySchemeBean=new NewPolicyBean();
					System.out.println("Enter New Policy Business Segment ID");
					newPolicySchemeBean.setBus_seg_id(scanner.nextInt());
					System.out.println("Enter New Policy Business Segment Name");
					newPolicySchemeBean.setBus_seg_name(scanner.next());
					System.out.println("Enter New Policy Question ID");
					newPolicySchemeBean.setPol_ques_id(scanner.nextInt());
					System.out.println("Enter New Question ");
					newPolicySchemeBean.setPol_ques_desc(scanner.next());
					System.out.println("Enter Answer-1 For Entered Question:");
					newPolicySchemeBean.setPol_ques_ans1(scanner.next());
					System.out.println("Enter Weightage for Answer-1 :");
					newPolicySchemeBean.setPol_ques_ans1_weightage(scanner.nextInt());
					System.out.println("Enter Answer-2 For Entered Question:");
					newPolicySchemeBean.setPol_ques_ans2(scanner.next());
					System.out.println("Enter Weightage for Answer-2 :");
					newPolicySchemeBean.setPol_ques_ans2_weightage(scanner.nextInt());
					System.out.println("Enter Answer-3 For Entered Question:");
					newPolicySchemeBean.setPol_ques_ans3(scanner.next());
					System.out.println("Enter Weightage for Answer-3 :");
					newPolicySchemeBean.setPol_ques_ans3_weightage(scanner.nextInt());
	
					agentService=new AgentServiceIMPL();
					agentService.createNewScheme(newPolicySchemeBean);
					System.out.println("New Policy Scheme Created Successfully");
					break;
				case 7:
					break;
				default:
					System.out.println("Enter correct Choice!!");
				}
			}
		}
/*		else if(role=="Agent" || role=="agent")
		{
			while(true)
			{
				System.out.println("______________________\n");
				System.out.println("Welcome Agent");
				System.out.println("______________________\n");
				System.out.println("1. Account Creation.");
				System.out.println("2. Policy Creation.");
				System.out.println("3. View policy.");
				System.out.println("4. Exit.");
				System.out.println("Enter your choice:");
				try
				{
					int agentChoice=scanner.nextInt();
					switch(agentChoice)
					{
					case 1:
						while(agentBean==null)
						{
							try {
								try {
									agentBean=populateAgentBean(username);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									//e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									//e.printStackTrace();
								}
							} catch (AgentException e) {
								// TODO Auto-generated catch block
								//e.printStackTrace();
							}
						}
						
						agentService=new AgentServiceIMPL();
						
						try {
							agentService.accountCreation(agentBean);
						} catch (AgentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						if(accountNumber==null)
						{
							System.out.println("___________________________\n");
							System.out.println("Account is not created!!");
							System.out.println("Try Again!!");
							System.out.println("___________________________\n");
						}
						else
						{
							System.out.println("___________________________\n");
							System.out.println("Account successfully created.");
							System.out.println("___________________________\n");
							accountNumber=null;
							agentService=null;
							agentBean=null;		
						}
						
						break;
					case 2: try {
							populateAgentBean(username);
						} catch (AgentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
						break;
					case 3:
						AgentViewPolicyBean agentViewPolicyBean=new AgentViewPolicyBean();
						System.out.println("Enter your unique agent name:");
						String agentName=scanner.next();
						try {
							
							agentViewPolicyBean=getPolicyDetails(agentName);
							
						} catch (AgentException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
						}
						if(agentViewPolicyBean!=null)
						{
							System.out.println("Policy Details of your customer");
							System.out.println("___________________________________________________\n");
							System.out.println("Insured Name:"+agentViewPolicyBean.getInsuredName());
							System.out.println("Policy Number: "+agentViewPolicyBean.getPolicyNumber());
							System.out.println("Policy Premium: "+agentViewPolicyBean.getPolicyPremium());
							System.out.println("Account No: "+agentViewPolicyBean.getAccountNumber());
							System.out.println("___________________________________________________\n");
							
						}
						if(agentViewPolicyBean==null)
						{
							System.out.println("___________________________________________________\n");
							System.out.println("Agent Name is invalid!!");
							System.out.println("___________________________________________________\n");
							System.exit(0);
						}
						break;
					case 4:
						System.out.println("Have a good Day!!");
						System.exit(0);
						break;
						default:
							System.out.println("____________________________________\n");
							System.out.println("You have entered a wrong choice!!");
							System.out.println("Try Again!!");
							System.out.println("_____________________________________\n");
							break;
					}
					
					}
					catch(InputMismatchException e)
					{
						System.out.println("___________________________________________\n");
						System.out.println("Please enter a numeric value, Try Again!!");
						System.out.println("____________________________________________\n");
						break;
					}
				}
		}
		else if(role=="User" || role=="user")
		{
			System.out.println("User Choice");
			System.out.println("1.Account Creation");
			System.out.println("2.View Policy");
			System.out.println("Enter the chocie ");
			int choice=scanner.nextInt();
			switch(choice) {
			case 1:
				try {
					populateAgentBean(username);
				} catch (AgentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				 //write a method for view policy
				
				break;
			}
			
		}
		else
		{
			System.out.println("You have entered wrong username and password!!");
		}*/
		
			
		
	}
	private static ProfileCreation populateProfileCreation() throws AgentException {

		ProfileCreation profileCreation = new ProfileCreation();

		System.out.println("____________________________________\n");
		System.out.println("Create a new profile");
		System.out.println("_____________________________________\n");
		System.out.println("Create username: ");
		profileCreation.setUserName(scanner.next());

		System.out.println("Create password: ");
		profileCreation.setPassword(scanner.next());
		
		System.out.println("Enter RoleCode: ");
		profileCreation.setRoleCode(scanner.next());
		
		return profileCreation;
	}
	private static AgentViewPolicyBean getPolicyDetails(String agentName) throws AgentException, SQLException, IOException {
		AgentViewPolicyBean agentViewPolicyBean=new AgentViewPolicyBean();
		agentService=new AgentServiceIMPL();
		agentViewPolicyBean=agentService.getPolicyDetails(agentName);
		return agentViewPolicyBean ;
	}
	
	private static void populateAgentBean(String username) throws AgentException, SQLException, IOException {
		
		agentService =new AgentServiceIMPL();
	
		Business business=new Business();
		
		System.out.println("Choose Your Business Segment:");
		System.out.println("1. Business Auto.");
		System.out.println("2. Restaurant.");
		System.out.println("3. Apartment.");
		System.out.println("4. General Merchant.");
		System.out.println("Enter your choice:");
		int premium=0;
		int businessSegmentChoice=scanner.nextInt();            
			List<QuestionBean> al = new ArrayList<>();
			switch (businessSegmentChoice) {
			case 1:
				business.setBusiness_Segment("Business Auto");
				al = agentService.createPolicy("Business_auto");
				
				
				 getDetails(al, username );
				break;
			case 2:business.setBusiness_Segment("Restaurant");
				al = agentService.createPolicy("Restaurant");
				getDetails(al, username);
				break;
			case 3:
				business.setBusiness_Segment("Apartment");
				al = agentService.createPolicy("Apartment");
				getDetails(al, username);
				break;
			case 4:
				business.setBusiness_Segment("General Merchant");
				al = agentService.createPolicy("general_merchant");
				getDetails(al, username);
				break;
			default:
				System.out.println("Please enter correct number");
				
				
			}
			
		
		
	}
	
	public static void  getDetails(List<QuestionBean> al, String username) throws IOException, AgentException, SQLException {
		Business businessVal=new Business();
		agentService=new AgentServiceIMPL();
		businessVal= agentService.CheckAccount(username,al.get(1).getBusinessSegment());
		if(businessVal.getBusiness_Segment().equals(al.get(1).getBusinessSegment())) {
			System.out.println("You have already taken policy on the particular business Segemnt");
			populateAgentBean(username);
		}
		else {
		AgentPolicyCreationBean agentBean=new AgentPolicyCreationBean();
		
		int premiumCal=0;
		for (QuestionBean pBean : al) {
			System.out.println(pBean.getQuestion());
			System.out.println(
					"1." + pBean.getAnswer1() + "\t" + "2." + pBean.getAnswer2() + "\t" + "3." + pBean.getAnswer3());
			System.out.println("enter the option");
			int option = scanner.nextInt();
			
			switch (option) {
			case 1:
				agentBean.setAnswer(pBean.getAnswer1());
				agentBean.setWeightage(pBean.getWeightage1());
				 premiumCal += pBean.getWeightage1();

				break;
			case 2:
				agentBean.setAnswer(pBean.getAnswer2());
				agentBean.setWeightage(pBean.getWeightage2());
				 premiumCal += pBean.getWeightage2();
				break;
			case 3:
				agentBean.setAnswer(pBean.getAnswer3());
				agentBean.setWeightage(pBean.getWeightage3());
				 premiumCal += pBean.getWeightage3();

			}
			agentBean.setQuestion(pBean.getQuestion());
			agentBean.setBusinessSegment(pBean.getBusinessSegment());
		
			agentBean.setUsername(username);
	

		}
		//al.get(1).getBusinessSegment();
		System.out.println("Your premium is:"+premiumCal);
        System.out.println("choose option to continue if premium is okay ");
        System.out.println("1.To continue");
        System.out.println("0.Return Back");
        int chocie=scanner.nextInt();
        switch(chocie) {
        case 1:
            if(businessVal.getUsername().equals(username)) {
            	agentService.policyCreation(agentBean);
            }
            else {
        	AgentBean agentBea=new AgentBean();
        	System.out.println("Enter Insured Name:");
    		String insuredBean=scanner.next();
    		agentBea.setInsuredName(insuredBean);
    		System.out.println("Enter Insured Street:");
    		String street=scanner.next();
    		agentBea.setInsuredStreet(street);
    		System.out.println("Enter Insured City:");
    		String city=scanner.next();
    		agentBea.setInsuredCity(city);
    		System.out.println("Enter Insured State:");
    		String state=scanner.next();
    	    agentBea.setInsuredState(state);
    		System.out.println("Enter Insured Zip:");
    		long zip=scanner.nextLong();
    		agentBea.setInsuredZip(zip);
    		agentBea.setBusinessSegment(al.get(1).getBusinessSegment());
    		
    		
    		System.out.println("enter account Number");
    		long accountNumber=scanner.nextLong();
    		agentBea.setAccountNumber(accountNumber);
    		
    		PolicyDetails policyDetails=new PolicyDetails();
    		
    		try {
				agentService.accountCreation(agentBea);
				policyDetails.setAccountNumber(accountNumber);
				policyDetails.setPremium(premiumCal);
				policyDetails.setPolicyNumber(generatePolicy());
				Long number=agentService.policy_Details(policyDetails);
				System.out.println("Your Policy Number is"+number);
				agentService.policyCreation(agentBean);
			} catch (AgentException e1) {
				
				e1.printStackTrace();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
       
            }
    		break;
        case 2:
			try {
				populateAgentBean(username);
			} catch (AgentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       	 break;
       	 
        }
		}
		

	}
	public static Long generatePolicy() throws IOException {
		FileReader fr = new FileReader("Policy_Number.txt");
		BufferedReader br = new BufferedReader(fr);
		int rd = Integer.parseInt(br.readLine());
		int inc = rd + 1;
		FileOutputStream fw = new FileOutputStream("Policy_Number.txt");
		fw.write(Integer.toString(inc).getBytes());
		fw.close();
		String date = "" + java.time.LocalDate.now();
		String yy = date.substring(2, 4);
		String mm = date.substring(5, 7);
		FileReader fr1 = new FileReader("Policy_Number.txt");
		BufferedReader br1 = new BufferedReader(fr1);
		int b = Integer.parseInt(br1.readLine());

		String s1 = null;
 
		if (b < 10)
			s1 = "000" + b;
		if (b > 10 && b < 100)
			s1 = "00" + b;
		if (b >= 100)
			s1 = "0" + b;  
		String id = yy + mm + s1;
		br1.close();
		fr1.close();
		br.close();
		fr.close();
        return Long.parseLong(id);
		
	}

}
