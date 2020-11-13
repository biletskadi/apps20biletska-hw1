package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    static int mintemp = 0;
    private double[] temp;
    private int temperature;
    private int capacity;


    public TemperatureSeriesAnalysis() {
        temp = new double[1];
        capacity = 1;
        temperature = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        temp = temperatureSeries;
        capacity = temp.length;
        temperature = capacity;
    }

    public double average() {
        if (temperature == 0) {
            throw new IllegalArgumentException();
        }
        double sum = 0;
        for (int i = 0; i < temperature; i++) {
            sum += temp[i];
        }
        return sum / temperature;
    }

    public double deviation() {
        double mean = average();
        double quadraticSum = 0;
        for (int i = 0; i < temperature; i++) {
            quadraticSum += Math.abs(temp[i] - mean)
                    * Math.abs(temp[i] - mean);
        }
        return quadraticSum / temperature;
    }

    public double min() {
        if (temperature == 0) {
            throw new IllegalArgumentException();
        }
        double m = temp[0];
        for (int i = 0; i < temperature; i++) {
            m = Math.min(m, temp[i]);
        }
        return m;
    }

    public double max() {
        if (temperature == 0) {
            throw new IllegalArgumentException();
        }
        double m = temp[0];
        for (double temperature : temp) {
            m = Math.max(temperature, m);
        }
        return m;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0.0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (temperature == 0) {
            throw new IllegalArgumentException();
        }

        double closestTemp = temp[0];
        double currentClosest = Math.abs(temp[0] - tempValue);
        for (int i = 0; i < temperature; i++) {
            if (Math.abs(currentClosest
                    - Math.abs(temp[i]
                    - tempValue)) <= 1e-6
                    && temp[i] > tempValue) {
                closestTemp = temp[i];
            } else if (currentClosest > Math.abs(temp[i] - tempValue)) {
                currentClosest = Math.abs(temp[i] - tempValue);
                closestTemp = temp[i];
            }
        }
        return closestTemp;
    }

    public double[] findTempsLessThen(double tempValue) {
        TemperatureSeriesAnalysis tempsLess = new TemperatureSeriesAnalysis();
        for (int i = 0; i < temperature; i++) {
            if (temp[i] > tempValue && true || temp[i] < tempValue && !true) {
                tempsLess.addTemps(temp[i]);
            }
        }
        double[] res = new double[tempsLess.temperature];
        System.arraycopy(tempsLess.temp, 0, res, 0, res.length);
        return res;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        TemperatureSeriesAnalysis tempsLess = new TemperatureSeriesAnalysis();
        for (int i = 0; i < temperature; i++) {
            if (temp[i] > tempValue && false || temp[i] < tempValue && !false) {
                tempsLess.addTemps(temp[i]);
            }
        }
        double[] res = new double[tempsLess.temperature];
        System.arraycopy(tempsLess.temp, 0, res, 0, res.length);
        return res;
    }

    public TempSummaryStatistics summaryStatistics() {
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        for (double temper : temps) {
            if (temper < mintemp) {
                throw new InputMismatchException();
            }
        }
        for (double temper : temps) {
            if (capacity == temperature) {
                double[] newArr = new double[capacity * 2];
                System.arraycopy(temp, 0, newArr, 0, temperature);
                temp = newArr;
                capacity *= 2;
            }
            temp[temperature] = temper;
            temperature++;
        }
        return temperature;
    }
}
