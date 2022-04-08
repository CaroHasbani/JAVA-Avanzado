package com.example.demo.domain;

import java.util.Objects;

import com.example.demo.domain.enums.Operator;

public class Operation {
    private Double left;
    private Double right;
    private Operator operator;

    public Operation(Double left, Double right, Operator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public Operation() {
    }

    public Double getLeft() {
        return left;
    }

    public void setLeft(Double left) {
        this.left = left;
    }

    public Double getRight() {
        return right;
    }

    public void setRight(Double right) {
        this.right = right;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        Operation that = (Operation) o;
        return Objects.equals(left, that.left) && operator == that.operator && Objects.equals(right, that.right);
    }

    @Override
    public int hashCode(){
        return Objects.hash(left, operator, right);
    }
}