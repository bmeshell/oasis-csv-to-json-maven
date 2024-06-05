package org.example;

import com.google.gson.*;
import com.opencsv.CSVReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class OasisCsvToJsonConverter {

    public OasisCsvToJsonConverter() {}

    public void convert(String fileName) {

        //parse questions from the csv file
        ArrayList<OasisQuestion> OasisQuestions = parseQuestions(fileName);

        //create a single .json object (questionnaire) that will hold info about the OASIS questionnaire and a list of questions
        JSONObject questionnaire = createShellOasisQuestionnaire();

        //loop through list of questions, convert each question to the corresponding JSONObject to go in the item JSONArray
        JSONArray itemJsonArray = new JSONArray();
        for (OasisQuestion oasisQuestion : OasisQuestions) {
            itemJsonArray.put(oasisQuestion.toItem());
        }

        //after building item JSONArray, add item to the questionnaire
        questionnaire.put("item", itemJsonArray);

        //now that the questionnaire JSONObject is fully populated, save it to a file
        savePrettyPrintJsonToFile(questionnaire);
    }

    private JSONObject createShellOasisQuestionnaire() {
        JSONObject shellOasisQuestionnaire = new JSONObject();

        //meta
        JSONObject meta = new JSONObject();
        meta.put("versionId","1");
        meta.put("lastUpdated","2022-12-06T00:00:00+00:00");
        meta.put("source","#EgeCkgLf791gePmW"); //todo: what to put here?
        String[] profile = new String[1];
        profile[0] = "http://hapi.fhir.org/StructureDefinition/del-StandardForm";
        meta.put("profile", profile);

        //text
        JSONObject text = new JSONObject();
        text.put("status","generated");
        text.put("div","<div xmlns=\\\"http://www.w3.org/1999/xhtml\\\">OASIS standard form version E-012023.<br/><br/>Data set for use in home health agencies (HHAs), State agencies, software vendors, professional associations and other Federal agencies in implementing and maintaining OASIS.</div>\"");

        //identifier
        JSONArray identifier = new JSONArray();
        JSONObject identifier0 = new JSONObject();
        identifier0.put("use","official");
        identifier0.put("system","http://del.cms.gov");
        identifier0.put("value","Outcome and Assessment Information Set");
        identifier.put(identifier0);

        //effectivePeriod
        JSONObject effectivePeriod = new JSONObject();
        effectivePeriod.put("start", "2023-01-01T00:00:00+00:00");

        //code
        JSONArray code = new JSONArray();
        JSONObject code0 = new JSONObject();
        code0.put("system","http://loinc.org");
        code0.put("code","99131-5");
        code0.put("display","Outcome and assessment information set (OASIS) form - version E - Start of Care during assessment period [CMS Assessment]");
        code.put(code0);

        //shellOasisQuestionnaire
        shellOasisQuestionnaire.put("resourceType", "Questionnaire");
        shellOasisQuestionnaire.put("id", "OASIS-E-012023");
        shellOasisQuestionnaire.put("meta", meta);
        shellOasisQuestionnaire.put("text", text);

        shellOasisQuestionnaire.put("url", "http://hapi.fhir.org/baseR4/Questionnaire/OASIS-E-012023");
        shellOasisQuestionnaire.put("identifier", identifier);
        shellOasisQuestionnaire.put("version", "E-012023");
        shellOasisQuestionnaire.put("name", "OASIS_DAH");

        shellOasisQuestionnaire.put("title", "Outcome and Assessment Information Set");
        shellOasisQuestionnaire.put("status", "active");
        shellOasisQuestionnaire.put("date", "2023-01-01T00:00:00+00:00"); //todo: get correct date
        shellOasisQuestionnaire.put("publisher", "Division of Chronic and Post Acute Care");

        shellOasisQuestionnaire.put("description", "Data set for use in home health agencies (HHAs), State agencies, software vendors, professional associations and other Federal agencies in implementing and maintaining OASIS.");
        shellOasisQuestionnaire.put("approvalDate", "2022-12-06");
        shellOasisQuestionnaire.put("effectivePeriod", effectivePeriod);
        shellOasisQuestionnaire.put("code", code);

        savePrettyPrintJsonToFile(shellOasisQuestionnaire);

        return shellOasisQuestionnaire;
    }

    private void savePrettyPrintJsonToFile(JSONObject jsonObject) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            File jsonFile = new File("src/main/java/org/example/Questionnaire-OASIS-E-012023.json");
            Writer writer = new BufferedWriter(new java.io.FileWriter(jsonFile));
            gson.toJson(JsonParser.parseString(jsonObject.toString()), writer);
            writer.flush();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private ArrayList<OasisQuestion> parseQuestions(String fileName) {
        //parse csv file into a list of OasisQuestions
        ArrayList<OasisQuestion> OasisQuestions = new ArrayList<OasisQuestion>();
        try {
            CSVReader csvReader = new CSVReader(new FileReader(fileName));
            csvReader.readNext(); //call readNext() once before loop starts to skip the first line (header) in csv file
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                OasisQuestions.add(new OasisQuestion(values));
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return OasisQuestions;
    }

}
