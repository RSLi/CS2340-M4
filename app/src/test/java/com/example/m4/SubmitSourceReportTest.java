package com.example.m4;

//import com.example.m4.controllers.CreateWaterSourceReportActivity;
import com.example.m4.models.Models;
import com.example.m4.models.Report;
import com.example.m4.models.WaterCondition;
import com.example.m4.models.WaterSourceReport;
import com.example.m4.models.WaterType;


import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by theresaming on 4/9/17.
 */
public class SubmitSourceReportTest {
    private static final int TIMEOUT = 200;

    /**
     * Test for creating a valid source report
     */
    @Test (timeout = TIMEOUT)
    public void submitReportTest() {
        WaterSourceReport validReport = new WaterSourceReport();
        validReport.setLocation("Atlanta");
        validReport.setWaterType(WaterType.Bottled);
        validReport.setWaterCondition(WaterCondition.Potable);
        assertTrue(validReport.isValid());
    }

    /**
     * Test for creating a nonvalid source report
     */
    @Test (timeout = TIMEOUT)
    public void submitInvalidReportTest() {
        Report invalidReport = new Report("username", 1, new Date(), 2001, 12, null, 1, 1);
        assertFalse(invalidReport.isValid());
        invalidReport.setLocation("Atlanta");
        assertTrue(invalidReport.isValid());
    }


}
