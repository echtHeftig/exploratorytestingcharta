package charta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ChartaRepository chartaRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        chartaRepository.deleteAll();

        chartaRepository.save(new Charta("Charter Name mit RandomId", "Dies sind versch. Areas", new Calendar.Builder().setDate(2018, 7, 3).build(),
                "Susanna", "", 13, 25, 25, 40,
                10, "It/is/a/path", "Dies ist eine Notiz", "Es gab noch ein paar weitere Moeglichkeiten",
                "Dies ist ein Bug. Und noch ein Bug", "Issue 1, Noch ein Issue 2"));
        chartaRepository.save(new Charta("Charter Zwei", "2Dies sind versch. Areas", new Calendar.Builder().setDate(2019, 7, 3).build(),
                "Susanna", "", 13, 25, 25, 40,
                10, "It/is/a/path", "Dies ist eine Notiz", "Es gab noch ein paar weitere Moeglichkeiten",
                "Dies ist ein Bug. Und noch ein Bug", "Issue 1, Noch ein Issue 2"));

        for(Charta charta : chartaRepository.findAll()) {
            System.out.println("####");
            System.out.println(charta.getCharterName());
            System.out.println("$$$$");
        }
    }
}
