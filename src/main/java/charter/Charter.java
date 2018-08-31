package charter;

import org.springframework.data.annotation.Id;

import java.util.Calendar;

public class Charter {

    @Id
    private String id;
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

    public Charter() {}

    public Charter(String charterName, String areas, Calendar start, String nameOfTester, String taskBreakDown, Integer duration, Integer testDesignAndExecutionTimeInPercent, Integer bugInvestigationAndReportingTimeInPercent, Integer sessionSetupTimeInPercentage, Integer charterVsOpportunityTimeInPercentage, String dataFilesPaths, String testNotes, String opportunities, String bugs, String issues) {
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

    public String getId() {
        return id;
    }

    public String getCharterName() {
        return charterName;
    }

    public String getAreas() {
        return areas;
    }

    public Calendar getStart() {
        return start;
    }

    public String getNameOfTester() {
        return nameOfTester;
    }

    public String getTaskBreakDown() {
        return taskBreakDown;
    }

    public Integer getDuration() {
        return duration;
    }

    public Integer getTestDesignAndExecutionTimeInPercent() {
        return testDesignAndExecutionTimeInPercent;
    }

    public Integer getBugInvestigationAndReportingTimeInPercent() {
        return bugInvestigationAndReportingTimeInPercent;
    }

    public Integer getSessionSetupTimeInPercentage() {
        return sessionSetupTimeInPercentage;
    }

    public Integer getCharterVsOpportunityTimeInPercentage() {
        return charterVsOpportunityTimeInPercentage;
    }

    public String getDataFilesPaths() {
        return dataFilesPaths;
    }

    public String getTestNotes() {
        return testNotes;
    }

    public String getOpportunities() {
        return opportunities;
    }

    public String getBugs() {
        return bugs;
    }

    public String getIssues() {
        return issues;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCharterName(String charterName) {
        this.charterName = charterName;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public void setNameOfTester(String nameOfTester) {
        this.nameOfTester = nameOfTester;
    }

    public void setTaskBreakDown(String taskBreakDown) {
        this.taskBreakDown = taskBreakDown;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setTestDesignAndExecutionTimeInPercent(Integer testDesignAndExecutionTimeInPercent) {
        this.testDesignAndExecutionTimeInPercent = testDesignAndExecutionTimeInPercent;
    }

    public void setBugInvestigationAndReportingTimeInPercent(Integer bugInvestigationAndReportingTimeInPercent) {
        this.bugInvestigationAndReportingTimeInPercent = bugInvestigationAndReportingTimeInPercent;
    }

    public void setSessionSetupTimeInPercentage(Integer sessionSetupTimeInPercentage) {
        this.sessionSetupTimeInPercentage = sessionSetupTimeInPercentage;
    }

    public void setCharterVsOpportunityTimeInPercentage(Integer charterVsOpportunityTimeInPercentage) {
        this.charterVsOpportunityTimeInPercentage = charterVsOpportunityTimeInPercentage;
    }

    public void setDataFilesPaths(String dataFilesPaths) {
        this.dataFilesPaths = dataFilesPaths;
    }

    public void setTestNotes(String testNotes) {
        this.testNotes = testNotes;
    }

    public void setOpportunities(String opportunities) {
        this.opportunities = opportunities;
    }

    public void setBugs(String bugs) {
        this.bugs = bugs;
    }

    public void setIssues(String issues) {
        this.issues = issues;
    }
}
