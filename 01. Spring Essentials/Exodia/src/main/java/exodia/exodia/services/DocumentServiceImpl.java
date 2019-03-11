package exodia.exodia.services;

import exodia.exodia.entities.Document;
import exodia.exodia.models.service.DocumentServiceModel;
import exodia.exodia.parser.ModelParser;
import exodia.exodia.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService{

    private DocumentRepository documentRepository;
    private ModelParser modelParser;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, ModelParser modelParser) {
        this.documentRepository = documentRepository;
        this.modelParser = modelParser;
    }


    @Override
    public DocumentServiceModel createDocument(DocumentServiceModel userServiceModel) {
         this.documentRepository.save(this.modelParser.convert(userServiceModel, Document.class));
        return userServiceModel;
    }

    @Override
    public List<DocumentServiceModel> getAllDocuments() {
        return this.documentRepository.findAll().stream()
                .map(d -> this.modelParser.convert(d, DocumentServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public DocumentServiceModel getById(String id) {
        Optional<Document> document = this.documentRepository.findById(id);
        if(document.isPresent()) {
            return this.modelParser.convert(document.get(), DocumentServiceModel.class);
        }
        return null;
    }

    @Override
    public void removeDocument(String id) {
         this.documentRepository.removeDocumentById(id);
    }

    @Override
    public String getByTitle(String title) {
        return this.documentRepository.findByTitle(title).getId();
    }
}
