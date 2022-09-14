package br.com.luiz.agendamentoservice.domain.service;

import br.com.luiz.agendamentoservice.domain.exception.TransportadoraNaoEncontradaException;
import br.com.luiz.agendamentoservice.domain.model.Transportadora;
import br.com.luiz.agendamentoservice.domain.repository.TransportadoraRepository;
import br.com.luiz.agendamentoservice.dto.TransportadoraDto;
import br.com.luiz.agendamentoservice.proxy.TransportadoraProxy;
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

    private TransportadoraProxy transportadoraProxy;

    private ModelMapper modelMapper;

//    public List<TransportadoraDto> findAllTransportadoras() {
//        List<TransportadoraDto> transportadoraDto = new ArrayList<>();
//        transportadoraRepository.findAll().forEach(transportadora -> transportadoraDto.add(mapTransportadoraModelToDto(transportadora)));
//        return transportadoraDto;
//    }


    @Transactional
    public Transportadora save(Transportadora transportadora) {
        return transportadoraRepository.save(transportadora);
    }

    //
//    public void deleteByCodigo(String codigo) {
//        Transportadora transportadora = buscarOuFalhar(codigo);
//        transportadoraRepository.deleteById(transportadora.getId());
//    }
//
    public TransportadoraDto mapTransportadoraModelToDto(Transportadora transportadora) {

        TransportadoraDto transportadoraDto = new TransportadoraDto();

        transportadoraDto.setCodigo(transportadora.getCodigo());
        transportadoraDto.setNome(transportadora.getNome());
        transportadoraDto.setEmail(transportadora.getEmail());

        return transportadoraDto;
    }


    public Transportadora mapTransportadoraDtoToModel(TransportadoraDto transportadoraDto) {

        Transportadora transportadora = new Transportadora();

        transportadora.setCodigo(transportadoraDto.getCodigo());
        transportadora.setNome(transportadoraDto.getNome());
        transportadora.setEmail(transportadoraDto.getEmail());

        return transportadora;
    }

    public Transportadora buscarOuFalhar(String transportadoraCodigo) {
        return transportadoraRepository.findByCodigo(transportadoraCodigo)
                .orElseThrow(() -> new TransportadoraNaoEncontradaException(transportadoraCodigo));
    }

    public void copyToDomainObject(TransportadoraDto transportadoraDto, Transportadora transportadora) {
        modelMapper.map(transportadoraDto, transportadora);
    }

    public boolean temNoBanco(String codigoTransp) {
        return transportadoraRepository.findByCodigo(codigoTransp).isPresent();
    }

    public List<Transportadora> retornaTransportadorasOk(List<Transportadora> transportadoras) {
        List<Transportadora> transportadorasOk = new ArrayList<>();

        transportadoras.forEach(transp -> {
            if (temNoBanco(transp.getCodigo())) {
                var transportadora = transportadoraRepository.findByCodigo(transp.getCodigo()).get();
                transp.setCodigo(transportadora.getCodigo());
                transp.setNome(transportadora.getNome());
                transp.setEmail(transportadora.getEmail());

                transportadorasOk.add(transp);
            } else {
                var transportadora = transportadoraProxy.findTransportadoraByCodigo(transp.getCodigo());
                transp.setCodigo(transportadora.getCodigo());
                transp.setNome(transportadora.getNome());
                transp.setEmail(transportadora.getEmail());

                transportadorasOk.add(transp);

                transportadoraRepository.save(transp);
                transportadoraRepository.flush();
            }


        });

        return transportadorasOk;
    }

}
