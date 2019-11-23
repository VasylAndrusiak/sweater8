package sweater.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sweater.domain.User;
import sweater.repos.MessageRepo;
import sweater.domain.Message;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }
    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model){
        Iterable <Message> messages = messageRepo.findAll();
        if(filter != null && !filter.isEmpty()){
            messages = messageRepo.findByName(filter);
        }else{
            messages = messageRepo.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }
    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user, @RequestParam String name, @RequestParam String email, Map<String, Object> model){
        Message message = new Message(name, email, user);
        messageRepo.save(message);

        Iterable <Message> messages = messageRepo.findAll();

        model.put("messages", messages);
        model.put("filter", "");
        return "main";
    }

}









