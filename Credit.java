package ipoteka_calculator;

import java.math.BigDecimal;

public class Credit {
    private static Credit credit;
    private BigDecimal menzilinDeyeri;
    private BigDecimal ilkinOdenis;
    private BigDecimal kreditMeblegi;
    private BigDecimal umumiFaizMeblegi;
    private int muddet;
    private double illikFaiz;

    public Credit() {
    }

    public static Credit instance() {
        if (credit == null) {
            credit = new Credit();
        }
        return credit;
    }

    public static Credit getCredit() {
        return credit;
    }

    public static void setCredit(Credit credit) {
        Credit.credit = credit;
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
