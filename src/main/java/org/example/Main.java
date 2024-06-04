package org.example;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            System.out.println("Begin oasis csv to json main method try block");
            List<OasisQuestion> OasisQuestions = new ArrayList<OasisQuestion>();
            //todo: hard coded file path
            CSVReader csvReader = new CSVReader(new FileReader("src/main/java/org/example/OasisE.csv"));
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                OasisQuestions.add(new OasisQuestion(values));
            }
            System.out.println("End of oasis csv to json main method try block");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}



//catch (FileNotFoundException e) {