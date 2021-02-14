package ipoteka_calculator_test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MonthlyPaymentTest {
    private static MonthlyPaymentTest monthlyPayment;
    private BigDecimal ayliqOdenis;
    private Date odenisAylari;

    public MonthlyPaymentTest() {
    }

    public static MonthlyPaymentTest getInstance(){
        if (monthlyPayment == null){
            monthlyPayment = new MonthlyPaymentTest();
        }
        return monthlyPayment;
    }

    public Date getOdenisAylari() {
        return odenisAylari;
    }

    public void setOdenisAylari(Date odenisAylari) {
        this.odenisAylari = odenisAylari;
    }

    public BigDecimal getAyliqOdenis() {
        return ayliqOdenis;
    }

    public void setAyliqOdenis(BigDecimal ayliqOdenis) {
        this.ayliqOdenis = ayliqOdenis;
    }
}
