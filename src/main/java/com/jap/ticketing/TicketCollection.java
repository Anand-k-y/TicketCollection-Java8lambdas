package com.jap.ticketing;

import java.util.List;

public interface TicketCollection {
   // define the abstract method for calculating total collection amount
       public double amount(List<TicketingData> data);
}
