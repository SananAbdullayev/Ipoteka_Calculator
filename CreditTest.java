package ipoteka_calculator_test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CreditTest {
    private static CreditTest creditTest;
    private long id;
    private BigDecimal menzilinDeyeri;
    private BigDecimal ilkinOdenis;
    private BigDecimal kreditMeblegi;
    private BigDecimal umumiFaizMeblegi;
    private int muddet;
    private double illikFaiz;

    public CreditTest() {
    }

    public static CreditTest getInstance() {
        if (creditTest == null) {
            creditTest = new CreditTest();
        }
        return creditTest;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getMenzilinDeyeri() {
        return menzilinDeyeri;
    }

    public void setMenzilinDeyeri(BigDecimal menzilinDeyeri) {
        this.menzilinDeyeri = menzilinDeyeri;
    }

    public BigDecimal getIlkinOdenis() {
        return ilkinOdenis;
    }

    public void setIlkinOdenis(BigDecimal ilkinOdenis) {
        this.ilkinOdenis = ilkinOdenis;
    }

    public BigDecimal getKreditMeblegi() {
        return kreditMeblegi;
    }

    public void setKreditMeblegi(BigDecimal kreditMeblegi) {
        this.kreditMeblegi = kreditMeblegi;
    }

    public BigDecimal getUmumiFaizMeblegi() {
        return umumiFaizMeblegi;
    }

    public void setUmumiFaizMeblegi(BigDecimal umumiFaizMeblegi) {
        this.umumiFaizMeblegi = umumiFaizMeblegi;
    }

    public int getMuddet() {
        return muddet;
    }

    public void setMuddet(int muddet) {
        this.muddet = muddet;
    }

    public double getIllikFaiz() {
        return illikFaiz;
    }

    public void setIllikFaiz(double illikFaiz) {
        this.illikFaiz = illikFaiz;
    }
}
