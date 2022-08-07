package ru.nvkz.socks.servise;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nvkz.socks.dto.ResultDto;
import ru.nvkz.socks.dto.SocksDto;
import ru.nvkz.socks.entity.Socks;
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
        socksRepository.deleteSocks(
                socksDto.getColor(),
                socksDto.getCottonPart(),
                socksDto.getQuantity()
        );

    }

    public ResultDto findByColorAndCottonPart(String color, String operation, Integer cottonPart) {
        List<Socks> socksList = socksRepository.findAll();

        long count = switch (operation) {
            case "moreThen" -> socksList.stream()
                    .filter(socks -> socks.getColor().equals(color))
                    .filter(socks -> socks.getCottonPart() > cottonPart)
                    .count();
            case "lessThen" -> socksList.stream()
                    .filter(socks -> socks.getColor().equals(color))
                    .filter(socks -> socks.getCottonPart() < cottonPart)
                    .count();
            case "equal" -> socksList.stream()
                    .filter(socks -> socks.getColor().equals(color))
                    .filter(socks -> socks.getCottonPart().equals(cottonPart))
                    .count();
        };
        return new ResultDto(Long.toString(count));
    }


}
