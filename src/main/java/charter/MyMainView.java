package charter;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        window.setModal(true);
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
        charterNameTxtField.setValue(Optional.ofNullable(charter.getCharterName()).orElse(""));
        areasTxtField.setValue(Optional.ofNullable(charter.getAreas()).orElse(""));
        startTxtField.setValue(
                charter.getStart() == null ? "" : charter.getStart().toString()
        );
        nameOfTesterTxtField.setValue(Optional.ofNullable(charter.getNameOfTester()).orElse(""));
        taskBreakDownTxtField.setValue(Optional.ofNullable(charter.getTaskBreakDown()).orElse(""));
        durationTxtField.setValue(
                charter.getDuration() == null ? "" : charter.getDuration().toString()
        );
        testDesignAndExecutionTimeInPercentTxtField.setValue(
                charter.getTestDesignAndExecutionTimeInPercent() == null ? "" : charter.getTestDesignAndExecutionTimeInPercent().toString()
        );
        bugInvestigationAndReportingTimeInPercentTxtField.setValue(
                charter.getBugInvestigationAndReportingTimeInPercent() == null ? "" : charter.getBugInvestigationAndReportingTimeInPercent().toString()
        );
        sessionSetupTimeInPercentageTxtField.setValue(
                charter.getSessionSetupTimeInPercentage() == null ? "" : charter.getSessionSetupTimeInPercentage().toString()
        );
        charterVsOpportunityTimeInPercentageTxtField.setValue(
                charter.getCharterVsOpportunityTimeInPercentage() == null ? "" : charter.getCharterVsOpportunityTimeInPercentage().toString()
        );
        dataFilesPathsTxtField.setValue(Optional.ofNullable(charter.getDataFilesPaths()).orElse(""));
        testNotesTxtField.setValue(Optional.ofNullable(charter.getTestNotes()).orElse(""));
        opportunitiesTxtField.setValue(Optional.ofNullable(charter.getOpportunities()).orElse(""));
        bugsTxtField.setValue(Optional.ofNullable(charter.getBugs()).orElse(""));
        issues.setValue(Optional.ofNullable(charter.getIssues()).orElse(""));

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

        Button saveButton = new Button(VaadinIcons.SAFE);
        saveButton.addStyleName(ValoTheme.BUTTON_SMALL);
        Charter overriddenCharter = overrideCharterByTextFieldValues(charter, components);
        saveButton.addClickListener(e -> createModalSaveConfirmationDialog(overriddenCharter));
        components.add(saveButton);
        return components;
    }

    private Charter overrideCharterByTextFieldValues(Charter charter, List<Component> components) {
        return null;
            }


    private void deleteCharter(Charter p) {
        charterRepository.delete(p);
        grid.setItems(charterRepository.findAll());
    }

    private void saveCharter(Charter p) {
        charterRepository.save(p);
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

    private void createModalSaveConfirmationDialog(Charter c) {

        Window confirmationDialogWindow = new Window("Confirm Save Dialog");

        confirmationDialogWindow.setHeight("200px");
        confirmationDialogWindow.setWidth("400px");
        confirmationDialogWindow.setPositionX(200);
        confirmationDialogWindow.setPositionY(50);
        confirmationDialogWindow.setModal(true);

        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        Button confirmSaveButton = new Button("Save", VaadinIcons.EXCLAMATION_CIRCLE);
        Button cancelButton = new Button("Cancel");
        Label label = new Label("Möchtest du die Charter wirklich speichern?");
        horizontalLayout.addComponents(cancelButton, confirmSaveButton);
        verticalLayout.addComponents(label, horizontalLayout);

        confirmSaveButton.addClickListener((Button.ClickListener) event -> {
            saveCharter(c);
            confirmationDialogWindow.close();
        });
        cancelButton.addClickListener((Button.ClickListener) event -> {
            confirmationDialogWindow.close();
        });

        confirmationDialogWindow.setContent(verticalLayout);
        UI.getCurrent().addWindow(confirmationDialogWindow);
    }

}
