package ru.nvkz.socks.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
@Builder
public class SocksDto {
    private final Long id;
    private final String color;
    private final Integer cottonPart;
    @Min(1)
    private final Integer quantity;
}
