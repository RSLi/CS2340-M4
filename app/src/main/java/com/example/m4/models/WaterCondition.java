package com.example.m4.models;

/**
 * This is the condition for water source report
 * (note that water purity's overall condition is another class)
 */
public enum WaterCondition
{
    Waste("Waste"),
    TreatableClear("Treatable-Clear"),
    TreatableMuddy("Treatable-Muddy"),
    Potable("Potable");

    private final String string;

    WaterCondition(String str) {
        this.string = str;
    }

    @Override
    public String toString()
    {
        return this.string;
    }
}
