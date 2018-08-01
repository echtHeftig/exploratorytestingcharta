package charta;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class ChartaController {

    private final AtomicLong counter = new AtomicLong();
    private List<Charta> chartaList = new ArrayList<>();

    @RequestMapping(value = "/charta/fixid", method = POST)
    public Charta createAndReturnCharta(@RequestParam(value = "id", defaultValue = "1") long id) {
        return new Charta(id, "Charter Name mit RandomId", "Dies sind versch. Areas", new Calendar.Builder().setDate(2018, 7, 3).build(),
                "Susanna", "", 13, 25, 25, 40,
                10, "It/is/a/path", "Dies ist eine Notiz", "Es gab noch ein paar weitere Moeglichkeiten",
                "Dies ist ein Bug. Und noch ein Bug", "Issue 1, Noch ein Issue 2");
    }

    @RequestMapping(value = "/charta", method = POST)
    public Charta createAndReturnCharta(@RequestBody Charta charta) {
        chartaList.add(charta);
        return charta;
    }

    @RequestMapping(value = "/charta/{id}", method = GET)
    public Charta getCharta(@PathVariable("id") long id) {
        for (Charta charta : chartaList) {
            if (id == charta.getId()) {
                return charta;
            }
        }
        return new Charta(counter.incrementAndGet(), "HAHA it's a teapot", "Dies sind versch. Areas", new Calendar.Builder().setDate(2018, 7, 3).build(),
                "Susanna", "", 13, 25, 25, 40,
                10, "It/is/a/path", "Dies ist eine Notiz", "Es gab noch ein paar weitere Moeglichkeiten",
                "Dies ist ein Bug. Und noch ein Bug", "Issue 1, Noch ein Issue 2");
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
}
