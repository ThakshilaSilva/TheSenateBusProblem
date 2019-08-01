package TheSenateBus;

public class Bus extends Thread {

    @Override
    public void run(){
        busArrive();

        if(Property.ridersCount.get() > 0){                                 //checking whether riders are there to get in to the bus
            busParked();
            waitForAllRiders();
        }

        depart();
    }

   private void busArrive(){

        try{
            Property.getLateArrivals().acquire();                           //control late comers boarding
            System.out.println(" ");
            System.out.printf("\nBus-%d arriving\n", this.getId());
        } catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    //notify riders to board
    private void busParked(){
        Property.getBusArrival().release();
        System.out.println(" ");
        System.out.printf("Bus-%d arrived and late comers are waiting...\n", this.getId());
    }

    //departing the bus
    private void depart(){
        Property.getLateArrivals().release();                               //releasing late comers to enter to the next bus's boarding area
        System.out.println(" ");
        System.out.printf("\nBus-%d is departed\n", this.getId());
        System.out.println(" ");
    }

    //wait until all riders are get in
    private void waitForAllRiders(){
        try {
            Property.getBusDeparture().acquire();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
