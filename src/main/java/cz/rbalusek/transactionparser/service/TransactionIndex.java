package cz.rbalusek.transactionparser.service;

import cz.rbalusek.transactionparser.data.Transaction;
import org.springframework.stereotype.Service;

import java.util.*;

/*
 Author: Radim Balusek
 */
@Service
public class TransactionIndex {

    private Integer index = 1;

    // calculate number of partner name according date
    public Map<String, Integer> sumTransaction(List<Transaction> transactionList) {

        Map<String, Integer> outputMap = new HashMap<>();
        Map<Date, String> unSortedMap = new HashMap<>();
        Map<String, Integer> partnerNameMap = new HashMap<>();
        LinkedHashMap<Date, String> sortedMap = new LinkedHashMap<>();
        int mapsize = outputMap.size();

        for (Transaction inTransaction : transactionList) {
            unSortedMap.put(inTransaction.getDate(), inTransaction.getPartnerName());
        }

        unSortedMap.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

        for (Map.Entry<Date, String> sortedMapIn : sortedMap.entrySet()) {
            String transactionKey = sortedMapIn.getKey() + sortedMapIn.getValue();
            String transactionValue = sortedMapIn.getValue();

            // store first value
            if (outputMap.size() == 0) {
                outputMap.put(transactionKey, index);
                partnerNameMap.put(transactionValue , index);
                mapsize = outputMap.size();
            }
            // test if the same value exist in map
            if (mapsize > 1 && compareElement(partnerNameMap, transactionValue)) {
                // if yes update index in temp partnerNameMap

                // first reindex map with partner name
                partnerNameMap = reindexPartnerMap(partnerNameMap, transactionValue);
                // after set index to output value
                outputMap.put(transactionKey, getPartnerMapIndex(partnerNameMap,transactionValue ));
            } else{
                outputMap.put(transactionKey, index);
                partnerNameMap.put(transactionValue , index);
            }
            mapsize++;
        }
        return outputMap;
    }

    private boolean compareElement(Map<String, Integer> testedMap, String transactionValue) {
        for (Map.Entry<String, Integer> in : testedMap.entrySet()) {
            if (in.getKey().contains(transactionValue)) {
                return true;
            }
        }
        return false;
    }

    private Map<String, Integer> reindexPartnerMap(Map<String, Integer> partnerMap, String inPartnerName) {

        Map<String, Integer> newPartnerNameMap = new HashMap<>(partnerMap);
        for (Map.Entry<String, Integer> in : newPartnerNameMap.entrySet()) {
          if(in.getKey().contains(inPartnerName)){
              in.setValue(in.getValue()+1);
          }
        }
        return newPartnerNameMap;
    }

    private Integer getPartnerMapIndex(Map<String, Integer> partnerMap, String inPartnerName) {
        int index = 0;
        for (Map.Entry<String, Integer> in : partnerMap.entrySet()) {
            if(in.getKey().contains(inPartnerName)){
                return in.getValue();
            }
        }
        return index;
    }
}