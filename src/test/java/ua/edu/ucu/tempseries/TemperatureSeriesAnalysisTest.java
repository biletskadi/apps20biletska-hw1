package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {
    private TemperatureSeriesAnalysis emptySeries;
    private TemperatureSeriesAnalysis simpleSeries;
    private TemperatureSeriesAnalysis longSeries;
    private TemperatureSeriesAnalysis sameElementsSeries;

    @Before
    public void setUp() {
        sameElementsSeries = new TemperatureSeriesAnalysis(new double[]{1.0, 1.0, 1.0, 1.0, 1.0});
        emptySeries = new TemperatureSeriesAnalysis();
        simpleSeries = new TemperatureSeriesAnalysis(new double[]{1.0, 2.0, 3.0, -2.0});
        longSeries = new TemperatureSeriesAnalysis(new double[]{2.0, 5.0, -1.0, 1.0, 5.0, -10.0});
    }

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }
    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }

    @Test
    public void testTempSummaryEqualsNotEqual() {
        TempSummaryStatistics tempOne = new TempSummaryStatistics(0, 0, 0, 0);
        TempSummaryStatistics tempTwo = new TempSummaryStatistics(1, 1, 1, 1);
        assertNotEquals(tempOne, tempTwo);
    }

    @Test
    public void testTempSummaryEqualsAnotherClass() {
        boolean expResult = false;
        boolean actualResult = simpleSeries.summaryStatistics().equals(new String("2"));
        assertEquals(expResult, actualResult);
    }

    @Test(expected = InputMismatchException.class)
    public void testAddTemperaturesVeryLowTemps() {
        simpleSeries.addTemps(-275);
    }

    @Test
    public void testAddTemperaturesSimpleSeries() {
        int expResult = 7;
        int actualResult = simpleSeries.addTemps(1.0, 0.0, 2.0);

        assertEquals(expResult, actualResult);
    }

    @Test
    public void testGreaterThanEmptyArray() {
        double[] expResult = {};
        double[] actualResult = emptySeries.findTempsGreaterThen(1.0);

        assertArrayEquals(expResult, actualResult, 0.000001);
    }

    @Test
    public void testGreaterThanSameElements() {
        double[] expResult = {};
        double[] actualResult = sameElementsSeries.findTempsGreaterThen(1.0);

        assertArrayEquals(expResult, actualResult, 0.0001);
    }

    @Test
    public void testLessThanEmptyArray() {
        double[] expResult = {};
        double[] actualResult = emptySeries.findTempsLessThen(1.0);

        assertArrayEquals(expResult, actualResult, 0.000001);
    }

    @Test
    public void testLessThanSameElements() {
        double[] expResult = {};
        double[] actualResult = sameElementsSeries.findTempsLessThen(1.0);

        assertArrayEquals(expResult, actualResult, 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeviationEmptySeries() {
        emptySeries.deviation();
    }

    @Test
    public void testDeviationSameElementsArray() {
        double expResult = 0;
        double actualResult = sameElementsSeries.deviation();

        assertEquals(expResult, actualResult, 0.000001);
    }

    @Test
    public void testDeviationSimpleArray() {
        double expResult = 3.5;
        double actualResult = simpleSeries.deviation();

        assertEquals(expResult, actualResult, 0.0001);
    }

    @Test
    public void testClosestToValueOneClosestElement() {
        double expResult = 3.0;
        double actualResult = simpleSeries.findTempClosestToValue(10.0);

        assertEquals(expResult, actualResult, 0.0);
    }

    @Test
    public void testClosestToValueTwoOppositeElements() {
        double expResult = 5.0;
        double actualResult = longSeries.findTempClosestToValue(3.5);

        assertEquals(expResult, actualResult, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testClosestToValueEmptyArray() {
        emptySeries.findTempClosestToValue(1.0);
    }

    @Test
    public void testClosestToZeroOeClosestElement() {
        double expResult = 1.0;
        double actualResult = simpleSeries.findTempClosestToZero();
        assertEquals(expResult, actualResult, 0.0);
    }

    @Test
    public void testClosestToZeroTwoOppositeElements() {
        double expResult = 1.0;
        double actualResult = longSeries.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testClosestToZeroEmptyArray() {
        double actualResult = emptySeries.findTempClosestToZero();
    }

    @Test
    public void testMinValueNormalArray() {
        double expResult = -2.0;
        double actualResult = simpleSeries.min();

        assertEquals(expResult, actualResult, 0.0);
    }

    @Test
    public void testMinValueSameElementsArray() {
        double expResult = 1.0;
        double actualResult = sameElementsSeries.min();

        assertEquals(expResult, actualResult, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinValueEmptyArray() {
        double actualResult = emptySeries.min();
    }

    @Test
    public void testMaxValueNormalArray() {
        double expResult = 3.0;
        double actualResult = simpleSeries.max();

        assertEquals(expResult, actualResult, 0.0);
    }

    @Test
    public void testMaxValueSameElementsArray() {
        double expResult = 1.0;
        double actualResult = sameElementsSeries.max();

        assertEquals(expResult, actualResult, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxValueEmptyArray() {
        emptySeries.max();
    }

    @Test
    public void testAverageSimpleArray() {
        double expValue = 1.0;
        double actualResult = simpleSeries.average();

        assertEquals(expValue, actualResult, 0.0001);
    }

    @Test
    public void testAverageLongArray() {
        double expResult = 1.0 / 3.0;
        double actualResult = longSeries.average();
        assertEquals(expResult, actualResult, 0.00001);
    }
}
