package com.example.m4.models;

public enum WaterOverallCondition
{
    Safe("Safe"),
    Treatable("Treatable"),
    Unsafe("Unsafe");

    private String string;

    WaterOverallCondition(String str) {
        this.string = str;
    }

    @Override
    public String toString()
    {
        return this.string;
    }
}
