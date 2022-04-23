package com.citi.FeedGenerator.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.citi.FeedGenerator.dto.AllTransactionFile;
import com.citi.FeedGenerator.dto.Transaction;
import com.citi.FeedGenerator.repository.RepositoryImpl;

@Controller
public class TransactionController {
	
	@GetMapping("/")
    public String index() {
        return "index";
    }
	
	@PostMapping("/upload")
	public ResponseEntity<?> handleFileUpload( @RequestParam("file") MultipartFile file) {
		System.out.println("Here");
		String fileName = file.getOriginalFilename();
		System.out.println("File name "+fileName);
		try {
			Path path = Paths.get("src\\main\\resources\\uploadedFiles\\"+fileName).toAbsolutePath();
			file.transferTo(path.toFile());
			System.out.println("uploaded file");
		
			AllTransactionFile newFile = new AllTransactionFile();
			newFile.setName(fileName);
			RepositoryImpl rep = new RepositoryImpl();
			ArrayList<Transaction> allTransactions = rep.readFile(fileName);
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
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok("File uploaded");
	}
	
	@GetMapping("/uploadSuccessful")
	public String uploadSuccessful() {
		return "uploadSuccessful";
	}
	
//	public ResponseEntity<?> handleFileUpload( @RequestParam("file") MultipartFile file){
//		String fileName = file.getOriginalFilename();
//		System.out.println("File name "+fileName);
//		try {
//			file.transferTo(new File("src/main/resources/uploadedFiles/"+fileName));
//			System.out.println("uploaded file");
//		
//		} catch (IllegalStateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}

}
