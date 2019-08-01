package TheSenateBus;

import java.util.Random;

public class Main {
    public static Random random;

    public static void main(String args[]){

        random = new Random();

        Thread dispatchBus = new Thread(() -> {
            long exp = getExponentiallyDistributedInterArrivalTime(Property.ARRIVAL_BUS_MEAN, random);
            while (true) {
                try {
                    Thread.sleep(exp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Bus().start();
            }
        });

        Thread dispatchRider = new Thread(() -> {
            long exp = getExponentiallyDistributedInterArrivalTime(Property.ARRIVAL_RIDER_MEAN, random);
            while (true) {
                try {
                    Thread.sleep(exp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Rider().start();
            }
        });

        dispatchBus.start();
        dispatchRider.start();


    }

    public static long getExponentiallyDistributedInterArrivalTime(float arrivalMeanTime, Random random){

        float lambda = 1 / arrivalMeanTime;
        return Math.round(-Math.log(1 - random.nextFloat()) / lambda);
    }

}
