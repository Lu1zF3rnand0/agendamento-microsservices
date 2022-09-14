package br.com.luiz.veiculoservice.domain.service;

import br.com.luiz.veiculoservice.domain.exception.VeiculoNaoEncontradoException;
import br.com.luiz.veiculoservice.domain.model.Veiculo;
import br.com.luiz.veiculoservice.domain.repository.VeiculoRepository;
import br.com.luiz.veiculoservice.dto.VeiculoDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class VeiculoService {

    private VeiculoRepository veiculoRepository;

    private ModelMapper modelMapper;

    public List<VeiculoDto> findAllVeiculos() {
        List<VeiculoDto> veiculoDto = new ArrayList<>();
        veiculoRepository.findAll().forEach(veiculo -> veiculoDto.add(mapVeiculoModelToDto(veiculo)));
        return veiculoDto;
    }


    @Transactional
    public Veiculo save(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public void deleteByCodigo(String codigo) {
        Veiculo veiculo = buscarOuFalhar(codigo);
        veiculoRepository.deleteById(veiculo.getId());
    }

    public VeiculoDto mapVeiculoModelToDto(Veiculo veiculo) {

        VeiculoDto veiculoDto = new VeiculoDto();

        veiculoDto.setCodigo(veiculo.getCodigo());
        veiculoDto.setChassi(veiculo.getChassi());
        veiculoDto.setModelo(veiculo.getModelo());
        veiculoDto.setMontadora(veiculo.getMontadora());

        return veiculoDto;
    }


    public Veiculo mapVeiculoDtoToModel(VeiculoDto veiculoDto) {

        Veiculo veiculo = new Veiculo();

        veiculo.setChassi(veiculoDto.getChassi());
        veiculo.setModelo(veiculoDto.getModelo());
        veiculo.setMontadora(veiculoDto.getMontadora());

        return veiculo;
    }

    public Veiculo buscarOuFalhar(String veiculoCodigo) {
        return veiculoRepository.findByCodigo(veiculoCodigo)
                .orElseThrow(() -> new VeiculoNaoEncontradoException(veiculoCodigo));
    }

    public void copyToDomainObject(VeiculoDto veiculoDto, Veiculo veiculo) {
        modelMapper.map(veiculoDto, veiculo);
    }
}
