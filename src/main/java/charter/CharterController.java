package charter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class CharterController {

    @Autowired
    private CharterRepository charterRepository;

    @RequestMapping(value = "/charters", method = POST)
    public Charter createAndReturnCharter(@RequestBody Charter charter) {
        charterRepository.save(charter);
        return charter;
    }

    @RequestMapping(value = "/charters", method = GET)
    public List<Charter> getAllCharters() {
        return charterRepository.findAll();
    }

    @RequestMapping(value = "/charters", method = DELETE)
    public void deleteAllCharters() {
        charterRepository.deleteAll();
    }
}
