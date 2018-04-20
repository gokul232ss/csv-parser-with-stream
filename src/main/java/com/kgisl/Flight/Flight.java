package com.kgisl.Flight;

import org.omg.CORBA.PRIVATE_MEMBER;

public class Flight {
    private String id;
    private String name;
    private String departure;
    private String arrival;
    private double amount;
    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }
    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
    * @param id the id to set
    */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the departure
     */
    public String getDeparture() {
        return departure;
    }

    /**
     * @param departure the departure to set
     */
    public void setDeparture(String departure) {
        this.departure = departure;
    }

    /**
     * @return the arrival
     */
    public String getArrival() {
        return arrival;
    }

    /**
     * @param arrival the arrival to set
     */
    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    @Override
    public String toString() {
        return "["+id + ", " + name + ", " + departure + ", " + arrival+","+amount+"]\n";
    }
    
}
