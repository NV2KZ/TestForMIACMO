package ru.nvkz.socks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nvkz.socks.dto.ResultDto;
import ru.nvkz.socks.dto.SocksDto;
import ru.nvkz.socks.servise.SocksService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/socks")
@RequiredArgsConstructor
public class SocksController {
    private final SocksService socksService;

    @GetMapping()
    public ResultDto getSocks(@RequestParam(value = "color") String color,
                              @RequestParam(value = "operation") String operation,
                              @RequestParam(value = "cottonPart") Integer cottonPart) {
        return socksService.findByColorAndCottonPart(color, operation, cottonPart);
    }

    @PostMapping("/income")
    public void incomeSocks(@Valid @RequestBody SocksDto socksDto) {
        socksService.income(socksDto);
    }

    @PostMapping("/outcome")
    public void outcomeSocks(@Valid @RequestBody SocksDto socksDto) {
        socksService.outcome(socksDto);
    }
}
