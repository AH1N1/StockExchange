package pl.sda.technicalanalyse;

import pl.sda.model.Company;
import pl.sda.model.Record;

import java.util.List;

public class PointAndFigureAnalyse {

    private Company company;
    private int revelsalAmount, boxSize;

    //na bazie podanej skali bierze okreslona ilosc dni
    //sprawdza najwieksza i najmniejsza wartosc oraz boxSize i releaseAmount i wyznacza wielkosc tabeli
    //jesli przerosnie okragla kwote / jesli wrosnie o okreslona kwote
    public int[][] getAnalyse() throws Exception {
        if (null == company.getRecords()) {
            throw  new Exception("List of companies is empty! wtf bitch? u kiddin ?");
        }

            List<Record> records =  company.getRecords();
            System.out.println(records.get(0));




        return null;
    }
}
