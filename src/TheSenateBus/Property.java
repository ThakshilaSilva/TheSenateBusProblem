package TheSenateBus;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Property {

    public static final int ARRIVAL_BUS_MEAN = 20 * 60 * 1000;                                //inter-arrival mean time of buses
    public static final int ARRIVAL_RIDER_MEAN = 30 * 1000;                                  //inter-arrival mean time of riders
    public static final int BUS_CAPACITY = 50;                                               //maximum capacity of the bus

    public static final AtomicInteger ridersCount = new AtomicInteger();

    private static final Semaphore waitingAreaEntrance = new Semaphore(BUS_CAPACITY);      //semaphore used for riders to enter the waiting area
    private static final Semaphore busArrival = new Semaphore(0);                       //semaphore used for riders to get into bus
    private static final Semaphore busDeparture = new Semaphore(0);                     //semaphore used for bus to depart
    private static final Semaphore lateArrivals = new Semaphore(1);                     //semaphore used to control late arrivals entering to the boarding area

    public static Semaphore getWaitingAreaEntrance() {
        return waitingAreaEntrance;
    }

    public static Semaphore getBusArrival() {
        return busArrival;
    }

    public static Semaphore getBusDeparture() {
        return busDeparture;
    }

    public static Semaphore getLateArrivals() {
        return lateArrivals;
    }
}
