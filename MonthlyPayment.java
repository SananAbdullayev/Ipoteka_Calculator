package ipoteka_calculator;

import java.math.BigDecimal;

public class MonthlyPayment {
    private static MonthlyPayment monthlyPayment = null;
    private BigDecimal ayliqOdenis;

    public MonthlyPayment() {
    }

    public static MonthlyPayment instance(){
        if (monthlyPayment == null){
            monthlyPayment = new MonthlyPayment();
        }
        return monthlyPayment;
    }
    public BigDecimal getAyliqOdenis() {
        return ayliqOdenis;
    }

    public void setAyliqOdenis(BigDecimal ayliqOdenis) {
        this.ayliqOdenis = ayliqOdenis;
    }
}
