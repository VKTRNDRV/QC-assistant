package com.example.qcassistant.web.sponsor;

import com.example.qcassistant.domain.dto.sponsor.MedidataSponsorAddDto;
import com.example.qcassistant.domain.dto.sponsor.MedidataSponsorEditDto;
import com.example.qcassistant.service.sponsor.MedidataSponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/medidata/sponsors")
public class MedidataSponsorController {

    private MedidataSponsorService sponsorService;


    @Autowired
    public MedidataSponsorController(MedidataSponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }

    @GetMapping
    public String getAllSponsors(Model model){
        model.addAttribute("sponsors", this.sponsorService.displayAllSponsors());
        return "medidata-sponsors";
    }

    @GetMapping("/add")
    public String getAddSponsor(Model model){
        model.addAttribute("sponsorAddDto",
                new MedidataSponsorAddDto());

        return "medidata-sponsors-add";
    }

    @PostMapping("/add")
    public String addSponsor(@ModelAttribute MedidataSponsorAddDto sponsorAddDto,
                             Model model, RedirectAttributes redirectAttributes){
        try {
            this.sponsorService.addSponsor(sponsorAddDto);
        }catch (RuntimeException exc){
            model.addAttribute("sponsorAddDto", sponsorAddDto);
            model.addAttribute("error", true);
            model.addAttribute("message", exc.getMessage());
            return "medidata-sponsors-add";
        }

        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String getEditSponsor(@PathVariable Long id, Model model){
        model.addAttribute("sponsorEditDto",
                this.sponsorService.getSponsorEditById(id));
        return "medidata-sponsors-edit";
    }

    @PostMapping("/edit")
    public String editSponsor(@ModelAttribute MedidataSponsorEditDto sponsorEditDto,
                              Model model, RedirectAttributes redirectAttributes){
        try {
            this.sponsorService.editSponsor(sponsorEditDto);
        }catch (RuntimeException exc){
            model.addAttribute("sponsorEditDto", sponsorEditDto);
            model.addAttribute("error", true);
            model.addAttribute("message", exc.getMessage());
            return "medidata-sponsors-edit";
        }

        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/";
    }
}
