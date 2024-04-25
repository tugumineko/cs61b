package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
    private int period;

    private int state;

    public StrangeBitwiseGenerator(int period){
        state = 0;
        this.period = period;
    }

    private double normalize(double value){
        return value*2-1;
    }

    @Override
    public double next() {
        state = (state + 1);
        int weirdState = state & (state >> 7) % period;
        return normalize((double) (weirdState % period) /period);
    }
}
