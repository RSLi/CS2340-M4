package com.example.m4.models;

public class WaterSourceReport extends Report
{
    private WaterCondition waterCondition;
    private WaterType waterType;

    public WaterCondition getWaterCondition()
    {
        return waterCondition;
    }

    public void setWaterCondition(WaterCondition waterCondition)
    {
        this.waterCondition = waterCondition;
    }

    public WaterType getWaterType()
    {
        return waterType;
    }

    public void setWaterType(WaterType waterType)
    {
        this.waterType = waterType;
    }

    @Override
    public boolean isValid()
    {
        if (waterCondition != null && waterType != null) { return true;}
        return false;
    }
}
