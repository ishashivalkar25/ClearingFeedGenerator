package com.citi.FeedGenerator;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.citi.FeedGenerator.dto.AllTransactionFile;
import com.citi.FeedGenerator.dto.Transaction;
import com.citi.FeedGenerator.repository.RepositoryImpl;

@SpringBootApplication
@ComponentScan
public class FeedGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedGeneratorApplication.class, args);
		
		AllTransactionFile file = new AllTransactionFile();
		file.setName("sampleFile.txt");
		RepositoryImpl rep = new RepositoryImpl();
		ArrayList<Transaction> allTransactions = rep.readFile(file);
		for (Transaction t: allTransactions) {
			System.out.println("Transaction id: "+ t.getRefNo());
			System.out.println("Transaction date: "+t.getDate());
			System.out.println("Payer name: "+t.getPayer().getName());
			System.out.println("Payer Accno: "+t.getPayer().getAccount());
			System.out.println("Payee name: "+t.getPayee().getName());
			System.out.println("Payee Accno: "+t.getPayee().getAccount());
			System.out.println("Amount: "+t.getAmount());
			System.out.println();
		}
	}

}
