package hw2;

import edu.princeton.cs.algs4.StdStats;

import java.util.Random;

import static java.lang.Math.sqrt;

public class PercolationStats {

    private Random RANDOM = new Random();

    private int times;

    private double expectation;
    private double variance;



    public PercolationStats(int N,int T,PercolationFactory pf){

        if(N<=0)throw new IllegalArgumentException("N should be positive!");
        if(T<=0)throw new IllegalArgumentException("T should be positive!");

        Percolation percolation = pf.make(N);
        times = T;
        double sum = 0;
        double sumSquare = 0;
        double[] threshold = new double[T+1];
        for(int i=1;i<=T;i++){
            double number = 0;
            while(!percolation.percolates())
            {
                int row = RANDOM.nextInt(N);
                int col = RANDOM.nextInt(N);
                if(percolation.isOpen(row,col))
                    continue;
                percolation.open(row,col);
                number++;
            }
            threshold[i] = number/(double)(N*N);
            sum+=threshold[i];
        }
        expectation = sum/((double) T);
        for(int i=1;i<=T;i++){
            double difference = (threshold[i]-expectation);
            sumSquare+=(difference*difference);
        }
        variance = sumSquare/(double)(T-1);
    }

    public double mean(){
        return expectation;
    }

    public double stddev(){
        return variance;
    }

    public double confidenceLow(){
        return expectation - 1.96*sqrt(variance/times);
    }

    public double confidenceHigh(){
        return expectation + 1.96*sqrt(variance/times);
    }

}
