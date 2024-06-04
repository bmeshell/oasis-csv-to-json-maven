package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Begin OasisCsvToJsonConverter main method");

        //todo: update readme with instructions. Two ways to pass the csv file.

        OasisCsvToJsonConverter oasisCsvToJsonConverter = new OasisCsvToJsonConverter();
        if (args.length == 0) {
            oasisCsvToJsonConverter.convert("src/main/java/org/example/OasisE.csv");
        }
        else {
            oasisCsvToJsonConverter.convert(args[0]);
        }

        System.out.println("End of OasisCsvToJsonConverter main method");
    }


}



//catch (FileNotFoundException e) {