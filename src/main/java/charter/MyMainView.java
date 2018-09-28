package charter;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
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
        button.addClickListener(e -> deleteCharter(p));
        return button;
    }

    private void deleteCharter(Charter p) {
        charterRepository.delete(p);
        grid.setItems(charterRepository.findAll());
    }
}
