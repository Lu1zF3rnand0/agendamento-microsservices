package br.com.luiz.cargasoltaservice.domain.service;

import br.com.luiz.cargasoltaservice.domain.exception.CargaSoltaNaoEncontradoException;
import br.com.luiz.cargasoltaservice.domain.model.CargaSolta;
import br.com.luiz.cargasoltaservice.domain.repository.CargaSoltaRepository;
import br.com.luiz.cargasoltaservice.dto.CargaSoltaDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CargaSoltaService {

    private CargaSoltaRepository cargaSoltaRepository;

    private ModelMapper modelMapper;

    public List<CargaSoltaDto> findAllCargaSoltas() {
        List<CargaSoltaDto> cargaSoltaDto = new ArrayList<>();
        cargaSoltaRepository.findAll().forEach(cargaSolta -> cargaSoltaDto.add(mapCargaSoltaModelToDto(cargaSolta)));
        return cargaSoltaDto;
    }


    @Transactional
    public CargaSolta save(CargaSolta cargaSolta) {
        return cargaSoltaRepository.save(cargaSolta);
    }

    public void deleteByCodigo(String codigo) {
        CargaSolta cargaSolta = buscarOuFalhar(codigo);
        cargaSoltaRepository.deleteById(cargaSolta.getId());
    }

    public CargaSoltaDto mapCargaSoltaModelToDto(CargaSolta cargaSolta) {

        CargaSoltaDto cargaSoltaDto = new CargaSoltaDto();

        cargaSoltaDto.setCodigo(cargaSolta.getCodigo());
        cargaSoltaDto.setNome(cargaSolta.getNome());
        cargaSoltaDto.setDescricao(cargaSolta.getDescricao());
        cargaSoltaDto.setImo(cargaSolta.isImo());

        return cargaSoltaDto;
    }


    public CargaSolta mapCargaSoltaDtoToModel(CargaSoltaDto cargaSoltaDto) {

        CargaSolta cargaSolta = new CargaSolta();

        cargaSolta.setNome(cargaSoltaDto.getNome());
        cargaSolta.setDescricao(cargaSoltaDto.getDescricao());
        cargaSolta.setImo(cargaSoltaDto.isImo());

        return cargaSolta;
    }

    public CargaSolta buscarOuFalhar(String cargaSoltaCodigo) {
        return cargaSoltaRepository.findByCodigo(cargaSoltaCodigo)
                .orElseThrow(() -> new CargaSoltaNaoEncontradoException(cargaSoltaCodigo));
    }

    public void copyToDomainObject(CargaSoltaDto cargaSoltaDto, CargaSolta cargaSolta) {
        modelMapper.map(cargaSoltaDto, cargaSolta);
    }
}
