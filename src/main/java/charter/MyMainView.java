package charter;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path = "")
public class MyMainView extends UI {

    @Autowired
    CharterController charterController;

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout verticalLayout = new VerticalLayout();
        Grid<Charter> grid = new Grid<>(Charter.class);
        grid.setItems(charterController.getAllCharters());
        grid.setWidth(100, Unit.PERCENTAGE);
        grid.setColumnReorderingAllowed(true);
        grid.setColumnOrder("id", "charterName", "nameOfTester");
        verticalLayout.addComponent(grid);
        setContent(verticalLayout);
    }
}
