package org.example;

import org.json.JSONObject;

public class OasisQuestion {

    private String assessmentInstrument;
    private String assessmentInstrumentVersion;
    private String itemID;
    private String questionText;
    private String sectionName;
    private String skipPatternTrigger;
    private String lookbackPeriod;
    private String itemUses;
    private String itemSubsets;
    private String assessmentResponseCodes;
    private String responseText;
    private String copyrightIndicator;

    //todo: capitalize enum values?
    private enum ItemType {
        group,
        choice,
        text,
        date,
        integer
    }

    public OasisQuestion(String[] csvRow) {
        //todo: currently assumes an array of exactly 12 Strings. Make more flexible and/or add error handling
        assessmentInstrument = csvRow[0];
        assessmentInstrumentVersion = csvRow[1];
        itemID = csvRow[2];
        questionText = csvRow[3];
        sectionName = csvRow[4];
        skipPatternTrigger = csvRow[5];
        lookbackPeriod = csvRow[6];
        itemUses = csvRow[7];
        itemSubsets = csvRow[8];
        assessmentResponseCodes = csvRow[9];
        System.out.println(assessmentResponseCodes); //todo: delete when done
        responseText = csvRow[10];
        copyrightIndicator = csvRow[11];
    }

    public JSONObject toItem() {
        JSONObject item = new JSONObject();

        item.put("linkId", (sectionName + "/" + itemID));
        item.put("prefix", itemID);
        item.put("text", questionText);
        item.put("readOnly", false); //todo: assumed groups are read only, questions are not. Only care about questions, so set to false. Is this accurate?
        item.put("repeats", false); //todo: for now assumed repeats is always false. Find out whether this is correct.

        //parse assessmentResponseCodes to get type
        if (assessmentResponseCodes.equals("")) {
            item.put("type", ItemType.group);
        }
        else if (assessmentResponseCodes.toUpperCase().contains("TEXT")) {
            item.put("type", ItemType.text);
        }
        else if (assessmentResponseCodes.toUpperCase().contains("MMDDYYYY")) {
            item.put("type", ItemType.date);
        }
        else if (responseText.toLowerCase().contains("minimum value|maximum value")) {
            item.put("type", ItemType.integer); //todo: what about questions that can be answered with an integer or "N/A", "unable to complete", etc. Example BIMS summary score C0500
        }
        else {
            item.put("type", ItemType.choice);
        }





        //todo: finish putting things

        return item;
    }

    public String getAssessmentInstrument() {
        return assessmentInstrument;
    }

    public void setAssessmentInstrument(String assessmentInstrument) {
        this.assessmentInstrument = assessmentInstrument;
    }

    public String getAssessmentInstrumentVersion() {
        return assessmentInstrumentVersion;
    }

    public void setAssessmentInstrumentVersion(String assessmentInstrumentVersion) {
        this.assessmentInstrumentVersion = assessmentInstrumentVersion;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSkipPatternTrigger() {
        return skipPatternTrigger;
    }

    public void setSkipPatternTrigger(String skipPatternTrigger) {
        this.skipPatternTrigger = skipPatternTrigger;
    }

    public String getLookbackPeriod() {
        return lookbackPeriod;
    }

    public void setLookbackPeriod(String lookbackPeriod) {
        this.lookbackPeriod = lookbackPeriod;
    }

    public String getItemUses() {
        return itemUses;
    }

    public void setItemUses(String itemUses) {
        this.itemUses = itemUses;
    }

    public String getItemSubsets() {
        return itemSubsets;
    }

    public void setItemSubsets(String itemSubsets) {
        this.itemSubsets = itemSubsets;
    }

    public String getAssessmentResponseCodes() {
        return assessmentResponseCodes;
    }

    public void setAssessmentResponseCodes(String assessmentResponseCodes) {
        this.assessmentResponseCodes = assessmentResponseCodes;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public String getCopyrightIndicator() {
        return copyrightIndicator;
    }

    public void setCopyrightIndicator(String copyrightIndicator) {
        this.copyrightIndicator = copyrightIndicator;
    }
}
