package cz.rbalusek.transactionparser.service;

import cz.rbalusek.transactionparser.data.SumPartnerName;
import cz.rbalusek.transactionparser.data.Transaction;
import org.springframework.stereotype.Service;

import java.util.*;

/*
 Author: Radim Balusek
 */
@Service
public class TransactionIndex {

    private int apple = 1;
    private int netflix = 1;
    private int microsoft = 1;

    // calculate number of partner name according date
    public Map<String, Integer> sumTransaction(List<Transaction> transactionList) {
        SumPartnerName sumPartnerName = new SumPartnerName();

        Map<String, Integer> outputMap = new HashMap<>();
        Map<Date, String> unSortedMap = new HashMap<>();
        LinkedHashMap<Date, String> sortedMap = new LinkedHashMap<>();

        for (Transaction inTransaction : transactionList) {
            unSortedMap.put(inTransaction.getDate(), inTransaction.getPartnerName());
        }

        unSortedMap.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

        for (Map.Entry<Date, String> sortedMapIn : sortedMap.entrySet()) {

            if (sortedMapIn.getValue().equals(SumPartnerName.APPLE)) {
                sumPartnerName.setApple(apple++);
                outputMap.put(sortedMapIn.getKey() + sortedMapIn.getValue(), sumPartnerName.getApple());
            }
            if (sortedMapIn.getValue().equals(SumPartnerName.NETFLIX)) {
                sumPartnerName.setNetflix(netflix++);
                outputMap.put(sortedMapIn.getKey() + sortedMapIn.getValue(), sumPartnerName.getNetflix());
            }
            if (sortedMapIn.getValue().equals(SumPartnerName.MICROSOFT)) {
                sumPartnerName.setMicrosoft(microsoft++);
                outputMap.put(sortedMapIn.getKey() + sortedMapIn.getValue(), sumPartnerName.getMicrosoft());
            }
        }
        return outputMap;
    }
}
