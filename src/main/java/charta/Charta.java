package charta;

import java.util.Calendar;

public class Charta {

    private final long id;
    private String charterName;
    private String areas;
    private Calendar start;
    private String nameOfTester;
    private String taskBreakDown;
    private Integer duration;
    private Integer testDesignAndExecutionTimeInPercent;
    private Integer bugInvestigationAndReportingTimeInPercent;
    private Integer sessionSetupTimeInPercentage;
    private Integer charterVsOpportunityTimeInPercentage;
    private String dataFilesPaths;
    private String testNotes;
    private String opportunities;
    private String bugs;
    private String issues;

    public Charta(long id, String charterName, String areas, Calendar start, String nameOfTester, String taskBreakDown, Integer duration, Integer testDesignAndExecutionTimeInPercent, Integer bugInvestigationAndReportingTimeInPercent, Integer sessionSetupTimeInPercentage, Integer charterVsOpportunityTimeInPercentage, String dataFilesPaths, String testNotes, String opportunities, String bugs, String issues) {
        this.id = id;
        this.charterName = charterName;
        this.areas = areas;
        this.start = start;
        this.nameOfTester = nameOfTester;
        this.taskBreakDown = taskBreakDown;
        this.duration = duration;
        this.testDesignAndExecutionTimeInPercent = testDesignAndExecutionTimeInPercent;
        this.bugInvestigationAndReportingTimeInPercent = bugInvestigationAndReportingTimeInPercent;
        this.sessionSetupTimeInPercentage = sessionSetupTimeInPercentage;
        this.charterVsOpportunityTimeInPercentage = charterVsOpportunityTimeInPercentage;
        this.dataFilesPaths = dataFilesPaths;
        this.testNotes = testNotes;
        this.opportunities = opportunities;
        this.bugs = bugs;
        this.issues = issues;
    }

    public long getId() {
        return id;
    }
    public String getCharterName() {
        return charterName;
    }

    public void setCharterName(String charterName) {
        this.charterName = charterName;
    }

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public String getNameOfTester() {
        return nameOfTester;
    }

    public void setNameOfTester(String nameOfTester) {
        this.nameOfTester = nameOfTester;
    }

    public String getTaskBreakDown() {
        return taskBreakDown;
    }

    public void setTaskBreakDown(String taskBreakDown) {
        this.taskBreakDown = taskBreakDown;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getTestDesignAndExecutionTimeInPercent() {
        return testDesignAndExecutionTimeInPercent;
    }

    public void setTestDesignAndExecutionTimeInPercent(Integer testDesignAndExecutionTimeInPercent) {
        this.testDesignAndExecutionTimeInPercent = testDesignAndExecutionTimeInPercent;
    }

    public Integer getBugInvestigationAndReportingTimeInPercent() {
        return bugInvestigationAndReportingTimeInPercent;
    }

    public void setBugInvestigationAndReportingTimeInPercent(Integer bugInvestigationAndReportingTimeInPercent) {
        this.bugInvestigationAndReportingTimeInPercent = bugInvestigationAndReportingTimeInPercent;
    }

    public Integer getSessionSetupTimeInPercentage() {
        return sessionSetupTimeInPercentage;
    }

    public void setSessionSetupTimeInPercentage(Integer sessionSetupTimeInPercentage) {
        this.sessionSetupTimeInPercentage = sessionSetupTimeInPercentage;
    }

    public Integer getCharterVsOpportunityTimeInPercentage() {
        return charterVsOpportunityTimeInPercentage;
    }

    public void setCharterVsOpportunityTimeInPercentage(Integer charterVsOpportunityTimeInPercentage) {
        this.charterVsOpportunityTimeInPercentage = charterVsOpportunityTimeInPercentage;
    }

    public String getDataFilesPaths() {
        return dataFilesPaths;
    }

    public void setDataFilesPaths(String dataFilesPaths) {
        this.dataFilesPaths = dataFilesPaths;
    }

    public String getTestNotes() {
        return testNotes;
    }

    public void setTestNotes(String testNotes) {
        this.testNotes = testNotes;
    }

    public String getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(String opportunities) {
        this.opportunities = opportunities;
    }

    public String getBugs() {
        return bugs;
    }

    public void setBugs(String bugs) {
        this.bugs = bugs;
    }

    public String getIssues() {
        return issues;
    }

    public void setIssues(String issues) {
        this.issues = issues;
    }
}
