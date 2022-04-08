package com.example.demo.domain;

import java.util.Objects;

import com.example.demo.domain.enums.Operator;

public class OperationResult {
    private Double left;
    private Operator operator;
    private Double right;
    private Double result;

    public OperationResult() {
    }

    public OperationResult(Double left, Operator operator, Double right, Double result) {
        this.left = left;
        this.operator = operator;
        this.right = right;
        this.result = result;
    }

    public Double getLeft() {
        return left;
    }

    public void setLeft(Double left) {
        this.left = left;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Double getRight() {
        return right;
    }

    public void setRight(Double right) {
        this.right = right;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "OperationResult{" +
                "left=" + left +
                ", operator=" + operator +
                ", right=" + right +
                ", result=" + result +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        OperationResult that = (OperationResult) o;
        return Objects.equals(left, that.left) && operator == that.operator && Objects.equals(right, that.right) && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode(){
        return Objects.hash(left, operator, right, result);
    }
}