package charter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public class CharterController {

    @Autowired
    private CharterRepository charterRepository;

    private static final Logger log = LoggerFactory.getLogger(CharterController.class);

    @RequestMapping(value = "/charters", method = POST)
    public ResponseEntity<Charter> createAndReturnCharter(@RequestBody Charter charter) {
        charterRepository.save(charter);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.LOCATION, "/charters/"+charter.getId());
        return new ResponseEntity<>(charter, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/charters", method = GET)
    public List<Charter> getAllCharters() {
        return charterRepository.findAll();
    }

    @RequestMapping(value = "/charters/{id}", method = GET)
    public ResponseEntity<Object> getSingleCharter(@PathVariable String id) {
        Optional<Charter> charterOptional = charterRepository.findById(id);
        if(!charterOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(charterOptional);
    }

    @RequestMapping(value = "/charters/{id}", method = PUT)
    public Optional<Charter> createOrUpdateCharter(@RequestBody Charter charter, @PathVariable String id) {
        Optional<Charter> charterOptional = charterRepository.findById(id);
        if(!charterOptional.isPresent()) {
            log.debug(String.format(
                    "Es gibt keine Charter mit der id {%s}. Neue Charter mit neuer CharterId wird erstellt", id)
            );
            charterRepository.save(charter);
            return Optional.of(charter);
        } else {
            updateCharterOptional(charterOptional, charter);
            charterRepository.save(charterOptional.get());
        }
        return charterRepository.findById(id);
    }

    @RequestMapping(value = "/charters", method = DELETE)
    public void deleteAllCharters() {
        charterRepository.deleteAll();
    }

    @RequestMapping(value = "/charters/{id}", method = DELETE)
    public void deleteSingleCharter(@PathVariable String id) {
        charterRepository.deleteById(id);
    }

    private void updateCharterOptional(Optional<Charter> charterOptional, Charter charter) {
        if(charterOptional.isPresent()) {
            charterOptional.get().setCharterName(charter.getCharterName());
            charterOptional.get().setAreas(charter.getAreas());
            charterOptional.get().setStart(charter.getStart());
            charterOptional.get().setNameOfTester(charter.getNameOfTester());
            charterOptional.get().setTaskBreakDown(charter.getTaskBreakDown());
            charterOptional.get().setDuration(charter.getDuration());
            charterOptional.get().setTestDesignAndExecutionTimeInPercent(charter.getTestDesignAndExecutionTimeInPercent());
            charterOptional.get().setBugInvestigationAndReportingTimeInPercent(charter.getBugInvestigationAndReportingTimeInPercent());
            charterOptional.get().setSessionSetupTimeInPercentage(charter.getSessionSetupTimeInPercentage());
            charterOptional.get().setCharterVsOpportunityTimeInPercentage(charter.getCharterVsOpportunityTimeInPercentage());
            charterOptional.get().setDataFilesPaths(charter.getDataFilesPaths());
            charterOptional.get().setTestNotes(charter.getTestNotes());
            charterOptional.get().setOpportunities(charter.getOpportunities());
            charterOptional.get().setBugs(charter.getBugs());
            charterOptional.get().setIssues(charter.getIssues());
        }
    }
}
