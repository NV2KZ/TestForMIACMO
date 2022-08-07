package ru.nvkz.socks.servise;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nvkz.socks.dto.ResultDto;
import ru.nvkz.socks.dto.SocksDto;
import ru.nvkz.socks.entity.Socks;
import ru.nvkz.socks.exception.NotEnoughException;
import ru.nvkz.socks.mapper.SocksMapper;
import ru.nvkz.socks.repository.SocksRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SocksService {
    private final SocksRepository socksRepository;

    @Transactional
    public void income(SocksDto socksDto) {
        List<Socks> incomeSocks = new ArrayList<>();

        for (int i = 0; i < socksDto.getQuantity(); i++) {
            incomeSocks.add(SocksMapper.mapToEntity(socksDto));
        }

        socksRepository.saveAll(incomeSocks);
    }


    @Transactional
    public void outcome(SocksDto socksDto) {
        if (socksDto.getQuantity() > socksRepository.findWithEqualOperator(socksDto.getColor(), socksDto.getCottonPart()).size()) {
            throw new NotEnoughException("Not enough socks with this parameters");
        }
        socksRepository.deleteSocks(
                socksDto.getColor(),
                socksDto.getCottonPart(),
                socksDto.getQuantity()
        );

    }

    public ResultDto findByColorAndCottonPart(String color, String operation, Integer cottonPart) {

        long count = switch (operation) {
            case "moreThen" -> socksRepository.findWithMoreThanOperator(color, cottonPart).size();
            case "lessThen" -> socksRepository.findWithLessThanOperator(color, cottonPart).size();
            case "equal" -> socksRepository.findWithEqualOperator(color, cottonPart).size();
            default -> 0;
        };
        return new ResultDto(Long.toString(count));
    }


}
