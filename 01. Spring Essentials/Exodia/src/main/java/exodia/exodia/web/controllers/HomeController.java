package exodia.exodia.web.controllers;

import exodia.exodia.models.view.DocumentHomeViewModel;
import exodia.exodia.parser.ModelParser;
import exodia.exodia.services.DocumentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private DocumentService documentService;
    private ModelParser modelParser;

    public HomeController(DocumentService documentService, ModelParser modelParser) {
        this.documentService = documentService;
        this.modelParser = modelParser;
    }

    @GetMapping("/")
    public String getIndexPage(Model model) {

        model.addAttribute("head", "views/head");
        model.addAttribute("guestNav", "views/guest-nav");
        model.addAttribute("view", "index");
        model.addAttribute("footer", "views/footer");

        return "base-layout";
    }

    @GetMapping("/home")
    public String getHomePage(Model model) {

        List<DocumentHomeViewModel> documents = this.documentService.getAllDocuments()
                .stream().map(d -> this.modelParser.convert(d, DocumentHomeViewModel.class))
                .collect(Collectors.toList());

        model.addAttribute("head", "views/head");
        model.addAttribute("guestNav", "views/home-nav");
        model.addAttribute("documents", documents);
        model.addAttribute("view", "home");
        model.addAttribute("footer", "views/footer");

        return "base-layout";
    }
}
