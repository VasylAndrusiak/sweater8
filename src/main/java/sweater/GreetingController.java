package sweater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }
    @GetMapping
    public String main(Map<String, Object> model){
        Iterable <Message> messages = messageRepo.findAll();

        model.put("messages", "hello, world!!!!!");
        return "main";
    }
    @PostMapping
    public String add(@RequestParam String name, @RequestParam String email, Map<String, Object> model){
        Message message = new Message(name, email);
        messageRepo.save(message);

        Iterable <Message> messages = messageRepo.findAll();

        model.put("messages", messages);
        return "main";
    }
    @PostMapping ("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {

        List<Message> messages = messageRepo.findByName(filter);

        model.put("messages", messages);
        return "main";
    }
}









