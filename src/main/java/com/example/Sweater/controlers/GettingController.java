package com.example.Sweater.controlers;


import com.example.Sweater.models.Message;
import com.example.Sweater.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GettingController {


    private final MessageService messageService;
    @Autowired
    public GettingController(MessageService messageService) {
        this.messageService = messageService;
    }


    @GetMapping("/")
    public String welcome(Model model){
        model.addAttribute("messages", messageService.allMessage());
        return "main";
    }
    @GetMapping("/greeting")
    public String getting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model){
        model.addAttribute("name", name);
        return "greeting";
    }

    @PostMapping("/createMessage")
    public String createMessage(@RequestParam(name = "text") String text,
                                @RequestParam(name = "tag") String tag)
                                {
        messageService.created(text, tag);
        return "redirect:/";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam(name = "tag") String filter, Model model) {
        if(filter != null && !filter.isEmpty()) model.addAttribute("messages", messageService.allTagMassage(filter));
        else model.addAttribute("messages", messageService.allMessage());
        return "main";
    }
}
