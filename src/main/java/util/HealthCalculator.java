package util;

public class HealthCalculator {

    private HealthCalculator(){};

    //weight is in KG and height is in CM
    public static float bmiCalculator(float weight, float height){
        if (height > 0 || weight > 0){
            return (weight / height / height) * 10000;
        }
        return 0;
    }
}
