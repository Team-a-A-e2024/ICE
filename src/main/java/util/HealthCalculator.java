package util;

public class HealthCalculator {

    private HealthCalculator(){};

    //calculates a persons bmi from their weight and height
    //weight is in KG and height is in CM
    //calculates bmi
    public static float bmiCalculator(float weight, float height){
        if (height > 0 && weight > 0){
            return (weight / height / height) * 10000;
        }
        return 0;
    }
}