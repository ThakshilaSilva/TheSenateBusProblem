package TheSenateBus;

public class Rider extends Thread {

    @Override
    public void run(){

        enterToWaitingArea();
        Property.ridersCount.incrementAndGet();
        steppedInToBoardingArea();
        waitForBus();
        boardBus();

    }

    //riders to entering to the waiting area
    private void enterToWaitingArea(){

        try{
            Property.getWaitingAreaEntrance().acquire();
            Property.getLateArrivals().acquire();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //riders eligible to enter to the boarding area
    private void steppedInToBoardingArea(){

        Property.getLateArrivals().release();
        System.out.printf("Rider-%d arrived, ", this.getId());

    }

    //riders waiting until bus arriving
    private void waitForBus(){

        try{
            Property.getBusArrival().acquire();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //riders boarding to the bus
    private void boardBus(){

        Property.getWaitingAreaEntrance().release();

        if (Property.ridersCount.decrementAndGet() == 0){
            notifyBusToDepart();
            System.out.println(" ");
            System.out.printf("Last Rider-%d boarded\n" , this.getId());
        }else {
            System.out.printf("Rider-%d boarded, ", this.getId());
            Property.getBusArrival().release();   //notify arrival of the bus to next rider
        }

    }

    private void notifyBusToDepart(){
        Property.getBusDeparture().release();
    }

}
