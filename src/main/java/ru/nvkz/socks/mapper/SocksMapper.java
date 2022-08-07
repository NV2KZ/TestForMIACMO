package ru.nvkz.socks.mapper;

import ru.nvkz.socks.dto.SocksDto;
import ru.nvkz.socks.entity.Socks;

public class SocksMapper {

    public static Socks mapToEntity(SocksDto socksDto) {
        Socks socks = new Socks();
        socks.setColor(socksDto.getColor());
        socks.setCottonPart(socksDto.getCottonPart());
        return socks;
    }

}
