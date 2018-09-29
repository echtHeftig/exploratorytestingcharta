package charter;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@SpringUI(path = "")
public class MyMainView extends UI {

    @Autowired
    private CharterRepository charterRepository;

    private Grid<Charter> grid = new Grid<>(Charter.class);

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout verticalLayout = new VerticalLayout();
        grid.setItems(charterRepository.findAll());
        grid.setWidth(100, Unit.PERCENTAGE);
        grid.setColumns("id", "charterName", "nameOfTester", "areas");
        grid.setColumnOrder("id", "charterName", "nameOfTester");
        grid.addComponentColumn(this::buildEditButton);
        grid.addComponentColumn(this::buildDeleteButton);
        verticalLayout.addComponent(grid);
        setContent(verticalLayout);
    }

    private Button buildEditButton(Charter charter) {
        Button button = new Button(VaadinIcons.EDIT);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        button.addClickListener(e -> createModalEditDialog(charter));
        return button;
    }

    private Button buildDeleteButton(Charter p) {
        Button button = new Button(VaadinIcons.CLOSE);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        button.addClickListener(e -> createModalConfirmationDialog(p));
        return button;
    }

    private void createModalEditDialog(Charter charter) {
        Window window = new Window("Edit Dialog");
        VerticalLayout verticalLayout = new VerticalLayout();
        List<Component> allEditDialogComponents = createAllEditDialogComponents(charter);
        for (Component component : allEditDialogComponents) {
            verticalLayout.addComponents(component);
        }
        window.setContent(verticalLayout);
        UI.getCurrent().addWindow(window);
    }

    private List<Component> createAllEditDialogComponents(Charter charter) {
        TextField idTxtField = new TextField("ID");
        TextField charterNameTxtField = new TextField("CharterName");
        TextField areasTxtField = new TextField("areasCaption");
        TextField startTxtField = new TextField("startCaption");
        TextField nameOfTesterTxtField = new TextField("nameOfTesterCaption");
        TextField taskBreakDownTxtField = new TextField("taskBreakDownCaption");
        TextField durationTxtField = new TextField("durationCaption");
        TextField testDesignAndExecutionTimeInPercentTxtField = new TextField("testDesignAndExecutionTimeInPercentCaption");
        TextField bugInvestigationAndReportingTimeInPercentTxtField = new TextField("bugInvestigationAndReportingTimeInPercentCaption");
        TextField sessionSetupTimeInPercentageTxtField = new TextField("sessionSetupTimeInPercentageCaption");
        TextField charterVsOpportunityTimeInPercentageTxtField = new TextField("charterVsOpportunityTimeInPercentageCaption");
        TextField dataFilesPathsTxtField = new TextField("dataFilesPathsCaption");
        TextField testNotesTxtField = new TextField("testNotesCaption");
        TextField opportunitiesTxtField = new TextField("opportunitiesCaption");
        TextField bugsTxtField = new TextField("bugsCaption");
        TextField issues = new TextField("issuesCaption");

        idTxtField.setValue(charter.getId());
        idTxtField.setReadOnly(true);
        idTxtField.setEnabled(false);
        idTxtField.setData(charter.getId());
        charterNameTxtField.setValue(charter.getCharterName());
        areasTxtField.setValue(charter.getAreas());
        startTxtField.setValue(charter.getStart().toString());
        nameOfTesterTxtField.setValue(charter.getNameOfTester());
        taskBreakDownTxtField.setValue(charter.getTaskBreakDown());
        durationTxtField.setValue(charter.getDuration().toString());
        testDesignAndExecutionTimeInPercentTxtField.setValue(charter.getTestDesignAndExecutionTimeInPercent().toString());
        bugInvestigationAndReportingTimeInPercentTxtField.setValue(charter.getBugInvestigationAndReportingTimeInPercent().toString());
        sessionSetupTimeInPercentageTxtField.setValue(charter.getSessionSetupTimeInPercentage().toString());
        charterVsOpportunityTimeInPercentageTxtField.setValue(charter.getCharterVsOpportunityTimeInPercentage().toString());
        dataFilesPathsTxtField.setValue(charter.getDataFilesPaths());
        testNotesTxtField.setValue(charter.getTestNotes());
        opportunitiesTxtField.setValue(charter.getOpportunities());
        bugsTxtField.setValue(charter.getBugs());
        issues.setValue(charter.getIssues());

        List<Component> components = new ArrayList<>();
        components.add(idTxtField);
        components.add(charterNameTxtField);
        components.add(areasTxtField);
        components.add(startTxtField);
        components.add(nameOfTesterTxtField);
        components.add(taskBreakDownTxtField);
        components.add(durationTxtField);
        components.add(testDesignAndExecutionTimeInPercentTxtField);
        components.add(bugInvestigationAndReportingTimeInPercentTxtField);
        components.add(sessionSetupTimeInPercentageTxtField);
        components.add(charterVsOpportunityTimeInPercentageTxtField);
        components.add(dataFilesPathsTxtField);
        components.add(testNotesTxtField);
        components.add(opportunitiesTxtField);
        components.add(bugsTxtField);
        components.add(issues);
        return components;
    }


    private void deleteCharter(Charter p) {
        charterRepository.delete(p);
        grid.setItems(charterRepository.findAll());
    }

    private void createModalConfirmationDialog(Charter c) {

        Window confirmationDialogWindow = new Window("Confirm Delete Dialog");

        confirmationDialogWindow.setHeight("200px");
        confirmationDialogWindow.setWidth("400px");
        confirmationDialogWindow.setPositionX(200);
        confirmationDialogWindow.setPositionY(50);
        confirmationDialogWindow.setModal(true);

        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        Button confirmDeleteButton = new Button("Delete", VaadinIcons.EXCLAMATION_CIRCLE);
        Button cancelButton = new Button("Cancel");
        Label label = new Label("Möchtest du die Charter wirklich löschen?");
        horizontalLayout.addComponents(cancelButton, confirmDeleteButton);
        verticalLayout.addComponents(label, horizontalLayout);

        confirmDeleteButton.addClickListener((Button.ClickListener) event -> {
            deleteCharter(c);
            confirmationDialogWindow.close();
        });
        cancelButton.addClickListener((Button.ClickListener) event -> {
            confirmationDialogWindow.close();
        });

        confirmationDialogWindow.setContent(verticalLayout);
        UI.getCurrent().addWindow(confirmationDialogWindow);
    }

}
