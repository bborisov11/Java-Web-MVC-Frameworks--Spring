package exodia.exodia.web.controllers;

import exodia.exodia.models.binding.DocumentBindingModel;
import exodia.exodia.models.service.DocumentServiceModel;
import exodia.exodia.models.view.DocumentDetailsViewModel;
import exodia.exodia.parser.ModelParser;
import exodia.exodia.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Controller
public class DocumentController {

    private DocumentService documentService;
    private ModelParser modelParser;

    @Autowired
    public DocumentController(DocumentService documentService, ModelParser modelParser) {
        this.documentService = documentService;
        this.modelParser = modelParser;
    }

    @GetMapping("/schedule")
    @SuppressWarnings("unchecked")
    public String getCreateDocument(Model model) {

        model.addAttribute("head", "views/head");
        model.addAttribute("guestNav", "views/guest-nav");
        model.addAttribute("view", "schedule");
        model.addAttribute("footer", "views/footer");

        return "base-layout";
    }

    @PostMapping("/schedule")
    public String postCreateDocument(DocumentBindingModel documentBindingModel) {

        this.documentService.createDocument(this.modelParser.convert(documentBindingModel, DocumentServiceModel.class));
        //TODO redirect to details
        return "redirect:/home";
    }

    @GetMapping("/details")
    public String getDetailsDocument(Model model, HttpServletRequest request) {

        String userId = request.getQueryString().split("=")[1];

        DocumentDetailsViewModel doc = this.modelParser.convert(this.documentService.getById(userId), DocumentDetailsViewModel.class);

        model.addAttribute("head", "views/head");
        model.addAttribute("guestNav", "views/guest-nav");

        model.addAttribute("documentID", doc.getId());
        model.addAttribute("title", doc.getTitle());
        model.addAttribute("content", doc.getContent());

        model.addAttribute("view", "details");
        model.addAttribute("footer", "views/footer");

        return "base-layout";
    }

    @GetMapping("/print")
    public String getPrintDocument(@RequestParam("id") String id, Model model) {

        DocumentDetailsViewModel doc = this.modelParser.convert(this.documentService.getById(id), DocumentDetailsViewModel.class);

        model.addAttribute("head", "views/head");
        model.addAttribute("guestNav", "views/guest-nav");

        model.addAttribute("documentID", doc.getId());
        model.addAttribute("title", doc.getTitle());
        model.addAttribute("content", doc.getContent());

        model.addAttribute("view", "print");
        model.addAttribute("footer", "views/footer");

        return "base-layout";
    }

    @PostMapping("/print")
    @Transactional
    public String deleteDocument(@RequestParam("id") String id) {
            this.documentService.removeDocument(id);
        return "redirect:/home";
    }

}
