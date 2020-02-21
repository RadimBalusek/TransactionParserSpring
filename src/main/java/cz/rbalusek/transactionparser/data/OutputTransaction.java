package cz.rbalusek.transactionparser.data;

import java.util.Objects;

public class OutputTransaction {

    private String partnerName;
    private String index;
    private String nameTransaction;

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getNameTransaction() {
        return nameTransaction;
    }

    public void setNameTransaction(String nameTransaction) {
        this.nameTransaction = nameTransaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutputTransaction)) return false;
        OutputTransaction that = (OutputTransaction) o;
        return partnerName.equals(that.partnerName) &&
                index.equals(that.index) &&
                nameTransaction.equals(that.nameTransaction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partnerName, index, nameTransaction);
    }

    @Override
    public String toString() {
        return "OutputTransaction{" +
                "partnerName='" + partnerName + '\'' +
                ", index='" + index + '\'' +
                ", nameTransaction='" + nameTransaction + '\'' +
                '}';
    }
}
