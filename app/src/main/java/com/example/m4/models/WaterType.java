package com.example.m4.models;

public enum WaterType
{
    Bottled("Bottled"),
    Well("Well"),
    Stream("Stream"),
    Lake("Lake"),
    Spring("Spring"),
    Other("Other");
    private String string;

    WaterType(String str) {
        this.string = str;
    }

    @Override
    public String toString()
    {
        return this.string;
    }
}
