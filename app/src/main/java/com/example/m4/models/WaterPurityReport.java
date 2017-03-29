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

    /**
     * Set the WaterOverallCondition of the report to the desired condition object
     * @param waterOverallCondition the condition of the report to be submitted
     */
    public void setWaterOverallCondition(WaterOverallCondition waterOverallCondition)
    {
        this.waterOverallCondition = waterOverallCondition;
    }

    /**
     * @return the Virus density in ppm
     */
    public Double getVirusPPM()
    {
        return virusPPM;
    }

    /**
     * @param virusPPM The virus ppm of the report
     */
    public void setVirusPPM(Double virusPPM)
    {
        this.virusPPM = virusPPM;
    }

    /**
     *
     * @return Contaminant index in ppm
     */
    public Double getContaminantPPM()
    {
        return contaminantPPM;
    }

    /**
     *
     * @param contaminantPPM The contaminant value in ppm that wants be be recorded
     */
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
