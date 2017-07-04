package pl.sda.technicalanalyse;

import com.sun.javaws.exceptions.ErrorCodeResponseException;
import pl.sda.model.Company;
import pl.sda.model.Record;

import static java.lang.Math.toIntExact;

import java.util.List;

public class PointAndFigureAnalyse {

    public PointAndFigureAnalyse(Company company, int revelsalAmount, int boxSize) {
        this.company = company;
        this.revelsalAmount = revelsalAmount;
        this.boxSize = boxSize;
    }

    private Company company;
    private int revelsalAmount, boxSize;

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
        double lastAdded, current, next, revelsalSum=0, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
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

        //policz jaka jest amplituda wartosci i na jej podstawie ustal wysokosc tablicy. policz ile jest przegiec i na ich podstawie wylicz szerokosc tabeli
        for (int i = records.size() - 1; i > 0; i--) {
            current = records.get(i).getClose();
            next = records.get(i - 1).getClose();
            if (current < min) {
                min = current;
            }
            if (current > max) {
                max = current;
            }
            if(!ascending)
            //if ((ascending && (current - next>=revelsalAmount) || (!ascending && (next - current>=revelsalAmount))) width++;

        }
        try {
            height = 1+ toIntExact(Math.round(max - min));
        } catch (Exception e) {
            throw new Exception("Error while casting long to int.");
        }
        System.out.println("max "+ max + " min " + min );
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
