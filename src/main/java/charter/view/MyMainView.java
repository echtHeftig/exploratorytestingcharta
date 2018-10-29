package charter.view;

import charter.model.CharterRepository;
import charter.model.Charter;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Optional;

@SpringUI(path = "")
public class MyMainView extends UI {

    public static final String EDIT_DIALOG_TITLE = "Edit Dialog";
    public static final String CREATE_DIALOG_TITLE = "Create Dialog";
    @Autowired
    private CharterRepository charterRepository;

    private Grid<Charter> grid = new Grid<>(Charter.class);

    private TextField idTxtField = new TextField("Id");
    private TextField charterNameTxtField = new TextField("CharterName");
    private TextField areasTxtField = new TextField("areasCaption");
    private DateTimeField startTxtField = new DateTimeField("Start of Session");
    private TextField nameOfTesterTxtField = new TextField("nameOfTesterCaption");
    private TextField taskBreakDownTxtField = new TextField("taskBreakDownCaption");
    private TextField durationTxtField = new TextField("durationCaption");
    private TextField testDesignAndExecutionTimeInPercentTxtField = new TextField("testDesignAndExecutionTimeInPercentCaption");
    private TextField bugInvestigationAndReportingTimeInPercentTxtField = new TextField("bugInvestigationAndReportingTimeInPercentCaption");
    private TextField sessionSetupTimeInPercentageTxtField = new TextField("sessionSetupTimeInPercentageCaption");
    private TextField charterVsOpportunityTimeInPercentageTxtField = new TextField("charterVsOpportunityTimeInPercentageCaption");
    private TextField dataFilesPathsTxtField = new TextField("dataFilesPathsCaption");
    private TextField testNotesTxtField = new TextField("testNotesCaption");
    private TextField opportunitiesTxtField = new TextField("opportunitiesCaption");
    private TextField bugsTxtField = new TextField("bugsCaption");
    private TextField issuesTxtField = new TextField("issuesCaption");

    private Binder<Charter> binder = new Binder<>();

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout verticalLayout = new VerticalLayout();
        grid.setItems(charterRepository.findAll());
        grid.setWidth(100, Unit.PERCENTAGE);
        grid.setColumns("id", "charterName", "nameOfTester", "areas");
        grid.setColumnOrder("id", "charterName", "nameOfTester");
        grid.addComponentColumn(this::buildEditButton);
        grid.addComponentColumn(this::buildDeleteButton);
        verticalLayout.addComponent(buildCreateButton());
        verticalLayout.addComponent(grid);
        setContent(verticalLayout);
    }

    private Binder<Charter> createBinderForAllFields() {
        binder.forField(idTxtField)
                .bind(Charter::getId, null);
        binder.forField(charterNameTxtField)
                .bind(Charter::getCharterName, Charter::setCharterName);
        binder.forField(areasTxtField)
                .bind(Charter::getAreas, Charter::setAreas);
        binder.forField(startTxtField)
                .bind(Charter::getStart, Charter::setStart);
        binder.forField(nameOfTesterTxtField)
                .bind(Charter::getNameOfTester, Charter::setNameOfTester);
        binder.forField(taskBreakDownTxtField)
                .bind(Charter::getTaskBreakDown, Charter::setTaskBreakDown);
        binder.forField(durationTxtField)
                .withConverter(createNewStringToIntegerConverter())
                .bind(Charter::getDuration, Charter::setDuration);
        binder.forField(testDesignAndExecutionTimeInPercentTxtField)
                .withConverter(createNewStringToIntegerConverter())
                .bind(Charter::getTestDesignAndExecutionTimeInPercent, Charter::setTestDesignAndExecutionTimeInPercent);
        binder.forField(bugInvestigationAndReportingTimeInPercentTxtField)
                .withConverter(createNewStringToIntegerConverter())
                .bind(Charter::getBugInvestigationAndReportingTimeInPercent, Charter::setBugInvestigationAndReportingTimeInPercent);
        binder.forField(sessionSetupTimeInPercentageTxtField)
                .withConverter(createNewStringToIntegerConverter())
                .bind(Charter::getSessionSetupTimeInPercentage, Charter::setSessionSetupTimeInPercentage);
        binder.forField(charterVsOpportunityTimeInPercentageTxtField)
                .withConverter(createNewStringToIntegerConverter())
                .bind(Charter::getCharterVsOpportunityTimeInPercentage, Charter::setCharterVsOpportunityTimeInPercentage);
        binder.forField(dataFilesPathsTxtField)
                .bind(Charter::getDataFilesPaths, Charter::setDataFilesPaths);
        binder.forField(testNotesTxtField)
                .bind(Charter::getTestNotes, Charter::setTestNotes);
        binder.forField(opportunitiesTxtField)
                .bind(Charter::getOpportunities, Charter::setOpportunities);
        binder.forField(bugsTxtField)
                .bind(Charter::getBugs, Charter::setBugs);
        binder.forField(issuesTxtField)
                .bind(Charter::getIssues, Charter::setIssues);

        return binder;
    }

    private StringToIntegerConverter createNewStringToIntegerConverter() {
        return new StringToIntegerConverter("Ungültiger Wert eingetragen");
    }

    private Button buildCreateButton() {
        final Button button = new Button("Create", VaadinIcons.ADD_DOCK);
        button.addClickListener(e -> createModalCreateDialog());
        button.addStyleName(ValoTheme.BUTTON_HUGE);
        return button;
    }

    private Button buildEditButton(Charter charter) {
        Button button = new Button("Edit", VaadinIcons.EDIT);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        button.addClickListener(e -> createModalEditDialog(charter));
        return button;
    }

    private Button buildDeleteButton(Charter p) {
        Button button = new Button("Delete", VaadinIcons.CLOSE);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        button.addClickListener(e -> createModalConfirmationDialog(p));
        return button;
    }

    private Button buildSaveButton(Charter p) {
        Button button = new Button("Save", VaadinIcons.CHECK);
        button.addStyleName(ValoTheme.BUTTON_LARGE);
        button.addClickListener(e -> createModalSaveConfirmationDialog(p));
        return button;
    }

    private Button buildCancelButton(Window window) {
        Button button = new Button("Cancel", VaadinIcons.CLOSE_BIG);
        button.addStyleName(ValoTheme.BUTTON_DANGER);
        button.addClickListener((Button.ClickListener) event -> {
            window.close();
        });
        return button;
    }

    private Button buildConfirmSaveButton(Charter charter, Window window) {
        Binder<Charter> binder = createBinderForAllFields();
        Button button = new Button("Confirm Save", VaadinIcons.CHECK_CIRCLE_O);
        button.addStyleName(ValoTheme.BUTTON_LARGE);
        button.addClickListener((Button.ClickListener) event -> {
            try {
                binder.writeBean(charter);
                Notification.show("Das Speichern war erfolgreich");
            } catch (ValidationException e) {
                e.printStackTrace();
                Notification.show("Das Speichern hat nicht geklappt", Notification.Type.ERROR_MESSAGE);
            }
            saveCharter(charter);
            window.close();
            final Collection<Window> windows = UI.getCurrent().getWindows();
            for(Window existingWindow : windows) {
                if(existingWindow.getCaption().equals(EDIT_DIALOG_TITLE) ||
                        existingWindow.getCaption().equals(CREATE_DIALOG_TITLE)) {
                    existingWindow.close();
                }
            }
        });

        return button;
    }

    private void createModalEditDialog(Charter charter) {
        setInitialStateOfAllEditDialogComponents(charter);
        Window window = new Window(EDIT_DIALOG_TITLE);
        window.setModal(true);

        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout idLayout = new HorizontalLayout();
        idLayout.addComponentsAndExpand(idTxtField);
        HorizontalLayout mainLayout = new HorizontalLayout(charterNameTxtField, areasTxtField);
        HorizontalLayout secondLayout = new HorizontalLayout(startTxtField, nameOfTesterTxtField, taskBreakDownTxtField, durationTxtField);
        HorizontalLayout thirdLayout = new HorizontalLayout(testDesignAndExecutionTimeInPercentTxtField, bugInvestigationAndReportingTimeInPercentTxtField, sessionSetupTimeInPercentageTxtField, charterVsOpportunityTimeInPercentageTxtField);
        HorizontalLayout fourthLayout = new HorizontalLayout(dataFilesPathsTxtField,testNotesTxtField, opportunitiesTxtField, bugsTxtField, issuesTxtField);

        final Button cancelButton = buildCancelButton(window);
        final Button saveButton = buildSaveButton(charter);

        HorizontalLayout confirmationLayout = new HorizontalLayout(cancelButton, saveButton);
        verticalLayout.addComponents(idLayout, mainLayout, secondLayout,
                thirdLayout, fourthLayout, confirmationLayout);

        window.setContent(verticalLayout);
        UI.getCurrent().addWindow(window);
    }

    private void createModalCreateDialog() {
        setAllFieldsToEmpty();
        Charter charter = new Charter();
        Window window = new Window(CREATE_DIALOG_TITLE);
        window.setModal(true);

        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout idLayout = new HorizontalLayout();
        idTxtField.setReadOnly(true);
        idTxtField.setEnabled(false);
        idLayout.addComponentsAndExpand(idTxtField);
        HorizontalLayout mainLayout = new HorizontalLayout(charterNameTxtField, areasTxtField);
        HorizontalLayout secondLayout = new HorizontalLayout(startTxtField, nameOfTesterTxtField, taskBreakDownTxtField, durationTxtField);
        HorizontalLayout thirdLayout = new HorizontalLayout(testDesignAndExecutionTimeInPercentTxtField, bugInvestigationAndReportingTimeInPercentTxtField, sessionSetupTimeInPercentageTxtField, charterVsOpportunityTimeInPercentageTxtField);
        HorizontalLayout fourthLayout = new HorizontalLayout(dataFilesPathsTxtField,testNotesTxtField, opportunitiesTxtField, bugsTxtField, issuesTxtField);

        final Button cancelButton = buildCancelButton(window);
        final Button saveButton = buildSaveButton(charter);

        HorizontalLayout confirmationLayout = new HorizontalLayout(cancelButton, saveButton);
        verticalLayout.addComponents(idLayout, mainLayout, secondLayout,
                thirdLayout, fourthLayout, confirmationLayout);

        window.setContent(verticalLayout);
        UI.getCurrent().addWindow(window);
    }

    private void setAllFieldsToEmpty() {
        this.idTxtField.setValue("");
        this.charterNameTxtField.setValue("");
        this.areasTxtField.setValue("");
        this.startTxtField.setValue(startTxtField.getEmptyValue());
        this.nameOfTesterTxtField.setValue("");
        this.taskBreakDownTxtField.setValue("");
        this.durationTxtField.setValue("");
        this.testDesignAndExecutionTimeInPercentTxtField.setValue("");
        this.bugInvestigationAndReportingTimeInPercentTxtField.setValue("");
        this.sessionSetupTimeInPercentageTxtField.setValue("");
        this.charterVsOpportunityTimeInPercentageTxtField.setValue("");
        this.dataFilesPathsTxtField.setValue("");
        this.testNotesTxtField.setValue("");
        this.opportunitiesTxtField.setValue("");
        this.bugsTxtField.setValue("");
        this.issuesTxtField.setValue("");
    }


    private void setInitialStateOfAllEditDialogComponents(Charter charter) {
        idTxtField.setValue(Optional.ofNullable(charter.getId()).orElse(""));
        idTxtField.setReadOnly(true);
        idTxtField.setEnabled(false);
        charterNameTxtField.setValue(Optional.ofNullable(charter.getCharterName()).orElse(""));
        areasTxtField.setValue(Optional.ofNullable(charter.getAreas()).orElse(""));
        startTxtField.setValue(
                charter.getStart() == null ? startTxtField.getEmptyValue() : charter.getStart()
        );
        startTxtField.setDateFormat("dd.MM.yyyy HH:mm");
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
        issuesTxtField.setValue(Optional.ofNullable(charter.getIssues()).orElse(""));
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

        Window confirmationDialogWindow = createConfirmationDialogWindow("Confirm Delete Dialog");

        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        Button confirmDeleteButton = new Button("Delete", VaadinIcons.EXCLAMATION_CIRCLE);
        Button cancelButton = buildCancelButton(confirmationDialogWindow);
        Label label = new Label("Möchtest du die Charter wirklich löschen?");
        horizontalLayout.addComponents(cancelButton, confirmDeleteButton);
        verticalLayout.addComponents(label, horizontalLayout);

        confirmDeleteButton.addClickListener((Button.ClickListener) event -> {
            deleteCharter(c);
            confirmationDialogWindow.close();
        });

        confirmationDialogWindow.setContent(verticalLayout);
        UI.getCurrent().addWindow(confirmationDialogWindow);
    }

    private void createModalSaveConfirmationDialog(Charter charter) {

        Window confirmationDialogWindow = createConfirmationDialogWindow("Confirm Save Dialog");

        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        Button confirmSaveButton = buildConfirmSaveButton(charter, confirmationDialogWindow);
        Button cancelButton = buildCancelButton(confirmationDialogWindow);
        Label label = new Label("Möchtest du die Charter wirklich speichern?");
        horizontalLayout.addComponents(cancelButton, confirmSaveButton);
        verticalLayout.addComponents(label, horizontalLayout);

        confirmationDialogWindow.setContent(verticalLayout);
        UI.getCurrent().addWindow(confirmationDialogWindow);
    }

    private Window createConfirmationDialogWindow(String dialogTitle) {
        Window confirmationDialogWindow = new Window(dialogTitle);

        confirmationDialogWindow.setHeight("200px");
        confirmationDialogWindow.setWidth("400px");
        confirmationDialogWindow.setPositionX(200);
        confirmationDialogWindow.setPositionY(50);
        confirmationDialogWindow.setModal(true);
        return confirmationDialogWindow;
    }

}
