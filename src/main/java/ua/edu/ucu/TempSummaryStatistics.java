package ua.edu.ucu.tempseries;

public class TempSummaryStatistics {
    private double average_temp, deviation_temp, min_temp, max_temp;

    TempSummaryStatistics(double average_temp, double deviation_temp,
                          double min_temp, double max_temp) {
        this.average_temp = average_temp;
        this.deviation_temp = deviation_temp;
        this.min_temp = min_temp;
        this.max_temp = max_temp;
    }

    public double get_average_temp() {
        return average_temp;
    }

    public double get_deviation_temp() {
        return deviation_temp;
    }

    public double get_min_temp() {
        return min_temp;
    }

    public double get_max_temp() {
        return max_temp;
    }
}

