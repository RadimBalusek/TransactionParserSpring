package cz.rbalusek.transactionparser.service;

import cz.rbalusek.transactionparser.data.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/*
 Author: Radim Balusek
 */
@Service
public class ConvertFile {

    // Convert file to list of transaction

    private DateTimeUtils dateTimeUtils;

    @Autowired
    public ConvertFile(DateTimeUtils dateTimeUtils) {
        this.dateTimeUtils = dateTimeUtils;
    }

    public List<Transaction> convertFileToList(Path path) {
        List<Transaction> transactionList = new ArrayList<>();
        String line = "";
        try (BufferedReader reader = Files.newBufferedReader(path);) {
            while ((line = reader.readLine()) != null) {
                String[] lineVariables = line.split(",");
                Transaction newTransaction = new Transaction();
                for (String element : lineVariables) {
                    String singleElement = element.trim().replace("\"", "");
                    // check if element compare date
                    if (dateTimeUtils.dateMatches(singleElement)) {
                        newTransaction.setDate(dateTimeUtils.convertToDate(singleElement));
                    }
                    // check if element compare mobile number
                    if (dateTimeUtils.phoneMatches(singleElement)) {
                        newTransaction.setPartnerName(singleElement.replaceAll("\\s*?\\/{1}\\s*\\d{9}", "").trim());
                    }
                    // check if element don't compare mobile number and date
                    if (!dateTimeUtils.phoneMatches(singleElement) & !dateTimeUtils.dateMatches(singleElement)) {
                        // check if new transaction compare transaction name if yes concat new element + old element
                        // this condition we need for case when transaction name compare ,
                        if (newTransaction.getNameTransaction() == null) {
                            newTransaction.setNameTransaction(singleElement);
                        } else {
                           newTransaction.setNameTransaction(newTransaction.getNameTransaction().concat(", "+singleElement));
                        }
                    }
                }
                transactionList.add(newTransaction);
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactionList;
    }
}
