package cz.rbalusek.transactionparser.data;

import java.util.Date;
import java.util.Objects;

public class Transaction {

    private String nameTransaction; //  example:  payment weekly
    private String partnerName; // example: Microsoft /602333666,
    private Date date; // 2013-09-05 14:08:15

    public String getNameTransaction() {
        return nameTransaction;
    }

    public void setNameTransaction(String nameTransaction) {
        this.nameTransaction = nameTransaction;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return nameTransaction.equals(that.nameTransaction) &&
                partnerName.equals(that.partnerName) &&
                date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameTransaction, partnerName, date);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "nameTransaction='" + nameTransaction + '\'' +
                ", partnerName='" + partnerName + '\'' +
                ", date=" + date +
                '}';
    }


}
