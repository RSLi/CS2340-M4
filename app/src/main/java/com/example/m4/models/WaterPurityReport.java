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
     * Get virus density of water reported
     * @return the Virus density in ppm
     */
    public Double getVirusPPM()
    {
        return virusPPM;
    }

    /**
     * Set virus density reported
     * @param virusPPM The virus ppm of the report
     */
    public void setVirusPPM(Double virusPPM)
    {
        this.virusPPM = virusPPM;
    }

    /**
     * Get contamination condition
     * @return Contaminant index in ppm
     */
    public Double getContaminantPPM()
    {
        return contaminantPPM;
    }

    /**
     * Set contamination condition
     * @param contaminantPPM The contaminant value in ppm that wants be be recorded
     */
    public void setContaminantPPM(Double contaminantPPM)
    {
        this.contaminantPPM = contaminantPPM;
    }

    @Override
    public boolean isValid()
    {
        return waterOverallCondition != null && virusPPM != null && contaminantPPM != null;
    }

    @Override
    public String getType() {
        return "Water Purity Report";
    }
}
