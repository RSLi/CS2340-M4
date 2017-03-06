package com.example.m4.models;

public class WaterPurityReport extends Report
{
    private WaterOverallCondition waterOverallCondition;
    private Double virusPPM;
    private Double contaminantPPM;

    public WaterOverallCondition getWaterOverallCondition()
    {
        return waterOverallCondition;
    }

    public void setWaterOverallCondition(WaterOverallCondition waterOverallCondition)
    {
        this.waterOverallCondition = waterOverallCondition;
    }

    public Double getVirusPPM()
    {
        return virusPPM;
    }

    public void setVirusPPM(Double virusPPM)
    {
        this.virusPPM = virusPPM;
    }

    public Double getContaminantPPM()
    {
        return contaminantPPM;
    }

    public void setContaminantPPM(Double contaminantPPM)
    {
        this.contaminantPPM = contaminantPPM;
    }

    @Override
    public boolean isValid()
    {
        if (waterOverallCondition != null && virusPPM != null && contaminantPPM != null) {
            return true;
        }
        return false;
    }
}
