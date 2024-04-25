package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private int period;

    private double factor;

    private int state;

    public AcceleratingSawToothGenerator(int period , double factor){
        state = 0;
        this.period = period;
        this.factor = factor;
    }

    private double normalize(double value){
        return value*2-1;
    }

    @Override
    public double next() {
        state = state+1;
        if(state%period == 0) {
            period = (int) Math.round(period * factor);
            state = period;
        }
        return normalize((double) (state % period) /period);
    }
}
