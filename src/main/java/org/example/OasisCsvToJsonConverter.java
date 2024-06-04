package org.example;

import com.opencsv.CSVReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class OasisCsvToJsonConverter {

    public OasisCsvToJsonConverter() {}

    public void convert(String fileName) {

        //parse questions from the csv file
        ArrayList<OasisQuestion> OasisQuestions = parseQuestions(fileName);

        //create a single .json object (questionnaire) that will hold info about the OASIS questionnaire and a list of questions
        JSONObject questionnaire = generateQuestionnaire(OasisQuestions);

        //todo: write to json file
    }

    private JSONObject generateQuestionnaire(ArrayList<OasisQuestion> OasisQuestions) {
        JSONObject questionnaire = createShellOasisQuestionnaire();

        //todo: populate the "items" field, ie put the questions in the questionnaire

        return questionnaire;
    }

    private JSONObject createShellOasisQuestionnaire() {
        JSONObject shellOasisQuestionnaire = new JSONObject();

        //meta
        JSONObject meta = new JSONObject();
        meta.put("versionId","18");
        meta.put("lastUpdated","2021-02-17T23:17:56.866+00:00");
        meta.put("source","#EgeCkgLf791gePmW");
        String[] profile = new String[1];
        profile[0] = "http://hapi.fhir.org/StructureDefinition/del-StandardForm";
        meta.put("profile", profile);

        //text
        JSONObject text = new JSONObject();
        text.put("status","generated");
        text.put("div","<div xmlns=\\\"http://www.w3.org/1999/xhtml\\\">OASIS_DAH standard form version D1-012020.<br/><br/>Data set for use in home health agencies (HHAs), State agencies, software vendors, professional associations and other Federal agencies in implementing and maintaining OASIS.</div>\"");

        //identifier
        JSONArray identifier = new JSONArray();
        JSONObject identifier0 = new JSONObject();
        identifier0.put("use","official");
        identifier0.put("system","http://del.cms.gov");
        identifier0.put("value","Outcome and Assessment Information Set");
        identifier.put(identifier0);

        //effectivePeriod
        JSONObject effectivePeriod = new JSONObject();
        effectivePeriod.put("start", "2020-01-01T00:00:00+00:00");

        //code
        JSONArray code = new JSONArray();
        JSONObject code0 = new JSONObject();
        code0.put("system","http://loinc.org");
        code0.put("code","93055-2");
        code0.put("display","Outcome and assessment information set (OASIS) form - version D1");
        code.put(code0);

        //shellOasisQuestionnaire
        shellOasisQuestionnaire.put("resourceType", "Questionnaire");
        shellOasisQuestionnaire.put("id", "OASIS-DAH-D1-012020");
        shellOasisQuestionnaire.put("meta", meta);
        shellOasisQuestionnaire.put("text", text);

        shellOasisQuestionnaire.put("url", "http://hapi.fhir.org/baseR4/Questionnaire/OASIS-DAH-D1-012020");
        shellOasisQuestionnaire.put("identifier", identifier);
        shellOasisQuestionnaire.put("version", "D1-012020");
        shellOasisQuestionnaire.put("name", "OASIS_DAH");

        shellOasisQuestionnaire.put("title", "Outcome and Assessment Information Set - Death at Home");
        shellOasisQuestionnaire.put("status", "active");
        shellOasisQuestionnaire.put("date", "2018-04-30T20:51:52.871Z");
        shellOasisQuestionnaire.put("publisher", "Division of Chronic and Post Acute Care");

        shellOasisQuestionnaire.put("description", "Data set for use in home health agencies (HHAs), State agencies, software vendors, professional associations and other Federal agencies in implementing and maintaining OASIS.");
        shellOasisQuestionnaire.put("approvalDate", "2019-04-05");
        shellOasisQuestionnaire.put("effectivePeriod", effectivePeriod);
        shellOasisQuestionnaire.put("code", code);

        System.out.println();
        System.out.println();
        System.out.println(shellOasisQuestionnaire);
        System.out.println();
        System.out.println();

        return shellOasisQuestionnaire;
    }

    private ArrayList<OasisQuestion> parseQuestions(String fileName) {
        //parse csv file into a list of OasisQuestions
        ArrayList<OasisQuestion> OasisQuestions = new ArrayList<OasisQuestion>();
        try {
            CSVReader csvReader = new CSVReader(new FileReader(fileName));
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
