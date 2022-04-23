package com.citi.FeedGenerator.repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.citi.FeedGenerator.dto.AllTransactionFile;
import com.citi.FeedGenerator.dto.Person;
import com.citi.FeedGenerator.dto.Transaction;


public class RepositoryImpl implements Repository {

	@Override
	public ArrayList<Transaction> readFile(AllTransactionFile file) {
		//returns array list containing all Transactions from file in object Transaction form
		
		ArrayList<Transaction> AllTransactionData = new ArrayList<>();
		String path = "src/main/resources/uploadedFiles/"+file.getName();
		BufferedReader bufReader;
		
		try {
			bufReader = new BufferedReader(new FileReader(path));
			ArrayList<String> listOfLines = new ArrayList<>();
			
			String line;
			line = bufReader.readLine();
			while (line != null) {
			      line = line + " "+bufReader.readLine();
			      listOfLines.add(line);
			      line = bufReader.readLine();
			    }
			
		    for (String i: listOfLines) {
		    	System.out.println("line: "+i);
		    	String[] splitData = i.split(" ");
		    	
		    	Transaction transaction = new Transaction();
		    	
		    	try {
		    		String refNo = splitData[0].substring(0, 12);
					
		    		Date date = new SimpleDateFormat("ddmmyyy").parse(splitData[0].substring(12,20));
		    		
		    		System.out.println("Date "+splitData[0].substring(12,20));
					LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					
					Person payer = new Person();
					payer.setName(splitData[0].substring(20));
					payer.setAccount(splitData[1].substring(0,12));
					
					Person payee = new Person();
					payee.setName(splitData[1].substring(12));
					payee.setAccount(splitData[2]);
					
					double amount = Double.parseDouble(splitData[3]);
					
					transaction.setRefNo(refNo);
					transaction.setDate(localDate);
					transaction.setPayee(payee);
					transaction.setPayer(payer);	
					transaction.setAmount(amount);
					
					AllTransactionData.add(transaction);
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    }
		    bufReader.close();
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return AllTransactionData;
	}

	@Override
	public boolean isValid(Transaction transaction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<ArrayList<Transaction>> validate(ArrayList<Transaction> allTransactions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeValidFile(ArrayList<Transaction> validTransactions) {
		// TODO Auto-generated method stub
		
	}

}
