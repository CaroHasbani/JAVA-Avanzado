package com.example.demo.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Operation;
import com.example.demo.domain.entity.OpResultEntity;
import com.example.demo.service.CalculatorService;

import java.util.List;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {
    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService){
        this.calculatorService = calculatorService;
    }

    @PostMapping("/calculate")
    @ResponseStatus(HttpStatus.OK)
    public OpResultEntity calculate(@RequestBody Operation operation){
        return this.calculatorService.calculate(operation);
    }

    @PostMapping("/validateResult")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String validateResult(@RequestBody OpResultEntity operationResult){
        return this.calculatorService.validateResult(operationResult);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<OpResultEntity> getOperations(){
        return this.calculatorService.getOperations();
    }
}