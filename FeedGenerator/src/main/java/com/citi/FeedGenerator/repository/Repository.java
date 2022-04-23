package com.citi.FeedGenerator.repository;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.citi.FeedGenerator.dto.AllTransactionFile;
import com.citi.FeedGenerator.dto.Transaction;

public interface Repository {
	public ArrayList<Transaction> readFile(AllTransactionFile file);
	public boolean isValid(Transaction transaction);
	public ArrayList<ArrayList<Transaction>> validate(ArrayList<Transaction> allTransactions);
	public void writeValidFile(ArrayList<Transaction> validTransactions);
}
