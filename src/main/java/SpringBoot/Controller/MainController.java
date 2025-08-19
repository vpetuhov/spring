package SpringBoot.Controller;

import SpringBoot.Model.Person1;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/app/v1")
public class MainController {

    @GetMapping("/getRequest")
    public String getRequest(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name)
            throws InterruptedException {

        if(id <= 10) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "id должен быть больше 10");
        }

        if(name.length() <= 5) throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "длина name должна быть больше 5");

        if (id > 10 && id < 50) {
            TimeUnit.MILLISECONDS.sleep(1000);
        } else {
            TimeUnit.MILLISECONDS.sleep(500);
        }

        return String.format(
                "{\n" +
                        "    \"Person1\": {\n" +
                        "        \"id\": %d,\n" +
                        "        \"name\": \"%s\"\n" +
                        "    }\n" +
                        "}",
                id,
                name
        );
    }

    @PostMapping("/postRequest")
    public String changePerson(@RequestBody Person1 person){
        if (person.getName() == null || person.getName().isEmpty() ||
                person.getSurname() == null || person.getSurname().isEmpty() ||
                person.getAge() == null) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "name, surname и age не должны быть пустыми");
        }

        return String.format(
                "{\n" +
                        "    \"Person1\": {\n" +
                        "        \"name\": \"%s\",\n" +
                        "        \"surname\": \"%s\",\n" +
                        "        \"age\": %d\n" +
                        "    },\n" +
                        "    \"Person2\": {\n" +
                        "        \"name\": \"%s\",\n" +
                        "        \"surname\": \"%s\",\n" +
                        "        \"age\": %d\n" +
                        "    }\n" +
                        "}",
                person.getName(), person.getSurname(), person.getAge(),
                person.getSurname(), person.getName(), person.getAge() * 2);

    }
}
