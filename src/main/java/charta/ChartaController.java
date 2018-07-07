package charta;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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
    public Charta createAndReturnChartaGeneratedId() {
        Charta charta = new Charta(counter.incrementAndGet(), "Charter Name", "Dies sind versch. Areas", new Calendar.Builder().setDate(2018, 7, 3).build(),
                "Susanna", "", 13, 25, 25, 40,
                10, "It/is/a/path", "Dies ist eine Notiz", "Es gab noch ein paar weitere Moeglichkeiten",
                "Dies ist ein Bug. Und noch ein Bug", "Issue 1, Noch ein Issue 2");
        chartaList.add(charta);
        return charta;
    }

}
