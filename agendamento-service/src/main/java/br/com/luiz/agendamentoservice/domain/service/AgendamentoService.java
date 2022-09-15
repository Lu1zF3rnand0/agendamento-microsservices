package br.com.luiz.agendamentoservice.domain.service;

import br.com.luiz.agendamentoservice.domain.exception.AgendamentoNaoEncontradoException;
import br.com.luiz.agendamentoservice.domain.model.Agendamento;
import br.com.luiz.agendamentoservice.domain.model.Carga;
import br.com.luiz.agendamentoservice.domain.repository.AgendamentoRepository;
import br.com.luiz.agendamentoservice.dto.AgendamentoDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AgendamentoService {

    private AgendamentoRepository agendamentoRepository;

    private ModelMapper modelMapper;

    public List<AgendamentoDto> findAllAgendamentos() {
        List<AgendamentoDto> agendamentoDto = new ArrayList<>();
        agendamentoRepository.findAll().forEach(agendamento -> agendamentoDto.add(mapAgendamentoModelToDto(agendamento)));
        return agendamentoDto;
    }


    @Transactional
    public Agendamento save(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    public void deleteByCodigo(String codigo) {
        Agendamento agendamento = buscarOuFalhar(codigo);
        agendamentoRepository.deleteById(agendamento.getId());
    }

    public AgendamentoDto mapAgendamentoModelToDto(Agendamento agendamento) {

        AgendamentoDto agendamentoDto = new AgendamentoDto();

        agendamentoDto.setCodigo(agendamento.getCodigo());
        agendamentoDto.setNumero(agendamento.getNumero());
        agendamentoDto.setDescricao(agendamento.getDescricao());
        agendamentoDto.setStatus(agendamento.getStatus());
        agendamentoDto.setCargas(agendamento.getCargas());
        agendamentoDto.setTransportadoras(agendamento.getTransportadoras());

        return agendamentoDto;
    }


    public Agendamento mapAgendamentoDtoToModel(AgendamentoDto agendamentoDto) {

        Agendamento agendamento = new Agendamento();

        agendamento.setDescricao(agendamentoDto.getDescricao());
        agendamento.setCargas(agendamentoDto.getCargas());
        agendamento.setTransportadoras(agendamentoDto.getTransportadoras());

        return agendamento;
    }

    public Agendamento buscarOuFalhar(String agendamentoCodigo) {
        return agendamentoRepository.findByCodigo(agendamentoCodigo)
                .orElseThrow(() -> new AgendamentoNaoEncontradoException(agendamentoCodigo));
    }

    public void copyToDomainObject(AgendamentoDto agendamentoDto, Agendamento agendamento) {
        modelMapper.map(agendamentoDto, agendamento);
    }

    public void cancelaAgendamento(Agendamento agendamento) {
        agendamento.setStatus("Cancelado");
        agendamentoRepository.save(agendamento);

    }

}
