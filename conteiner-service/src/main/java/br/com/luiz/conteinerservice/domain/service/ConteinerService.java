package br.com.luiz.conteinerservice.domain.service;

import br.com.luiz.conteinerservice.domain.exception.ConteinerNaoEncontradoException;
import br.com.luiz.conteinerservice.domain.model.Conteiner;
import br.com.luiz.conteinerservice.domain.repository.ConteinerRepository;
import br.com.luiz.conteinerservice.dto.ConteinerDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ConteinerService {

    private ConteinerRepository conteinerRepository;

    private ModelMapper modelMapper;

    public List<ConteinerDto> findAllConteiners() {
        List<ConteinerDto> conteinerDto = new ArrayList<>();
        conteinerRepository.findAll().forEach(conteiner -> conteinerDto.add(mapConteinerModelToDto(conteiner)));
        return conteinerDto;
    }


    @Transactional
    public Conteiner save(Conteiner conteiner) {
        return conteinerRepository.save(conteiner);
    }

    public void deleteByCodigo(String codigo) {
        Conteiner conteiner = buscarOuFalhar(codigo);
        conteinerRepository.deleteById(conteiner.getId());
    }

    public ConteinerDto mapConteinerModelToDto(Conteiner conteiner) {

        ConteinerDto conteinerDto = new ConteinerDto();

        conteinerDto.setCodigo(conteiner.getCodigo());
        conteinerDto.setNumero(conteiner.getNumero());
        conteinerDto.setTamanho(conteiner.getTamanho());
        conteinerDto.setStatus(conteiner.getStatus());

        return conteinerDto;
    }


    public Conteiner mapConteinerDtoToModel(ConteinerDto conteinerDto) {

        Conteiner conteiner = new Conteiner();

        conteiner.setNumero(conteinerDto.getNumero());
        conteiner.setTamanho(conteinerDto.getTamanho());
        conteiner.setStatus(conteinerDto.getStatus());

        return conteiner;
    }

    public Conteiner buscarOuFalhar(String conteinerCodigo) {
        return conteinerRepository.findByCodigo(conteinerCodigo)
                .orElseThrow(() -> new ConteinerNaoEncontradoException(conteinerCodigo));
    }

    public void copyToDomainObject(ConteinerDto conteinerDto, Conteiner conteiner) {
        modelMapper.map(conteinerDto, conteiner);
    }
}
