package lab14;

import edu.princeton.cs.algs4.StdAudio;
import lab14lib.Generator;

public class SawToothGenerator implements Generator {
    private double frequency;

    private int state;

    public SawToothGenerator(double frequency){
        state = 0;
        this.frequency = frequency;
    }

    private double normalize(double value){
        return value*2-1;
    }

    @Override
    public double next() {
        state = (state + 1);
        double period = frequency;
        return normalize((state%period)/period);
    }
}
