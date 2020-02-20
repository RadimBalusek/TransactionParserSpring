package cz.rbalusek.transactionparser.data;

import java.util.Objects;

public class SumPartnerName {

    public static final String APPLE = "Apple";
    public static final String NETFLIX = "Netflix";
    public static final String MICROSOFT = "Microsoft";

    private int apple;
    private int netflix;
    private int microsoft;


    public int getApple() {
        return apple;
    }

    public void setApple(int apple) {
        this.apple = apple;
    }

    public int getNetflix() {
        return netflix;
    }

    public void setNetflix(int netflix) {
        this.netflix = netflix;
    }

    public int getMicrosoft() {
        return microsoft;
    }

    public void setMicrosoft(int microsoft) {
        this.microsoft = microsoft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SumPartnerName)) return false;
        SumPartnerName that = (SumPartnerName) o;
        return apple == that.apple &&
                netflix == that.netflix &&
                microsoft == that.microsoft &&
                APPLE.equals(that.APPLE) &&
                NETFLIX.equals(that.NETFLIX) &&
                MICROSOFT.equals(that.MICROSOFT);
    }

    @Override
    public int hashCode() {
        return Objects.hash(APPLE, NETFLIX, MICROSOFT, apple, netflix, microsoft);
    }

    @Override
    public String toString() {
        return "SumPartnerName{" +
                "APPLE='" + APPLE + '\'' +
                ", NETFLIX='" + NETFLIX + '\'' +
                ", MICROSOFT='" + MICROSOFT + '\'' +
                ", apple=" + apple +
                ", netflix=" + netflix +
                ", microsoft=" + microsoft +
                '}';
    }
}
