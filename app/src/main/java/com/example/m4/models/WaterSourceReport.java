package com.example.m4.models;

public class WaterSourceReport extends Report
{
    private WaterCondition waterCondition;
    private WaterType waterType;

    /**
     * Get WaterCondition object
     * @return The WaterCondition object representing the waterCondition of the reported site
     */
    public WaterCondition getWaterCondition()
    {
        return waterCondition;
    }

    /**
     * @param waterCondition The waterCondition that needs to be recorded in the report
     */
    public void setWaterCondition(WaterCondition waterCondition)
    {
        this.waterCondition = waterCondition;
    }

    /**
     * get WaterType object
     * @return The water type reported
     */
    public WaterType getWaterType()
    {
        return waterType;
    }

    /**
     * Set water type reported
     * @param waterType The water type that needs to be reported
     */
    public void setWaterType(WaterType waterType)
    {
        this.waterType = waterType;
    }

    @Override
    public boolean isValid()
    {
        return waterCondition != null && waterType != null;
    }

    @Override
    public String getType() {
        return "Water Source Report";
    }
}
