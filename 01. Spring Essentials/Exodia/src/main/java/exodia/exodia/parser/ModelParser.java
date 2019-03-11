package exodia.exodia.parser;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class ModelParser {

    private ModelMapper modelMapper;

    public ModelParser() {
        this.modelMapper = new ModelMapper();
    }

    public <S, D> D convert(S source, Class<D> destinationClass) {
        return this.modelMapper.map(source, destinationClass);
    }
}