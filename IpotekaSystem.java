package ipoteka_calculator_test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class IpotekaSystem {

    public boolean apply(CustomerTest customer, IpotekaUtilTest ipotekaUtil, CreditTest creditTest) throws AgeNotEnoughException, GenderException {
        boolean result = true;
        Calendar calendar = Calendar.getInstance();
        Date customerDate = null;
        Date date = calendar.getTime();
        Calendar nextTime = Calendar.getInstance();
        nextTime.setTime(date);
        nextTime.add(Calendar.YEAR, 30);
        Date nextDate = nextTime.getTime();

        if (customer.getGender() == 'k') {
            calendar.setTime(customer.getBirthday());
            calendar.add(Calendar.YEAR, 65);
            customerDate = calendar.getTime();
        } else if (customer.getGender() == 'q') {
            calendar.setTime(customer.getBirthday());
            calendar.add(Calendar.YEAR, 62);
            customerDate = calendar.getTime();
        } else {
            //todo Exception
            throw new GenderException(
                    "Zəhmət olmasa cinsinizi düzgün daxil edin!!!",
                    customer.getId(),
                    customer.getGender()
            );
        }

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(customer.getBirthday());
        System.out.println(LocalDateTime.now().getYear() - calendar1.get(Calendar.YEAR));
        if (nextDate.compareTo(customerDate) > 0 || LocalDateTime.now().getYear() - calendar1.get(Calendar.YEAR) < 18) {
            System.out.println("ipoteka verile bilmez");
            throw new AgeNotEnoughException(
                    "Yaşınız uyğun deyil",
                    customer.getId(),
                    customer.getGender(),
                    customer.getBirthday()
            );
        }
        System.out.println("IPOTEKA Verile Biler");
        //todo Ilkin ödənişin hesablanması.
        //1. Tikinti üsulu = 1 - dirsə -> 30%
        //2. Tikinti üsulu = 2 - dirsə -> 15%
        int maxAmount;
        if (ipotekaUtil.getIpotekaUsulu() == 1) {
            maxAmount = 150000;
        } else {
            maxAmount = 100000;
        }
        if (ipotekaUtil.getTikintiIli() == 1) {
            double faiz = 0.3;
            if (creditTest.getMenzilinDeyeri().subtract(creditTest.getMenzilinDeyeri().multiply(BigDecimal.valueOf(faiz))).compareTo(BigDecimal.valueOf(maxAmount)) <= 0) {
                creditTest.setIlkinOdenis(creditTest.getMenzilinDeyeri().multiply(BigDecimal.valueOf(faiz)));
            } else {
                creditTest.setIlkinOdenis(creditTest.getMenzilinDeyeri().subtract(BigDecimal.valueOf(maxAmount)));
            }
        } else if (ipotekaUtil.getTikintiIli() == 2) {
            double faiz = 0.15;
            if (creditTest.getMenzilinDeyeri().subtract(creditTest.getMenzilinDeyeri().multiply(BigDecimal.valueOf(faiz))).compareTo(BigDecimal.valueOf(maxAmount)) <= 0) {
                creditTest.setIlkinOdenis(creditTest.getMenzilinDeyeri().multiply(BigDecimal.valueOf(faiz)));
            } else {
                creditTest.setIlkinOdenis(creditTest.getMenzilinDeyeri().subtract(BigDecimal.valueOf(maxAmount)));
            }
        }
        //todo Kredit Mebleginin hesablanmasi
        creditTest.setKreditMeblegi(creditTest.getMenzilinDeyeri().subtract(creditTest.getIlkinOdenis()));

        //todo Illik Faiz derecesinin hesablanmasi
        if (ipotekaUtil.getIpotekaUsulu() == 1) {
            if (ipotekaUtil.getVerilmeUsulu() == 1) {
                creditTest.setIllikFaiz(7);
            } else if (ipotekaUtil.getVerilmeUsulu() == 2) {
                creditTest.setIllikFaiz(8);
            }
        } else if (ipotekaUtil.getIpotekaUsulu() == 2) {
            if (ipotekaUtil.getVerilmeUsulu() == 1) {
                creditTest.setIllikFaiz(3.7);
            } else if (ipotekaUtil.getVerilmeUsulu() == 2) {
                creditTest.setIllikFaiz(4);
            }
        }
        MonthlyPaymentTest monthlyPayment = MonthlyPaymentTest.getInstance();
        //todo Ayliq Odenisin Tapilmasi
        monthlyPayment.setAyliqOdenis((creditTest.getKreditMeblegi().multiply(BigDecimal.valueOf(creditTest.getIllikFaiz() / (12 * 100))).multiply(
                BigDecimal.valueOf(Math.pow((1 + (creditTest.getIllikFaiz() / (12 * 100))), creditTest.getMuddet())))).divide(
                BigDecimal.valueOf(Math.pow((1 + (creditTest.getIllikFaiz() / (12 * 100))), creditTest.getMuddet()) - 1), RoundingMode.HALF_UP));

        return result;
    }
}

