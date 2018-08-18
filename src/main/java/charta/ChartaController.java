package charta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class ChartaController {

    @Autowired
    private ChartaRepository chartaRepository;

    @RequestMapping(value = "/charta", method = POST)
    public Charta createAndReturnCharta(@RequestBody Charta charta) {
        chartaRepository.save(charta);
        return charta;
    }

    @RequestMapping(value = "/charta", method = GET)
    public List<Charta> getAllChartaNamesAsConcatString() {
        return chartaRepository.findAll();
    }

    @RequestMapping(value = "/charta", method = DELETE)
    public void deleteAllChartas() {
        chartaRepository.deleteAll();
    }
}
