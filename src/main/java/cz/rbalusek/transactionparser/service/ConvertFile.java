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
                for (int i = lineVariables.length - 1; i >= 0; i--) {
                    if (dateTimeUtils.matches(lineVariables[i].trim())) {
                        newTransaction.setDate(dateTimeUtils.convertToDate(lineVariables[i]));
                    }
                    if (lineVariables[i].contains("/")) {
                        newTransaction.setPartnerName(lineVariables[i].replaceAll("\\s*?\\/{1}\\s*\\d{9}", "").trim());
                    }
                    if (lineVariables[i].contains("/") | !dateTimeUtils.matches(lineVariables[i].trim())) {

                        if (lineVariables[i].contains("\"O2TV")){
                            newTransaction.setNameTransaction("O2TV, SportTV");
                        }else{
                        newTransaction.setNameTransaction(lineVariables[i].trim().replace("\"",""));}
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
