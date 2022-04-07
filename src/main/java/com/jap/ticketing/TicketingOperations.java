package com.jap.ticketing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TicketingOperations {
    //This method reads the sample.csv file provided and returns a List
    
	
	public List<TicketingData> readTicketingData(String fileName)
    {
		List<TicketingData> list = new ArrayList<TicketingData>();
    	try (BufferedReader br1 = new BufferedReader(new FileReader(fileName))) {
            br1.readLine();
            String line = "";        
            while ((line = br1.readLine()) != null) {
                
            	String[] arr = line.split(",");
                arr = line.split(",");
                TicketingData s = new TicketingData();
                s.setScheduleNo(arr[0]);
                s.setRouteNo(arr[1]);
                s.setTicketFromStopId(Integer.parseInt(arr[2]));
                s.setTicketFromStopSeqNo(Integer.parseInt(arr[3]));
                s.setTicketTillStopId(Integer.parseInt(arr[4]));
                s.setTicketTillStopSeqNo(Integer.parseInt(arr[5]));
                s.setTicketDate(arr[6]);
                s.setTicketTime(arr[7]);
                s.setTotalTicketAmount(Double.parseDouble(arr[8]));
                s.setTravelledKM(Float.parseFloat(arr[9]));
                list.add(s);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
         
        return list;
    }
    //This method sorts the data based on the kms travelled by a bus (route)
    public List<TicketingData> sortByKmsTravelled(List<TicketingData> ticketingDataList)
    {   
    	Collections.sort(ticketingDataList, new Comparator<TicketingData>() {
         
			@Override
			public int compare(TicketingData o1, TicketingData o2) {
				// TODO Auto-generated method stub
				
				 if (o1.getTravelledKM()== o2.getTravelledKM())
			            return 0;
			        else if (o1.getTravelledKM() < o2.getTravelledKM())
			            return 1;
			        else
			            return -1;
			}
        });
    	
        return ticketingDataList;
    }

    //This method calculates the total revenue from ticket collection amount
    public double totalAmountCollected(List<TicketingData> ticketingDataList)
    { 
       TicketCollection total=(data)->{
    	   double totalAmount=0;
    	   for(TicketingData n: data) {
    		   totalAmount+=n.getTotalTicketAmount();
    	   }
    	   return totalAmount;
       };
       return total.amount(ticketingDataList);
    }
   
}
