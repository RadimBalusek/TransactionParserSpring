package cz.rbalusek.transactionparser.service;

import cz.rbalusek.transactionparser.data.OutputTransaction;
import cz.rbalusek.transactionparser.data.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 Author: Radim Balusek
 */
@Service
public class BuildFinalList {

    private TransactionIndex calculateTransactionIndex;

    @Autowired
    public BuildFinalList(TransactionIndex calculateTransactionIndex) {
        this.calculateTransactionIndex = calculateTransactionIndex;
    }
    // create final list and validate output

    public void printOutputTransaction(List<OutputTransaction> transaction){
        for (OutputTransaction in : transaction) {
            System.out.println(in.getPartnerName()+in.getIndex()+in.getNameTransaction());
        }
    }

    public void resultListBuilder(List<Transaction> transaction) {
        Map<String, Integer> outputMap = calculateTransactionIndex.sumTransaction(transaction);
        List<OutputTransaction> output = new ArrayList<>();

        for (Transaction in : transaction) {
            OutputTransaction newTransaction = new OutputTransaction();
            newTransaction.setPartnerName(in.getPartnerName());
            newTransaction.setIndex("|" + findIndex(outputMap, in.getDate() + in.getPartnerName()) + "|");
            newTransaction.setNameTransaction(in.getNameTransaction());
            output.add(newTransaction);
        }

        printOutputTransaction(output);
    }

    private String findIndex(Map<String, Integer> outputMap, String key) {
        for (Map.Entry<String, Integer> entry : outputMap.entrySet()) {
            if (entry.getKey().equals(key)) {
                return validationValue(entry);
            }
        }
        return "";
    }

    private String validationValue(Map.Entry<String, Integer> entry) {
        // update netflix index
        if (entry.getKey().endsWith("Netflix") && entry.getValue() < 10) {
            String updateMicrosoft = "0" + entry.getValue().toString();
            return updateMicrosoft;
        } else {
            return entry.getValue().toString();
        }
    }
}

