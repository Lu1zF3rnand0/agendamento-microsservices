package br.com.luiz.transportadoraservice.domain.service;

import br.com.luiz.transportadoraservice.domain.exception.TransportadoraNaoEncontradoException;
import br.com.luiz.transportadoraservice.domain.model.Transportadora;
import br.com.luiz.transportadoraservice.domain.repository.TransportadoraRepository;
import br.com.luiz.transportadoraservice.dto.TransportadoraDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TransportadoraService {

    private TransportadoraRepository transportadoraRepository;

    private ModelMapper modelMapper;

    public List<TransportadoraDto> findAllTransportadoras() {
        List<TransportadoraDto> transportadoraDto = new ArrayList<>();
        transportadoraRepository.findAll().forEach(transportadora -> transportadoraDto.add(mapTransportadoraModelToDto(transportadora)));
        return transportadoraDto;
    }


    @Transactional
    public Transportadora save(Transportadora transportadora) {
        return transportadoraRepository.save(transportadora);
    }

    public void deleteByCodigo(String codigo) {
        Transportadora transportadora = buscarOuFalhar(codigo);
        transportadoraRepository.deleteById(transportadora.getId());
    }

    public TransportadoraDto mapTransportadoraModelToDto(Transportadora transportadora) {

        TransportadoraDto transportadoraDto = new TransportadoraDto();

        transportadoraDto.setCodigo(transportadora.getCodigo());
        transportadoraDto.setCnpj(transportadora.getCnpj());
        transportadoraDto.setNome(transportadora.getNome());
        transportadoraDto.setEmail(transportadora.getEmail());
        transportadoraDto.setTelefone(transportadora.getTelefone());

        return transportadoraDto;
    }


    public Transportadora mapTransportadoraDtoToModel(TransportadoraDto transportadoraDto) {

        Transportadora transportadora = new Transportadora();

        transportadora.setCnpj(transportadoraDto.getCnpj());
        transportadora.setNome(transportadoraDto.getNome());
        transportadora.setEmail(transportadoraDto.getEmail());
        transportadora.setTelefone(transportadoraDto.getTelefone());

        return transportadora;
    }

    public Transportadora buscarOuFalhar(String transportadoraCodigo) {
        return transportadoraRepository.findByCodigo(transportadoraCodigo)
                .orElseThrow(() -> new TransportadoraNaoEncontradoException(transportadoraCodigo));
    }

    public void copyToDomainObject(TransportadoraDto transportadoraDto, Transportadora transportadora) {
        modelMapper.map(transportadoraDto, transportadora);
    }
}
