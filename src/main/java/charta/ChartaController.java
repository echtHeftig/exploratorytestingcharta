package charta;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class ChartaController {

    private List<Charta> chartaList = new ArrayList<>();

    @RequestMapping(value = "/charta", method = POST)
    public Charta createAndReturnCharta(@RequestBody Charta charta) {
        chartaList.add(charta);
        return charta;
    }

    @RequestMapping(value = "/charta", method = GET)
    public String getAllChartaNamesAsConcatString() {
        StringBuilder charterList = new StringBuilder("Charters-Names: ");
        if (chartaList.isEmpty()) {
            return "The list does not contain any Charters";
        } else {
            for (Charta charta : chartaList) {
                charterList.append("\n").append(charta.getCharterName());
            }
        }
        return charterList.toString();
    }

    @RequestMapping(value = "/charta", method = DELETE)
    public String deleteAllChartas() {
        chartaList.clear();
        return chartaList.toString();
    }
}
