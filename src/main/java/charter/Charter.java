package charter;

import charter.model.CharterDescription;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Charter {

    @Id
    private String id;
    private String charterName;
    private String areas;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime start;
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
    private CharterDescription charterDescription;

    public Charter() {}

    public Charter(String charterName, String areas, LocalDateTime start, String nameOfTester, String taskBreakDown, Integer duration, Integer testDesignAndExecutionTimeInPercent, Integer bugInvestigationAndReportingTimeInPercent, Integer sessionSetupTimeInPercentage, Integer charterVsOpportunityTimeInPercentage, String dataFilesPaths, String testNotes, String opportunities, String bugs, String issues, CharterDescription charterDescription) {
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
        this.charterDescription = charterDescription;
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

    public LocalDateTime getStart() {
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

    public void setStart(LocalDateTime start) {
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

    public CharterDescription getCharterDescription() {
        return charterDescription;
    }

    public void setCharterDescription(CharterDescription charterDescription) {
        this.charterDescription = charterDescription;
    }
}
