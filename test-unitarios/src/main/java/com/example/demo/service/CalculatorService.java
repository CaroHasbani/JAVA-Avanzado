package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Operation;
import com.example.demo.domain.entity.OpResultEntity;
import com.example.demo.domain.enums.Operator;
import com.example.demo.repository.OpResultRepository;

import java.util.List;

@Service
public class CalculatorService {
    @Autowired
    private OpResultRepository operationResultRepository;

    public CalculatorService(OpResultRepository operationResultRepository){
        this.operationResultRepository = operationResultRepository;
    }

    public List<OpResultEntity> getOperations(){
        return (List<OpResultEntity>) this.operationResultRepository.findAll();
    }

    public OpResultEntity add(Double left, Double right){
        return new OpResultEntity(Operator.ADD, left, right, left + right);
    }

    public OpResultEntity difference(Double left, Double right){
        return new OpResultEntity(Operator.DIFFERENCE, left, right, left - right);
    }

    public OpResultEntity multiply(Double left, Double right){
        return new OpResultEntity(Operator.MULTIPLY, left, right, left * right);
    }

    public OpResultEntity divide(Double left, Double right){
        return new OpResultEntity(Operator.DIVIDE, left, right, left / right);
    }

    public OpResultEntity calculate(Operation operation){
        if(Operator.ADD.equals(operation.getOperator())){
            return operationResultRepository.save(this.add(operation.getLeft(), operation.getRight()));
        } else if(Operator.DIFFERENCE.equals(operation.getOperator())){
            return operationResultRepository.save(this.difference(operation.getLeft(), operation.getRight()));
        } else if(Operator.MULTIPLY.equals(operation.getOperator())){
            return operationResultRepository.save(this.multiply(operation.getLeft(), operation.getRight()));
        } else if(Operator.DIVIDE.equals(operation.getOperator())){
            return operationResultRepository.save(this.divide(operation.getLeft(), operation.getRight()));
        } else {
            return null;
        }
    }

    public String validateResult(OpResultEntity operationResult){
        Operation prevOperation = new Operation(operationResult.getLeft(), operationResult.getRight(), operationResult.getOperator());
        Double result = this.calculate(prevOperation).getResult();

        if(!operationResult.getResult().equals(result)){
            return "Wrong!";
        } else {
            return "Correct!";
        }
    }
}