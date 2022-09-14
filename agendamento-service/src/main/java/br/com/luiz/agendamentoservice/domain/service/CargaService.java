package br.com.luiz.agendamentoservice.domain.service;

import br.com.luiz.agendamentoservice.domain.exception.CargaNaoEncontradaException;
import br.com.luiz.agendamentoservice.domain.model.Carga;
import br.com.luiz.agendamentoservice.domain.repository.CargaRepository;
import br.com.luiz.agendamentoservice.dto.CargaDto;
import br.com.luiz.agendamentoservice.proxy.CargaSoltaProxy;
import br.com.luiz.agendamentoservice.proxy.ConteinerProxy;
import br.com.luiz.agendamentoservice.proxy.VeiculoProxy;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CargaService {

    private CargaRepository cargaRepository;

    private ConteinerProxy conteinerProxy;

    private VeiculoProxy veiculoProxy;

    private CargaSoltaProxy cargaSoltaProxy;

    private ModelMapper modelMapper;

//    public List<AgendamentoDto> findAllAgendamentos() {
//        List<AgendamentoDto> agendamentoDto = new ArrayList<>();
//        agendamentoRepository.findAll().forEach(agendamento -> agendamentoDto.add(mapAgendamentoModelToDto(agendamento)));
//        return agendamentoDto;
//    }


    @Transactional
    public Carga save(Carga carga) {
        return cargaRepository.saveAndFlush(carga);
    }

    //
//    public void deleteByCodigo(String codigo) {
//        Agendamento agendamento = buscarOuFalhar(codigo);
//        agendamentoRepository.deleteById(agendamento.getId());
//    }
//
    public CargaDto mapCargaModelToDto(Carga carga) {

        CargaDto cargaDto = new CargaDto();

        cargaDto.setCodigo(carga.getCodigo());
        cargaDto.setDescricao(carga.getDescricao());
        cargaDto.setTipoCarga(carga.getTipoCarga());

        return cargaDto;
    }


    public Carga mapCargaDtoToModel(CargaDto cargaDto) {

        Carga carga = new Carga();

        carga.setCodigo(cargaDto.getCodigo());
        carga.setDescricao(cargaDto.getDescricao());
        carga.setTipoCarga(cargaDto.getTipoCarga());

        return carga;
    }

    public Carga buscarOuFalhar(String cargaCodigo) {
        return cargaRepository.findByCodigo(cargaCodigo)
                .orElseThrow(() -> new CargaNaoEncontradaException(cargaCodigo));
    }

    public void copyToDomainObject(CargaDto cargaDto, Carga carga) {
        modelMapper.map(cargaDto, carga);
    }

    public boolean temNoBanco(String codigoCarga) {
        return cargaRepository.findByCodigo(codigoCarga).isPresent();
    }

    //    @CircuitBreaker(name = "carga", fallbackMethod = "fallbackMethod")
    public List<Carga> retornaCargasOk(List<Carga> cargas) {
        List<Carga> cargasOk = new ArrayList<>();

        cargas.forEach(item -> {
            if (temNoBanco(item.getCodigo())) {
                var carga = cargaRepository.findByCodigo(item.getCodigo()).get();
                item.setCodigo(carga.getCodigo());
                item.setTipoCarga(carga.getTipoCarga());
                item.setDescricao(carga.getDescricao());

            } else {
                if ("Conteiner".equalsIgnoreCase(item.getTipoCarga())) {
                    var carga = conteinerProxy.findConteinerByCodigo(item.getCodigo());

                    item.setTipoCarga(carga.getCodigo());
                    item.setDescricao("Numero: " + carga.getNumero());
                    item.setTipoCarga("Conteiner");
                } else if ("Veiculo".equalsIgnoreCase(item.getTipoCarga())) {
                    var carga = veiculoProxy.findVeiculoByCodigo(item.getCodigo());

                    item.setTipoCarga(carga.getCodigo());
                    item.setDescricao("Chassi: " + carga.getChassi());
                    item.setTipoCarga("Veiculo");
                } else if ("Carga Solta".equalsIgnoreCase(item.getTipoCarga())) {
                    var carga = cargaSoltaProxy.findCargaSoltaByCodigo(item.getCodigo());

                    item.setTipoCarga(carga.getCodigo());
                    item.setDescricao("Carga: " + carga.getNome());
                    item.setTipoCarga("Carga Solta");
                }
            }

            cargasOk.add(item);
            cargaRepository.save(item);
            cargaRepository.flush();


        });

        return cargasOk;
    }
}
