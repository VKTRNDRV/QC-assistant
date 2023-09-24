package com.example.qcassistant.web;

import com.example.qcassistant.domain.dto.destination.DestinationAddDto;
import com.example.qcassistant.domain.dto.destination.DestinationEditDto;
import com.example.qcassistant.service.DestinationService;
import com.example.qcassistant.service.LanguageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/destinations")
public class DestinationController {

    private LanguageService languageService;

    private DestinationService destinationService;

    public DestinationController(LanguageService languageService, DestinationService destinationService) {
        this.languageService = languageService;
        this.destinationService = destinationService;
    }


    @GetMapping("/add")
    public String getAddDestination(Model model){
        model.addAttribute("allLanguages",
                this.languageService.getAllLanguages());
        model.addAttribute("destinationAddDto",
                new DestinationAddDto());
        return "destinations-add";
    }

    @PostMapping("/add")
    public String addDestination(@ModelAttribute DestinationAddDto destinationAddDto,
                                 Model model, RedirectAttributes redirectAttributes){
        try {
            this.destinationService.addDestination(destinationAddDto);
        }catch (RuntimeException exc){
            model.addAttribute("allLanguages", this.languageService.getAllLanguages());
            model.addAttribute("error", true);
            model.addAttribute("message", exc.getMessage());
            return "destinations-add";
        }

        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/";
    }

    @GetMapping("/all")
    public String getAllDestinations(Model model){
        model.addAttribute("destinations",
                this.destinationService.displayDestinations());
        return "destinations-all";
    }


    @GetMapping("/edit/{id}")
    public String getEditDestination(@PathVariable Long id, Model model){
        model.addAttribute("destinationUpdated",
                this.destinationService.getDestinationEditById(id));
        model.addAttribute("allLanguages",
                this.languageService.getAllLanguages());

        return "destination-edit";
    }

    @PostMapping("/edit")
    public String editDestination(@ModelAttribute DestinationEditDto editDto, Model model,
                                  RedirectAttributes redirectAttributes){
        try {
            this.destinationService.editDestination(editDto);
        }catch (RuntimeException exc){
            model.addAttribute("allLanguages",
                    this.languageService.getAllLanguages());
            model.addAttribute("error", true);
            model.addAttribute("message", exc.getMessage());
        }

        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/";
    }
}
