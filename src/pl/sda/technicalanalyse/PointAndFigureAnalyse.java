package pl.sda.technicalanalyse;

import pl.sda.model.Company;
import pl.sda.model.Record;

import java.util.List;

import static java.lang.Math.toIntExact;

public class PointAndFigureAnalyse {

    public PointAndFigureAnalyse(Company company, double revelsalAmount, double boxSize) {
        this.company = company;
        this.revelsalAmount = revelsalAmount;
        this.boxSize = boxSize;
    }

    private Company company;
    private double revelsalAmount, boxSize;

    //na bazie podanej skali bierze okreslona ilosc dni
    //sprawdza najwieksza i najmniejsza wartosc oraz boxSize i releaseAmount i wyznacza wielkosc tabeli
    //jesli przerosnie okragla kwote / jesli wrosnie o okreslona kwote
    public int[][] getAnalyse() throws Exception {
        if (null == company.getRecords()) {
            throw new Exception("List of companies is empty! wtf bitch? u kiddin ?");
        }
        List<Record> records = company.getRecords();
        //System.out.println(records.get(0));
        //doraznie -1 potem zmienic
        int height = 1, width = 1;
        double lastAdded, current, next, revelsalSum = 0, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, box = 0, rev = 0, sum = 0, last, prev;
        boolean ascending = false, drawLine = false;
        //sprawdza czy pierwsza zmiana to wzrost, spadek czy moe zmiana nie istnieje(przypadek skrajny)
        for (int i = records.size() - 1; i >= 0; i--) {
            current = records.get(i).getClose();
            next = records.get(i - 1).getClose();
            if (current - next < 0) {
                drawLine = false;
                ascending = true;
                break;
            }
            if (current - next > 0) {
                drawLine = false;
                ascending = false;
                break;
            } else drawLine = true;
        }
        if (drawLine) {
            System.out.println("No change noticed- draw a line or sth.");
        }

        System.out.println("rev " + revelsalAmount + " box "+ boxSize);
        //policz jaka jest amplituda wartosci i na jej podstawie ustal wysokosc tablicy. policz ile jest przegiec i na ich podstawie wylicz szerokosc tabeli
        last = records.get(records.size() - 1).getClose();
        for (int i = records.size() - 1; i > 0; i--) {
            current = records.get(i).getClose();

            if (current < min) min = current;
            if (current > max) max = current;
            sum = current - last;

            System.out.println("current " + current+ " last " + last + " sum "+ sum+ " asc " + ascending);

            if (ascending) {
                if (sum >= boxSize) last = current; //prev sum>=0
                else if (sum <= -revelsalAmount) {
                    ascending = false;
                    last = current;
                    width++;
                    System.out.println("zmiana w asc");
                }
            } else {
                if (sum <= -boxSize) last = current; //prev sum<=0
                else if (sum >= boxSize) {
                    ascending = true;
                    last = current;
                    System.out.println("zmiana w desc");
                    width++;
                }
            }
        }
        try {
            height = 1 + toIntExact(Math.round(max - min));
        } catch (Exception e) {
            throw new Exception("Error while casting long to int.");
        }
        System.out.println("max " + max + " min " + min);
        System.out.println("width: " + width + " height: " + height);

        lastAdded = records.get(records.size() - 1).getClose();
        for (int i = records.size() - 1; i >= 0; i--) {
            current = records.get(i).getClose();
            //jesli wiekszy
            if (current - lastAdded >= boxSize) {
                //dodaj do tabeli

            }

            //jesli mniejszy
        }


        return null;
    }
}
