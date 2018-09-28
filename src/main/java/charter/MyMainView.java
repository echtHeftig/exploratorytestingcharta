package charter;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

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
        grid.addComponentColumn(this::buildDeleteButton);
        verticalLayout.addComponent(grid);
        setContent(verticalLayout);
    }

    private Button buildDeleteButton(Charter p) {
        Button button = new Button(VaadinIcons.CLOSE);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        button.addClickListener(e -> createModalConfirmationDialog(p));
        return button;
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
