package ipoteka_calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IpotekaMain {
    private static List<Customer> customerList = new ArrayList<>();
    private static int ipotekaUsulu;
    private static int tikintiIli;
    private static int verilmeUsulu;

    public static void main(String[] args) throws ParseException {
        System.out.println("    \033[0;1m**********İpoteka    Kalkulyatoru**********\033[0;0m");
        System.out.println("       ****İpoteka krediti hesablanması****");

        Customer customer = Customer.instance();
        Credit credit = Credit.instance();
        MonthlyPayment monthlyPayment = MonthlyPayment.instance();

        Scanner scanner1 = new Scanner(System.in);

        System.out.print("Adınız: ");
        customer.setName(scanner1.nextLine());
        System.out.print("Soyadınız: ");
        customer.setSurname(scanner1.nextLine());
        System.out.print("Cinsiyyətiniz (K və ya Q yazın): ");
        customer.setCinsiyyet(scanner1.nextLine().toLowerCase());
        System.out.print("Doğum tarixiniz (Məs:21.05.2001) : ");
        customer.setBirthday(scanner1.nextLine());

        if ((customer.getCinsiyyet().equals("k") && customer.getAge() < 65) ||
                (customer.getCinsiyyet().equals("q") && customer.getAge() < 62)) {
            System.out.println("Ipoteka Verile Biler");
            // 1) Ipoteka üsulu hansı olacaq:  Standart->7% yoxsa Güzəştli->3.7%
            ipotekaUsulu = IpotekaUtil.ipoteUsulu();
            // 2) Tikinti ili nə olacaq:  2013-cü ildən yuxarı mənzilər yoxsa->min(15%) 1970-ci ildən yuxarı mənzillər->min(30%)
            tikintiIli = IpotekaUtil.tikintiIli();
            // 3) Verilmə üsulu nə olacaq:  Zəmanətli yoxsa Zəmanətsiz
            verilmeUsulu = IpotekaUtil.verilmeUsulu();
            // 4) Mənzilin dəyəri nə olacaqI: Minimum 20 000 AZN olmalıdır
            credit.setMenzilinDeyeri(BigDecimal.valueOf(IpotekaUtil.menzilDeyeri()));
            // 5) Müddət neçə ay olacaq: 36 - 360
            credit.setMuddet(IpotekaUtil.muddet());

            //todo Ilkin ödənişin hesablanması.
            //1. Tikinti üsulu = 1 - dirsə -> 30%
            //2. Tikinti üsulu = 2 - dirsə -> 15%
            if (tikintiIli == 1) {
                double faiz = 0.3;
                if (credit.getMenzilinDeyeri().subtract(credit.getMenzilinDeyeri().multiply(BigDecimal.valueOf(faiz))).compareTo(BigDecimal.valueOf(100000)) <= 0) {
                    credit.setIlkinOdenis(credit.getMenzilinDeyeri().multiply(BigDecimal.valueOf(faiz)));
                } else {
                    credit.setIlkinOdenis(credit.getMenzilinDeyeri().subtract(BigDecimal.valueOf(100000)));
                }
            } else if (tikintiIli == 2) {
                double faiz = 0.15;
                if (credit.getMenzilinDeyeri().subtract(credit.getMenzilinDeyeri().multiply(BigDecimal.valueOf(faiz))).compareTo(BigDecimal.valueOf(100000)) <= 0) {
                    credit.setIlkinOdenis(credit.getMenzilinDeyeri().multiply(BigDecimal.valueOf(faiz)));
                } else {
                    credit.setIlkinOdenis(credit.getMenzilinDeyeri().subtract(BigDecimal.valueOf(100000)));
                }
            }

            //todo Kredit Mebleginin hesablanmasi
            credit.setKreditMeblegi(credit.getMenzilinDeyeri().subtract(credit.getIlkinOdenis()));

            //todo Illik Faiz derecesinin hesablanmasi
            if (ipotekaUsulu == 1) {
                if (verilmeUsulu == 1) {
                    credit.setIllikFaiz(7);
                } else if (verilmeUsulu == 2) {
                    credit.setIllikFaiz(8);
                }
            } else if (ipotekaUsulu == 2) {
                if (verilmeUsulu == 1) {
                    credit.setIllikFaiz(3.7);
                } else if (verilmeUsulu == 2) {
                    credit.setIllikFaiz(4);
                }
            }

            //todo Ayliq Odenisin Tapilmasi
            monthlyPayment.setAyliqOdenis((credit.getKreditMeblegi().multiply(BigDecimal.valueOf(credit.getIllikFaiz() / (12 * 100))).multiply(
                    BigDecimal.valueOf(Math.pow((1 + (credit.getIllikFaiz() / (12 * 100))), credit.getMuddet())))).divide(
                    BigDecimal.valueOf(Math.pow((1 + (credit.getIllikFaiz() / (12 * 100))), credit.getMuddet()) - 1), RoundingMode.HALF_UP));

            System.out.printf("Adi: %s\nSoyadi: %s\nYasi: %s\nCinsi: %s\n",
                    customer.getName(),
                    customer.getSurname(),
                    customer.getAge(),
                    customer.getCinsiyyet()
            );

            System.out.println("Bank Terefinden Verilen Kreditin Meblegi: " + credit.getKreditMeblegi() + "AZN");
            System.out.println("Ilkin Odenis Meblegi: " + credit.getIlkinOdenis() + "AZN");
            System.out.println("Illik Faiz Miqdari: " + credit.getIllikFaiz() + "%");
            System.out.println("Ayliq Odenecek Mebleg: " + monthlyPayment.getAyliqOdenis().setScale(2, BigDecimal.ROUND_UP) + "AZN");
            customerList.add(customer);
            
        } else {
            System.out.println("Ipoteka üçün yaşınız düşmür :(");
        }
    }
}