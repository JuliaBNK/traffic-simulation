/*
 * Name:     Iuliia Buniak
 *
 * Course:   CS-12, Fall 2018
 *
 * Date:     12/01/18
 *
 * Filename: SimulationIB.java
 *
 * Purpose:  To simulate multiply trips from Point A to Point B and figure out
 *           which route takes the shortest amount of time  
 */

public class SimulationIB {

    public static void main (String [] arg){
        
        // DATA DECLARATION
        
        // declaration of arrays with speed on two segments of fwy  
        int [] fwySpeed1;
        int [] fwySpeed2;
        
        // declaration of arrays with speed on tree segments of city route  
        int [] citySpeed1;
        int [] citySpeed2;
        int [] citySpeed3;
        
        // declaration of arrays with time on two segments of fwy
        double [] fwyTime1;
        double [] fwyTime2;
        
        // declaration of arrays with time on tree segments of city route
        double [] cityTime1;
        double [] cityTime2;
        double [] cityTime3;
        
        // declaration of arrays with overall travel times on fwy and city route
        double [] fwyTotalTime;
        double [] cityTotalTime;
        
        // declaration and instansiation of arrays with average speed on HWY and in a city
        int [] fwySpeed = new int [2];
        int [] citySpeed = new int [2];
        
        // declaration of arrays of segment distances on highway and in a city with initialization lists
        double [] distFwy = {4.7, 5.3};
        double [] distCity = {3.4, 4.1, 2.5}; 
        
        // other data
        int nSims;                         // number of simulations; difined by user's input
        int counterCityFaster = 0;         // number of times when city route is faster
        int counterFwyFaster = 0;          // number of times when fwy route is faster
        int counterEquilTime = 0;          // number of timeS when city and frw take the same amount of time
        double totalCityDistance= 0.0;     // total distance of city route
        double totalFwyDistance = 0.0;     // total distance of fwy route
        String fastestRoute = "none";      // defining which route is faster, default value
        
        // constants
        final int MINUTES_IN_HOUR = 60;    // number of minutes in hour
        
        // USER INPUT
        
        // promting user for number of simulation 
        nSims = UtilsIB.readInt("How many simulation iterations? > ", false);
        
        // initializing the max and min speed on fwy by user's input
        fwySpeed[0] = UtilsIB.readInt("Average freeway speed (min)? > ", false);
        fwySpeed[1] = UtilsIB.readInt("Average freeway speed (max)? > ", false);

        // initializing the max and min speed on city route by user's input
        citySpeed[0] = UtilsIB.readInt("Average city speed (min)? > ", false);
        citySpeed[1] = UtilsIB.readInt("Average city speed (max)? > ", false); 
        
        // DATA INSTANTIATIONS

        // instantiating prior arrays
        fwySpeed1 = new int [nSims]; 
        fwySpeed2 = new int [nSims];
        citySpeed1 = new int [nSims];
        citySpeed2 = new int [nSims];
        citySpeed3 = new int [nSims];
        fwyTime1 = new double [nSims];
        fwyTime2 = new double [nSims]; 
        cityTime1 = new double [nSims];
        cityTime2 = new double [nSims];
        cityTime3 = new double [nSims];
        fwyTotalTime = new double [nSims];
        cityTotalTime = new double [nSims];

        System.out.println();
        
                
        // calculation of random speed, time at different segments and total time on hyw and on city route 
        for (int i=0; i < nSims; i++) {
        
            System.out.println("Performing simulation for iteration " + (i+1));
            
            // defining random speed on fwy (2 segments) in a range between user input numbers 
            fwySpeed1[i] = UtilsIB.randomInt(fwySpeed[0], fwySpeed[1]);
            fwySpeed2[i] = UtilsIB.randomInt(fwySpeed[0], fwySpeed[1]);
            
            // defining random speed on city route (3 segments) in a range between user input numbers
            citySpeed1[i] = UtilsIB.randomInt(citySpeed[0], citySpeed[1]);
            citySpeed2[i] = UtilsIB.randomInt(citySpeed[0], citySpeed[1]);
            citySpeed3[i] = UtilsIB.randomInt(citySpeed[0], citySpeed[1]);
            
            // calculating time spent on 2 different segments of freeway
            fwyTime1[i] = (distFwy[0] / fwySpeed1[i]) * MINUTES_IN_HOUR; 
            fwyTime2[i] = (distFwy[1] / fwySpeed2[i]) * MINUTES_IN_HOUR;
            
            // calculating time spent on 3 different segments of city route
            cityTime1[i] = (distCity[0] / citySpeed1[i])* MINUTES_IN_HOUR;
            cityTime2[i] = (distCity[1] / citySpeed2[i])* MINUTES_IN_HOUR;
            cityTime3[i] = (distCity[2] / citySpeed3[i])* MINUTES_IN_HOUR;
            
            // total time spent on fwy and on city route
            fwyTotalTime[i] = fwyTime1[i] + fwyTime2[i];
            cityTotalTime[i] =  cityTime1[i] + cityTime2[i] + cityTime3[i];    
        }
        
        
        // Output header  
        System.out.println();
        System.out.println("===================================================="); 
        System.out.println("TRAFFIC SIMULATION FOR " + nSims + " ITERATIONS"); 
        System.out.println("====================================================");
        System.out.println();
        
        System.out.println("LEGEND: s=speed {MPH}, t=time, T=total time {min} " + 
                            "// f=freeway, c=city // #=iteration or segment");
        System.out.println();
        
        System.out.printf("%4s%4s%6s%4s%6s%4s%6s%10s%6s%4s%6s%12s%7s%17s", 
                           "###","sc1","tc1", "sc2", "tc2", "sc3","tc3", 
                           "sf1", "tf1", "sf2", "tf2", "Tcity", "Tfwy", "fastest route\n"); 
        
        System.out.printf("%4s%4s%6s%4s%6s%4s%6s%10s%6s%4s%6s%12s%7s%17s",
                          "===", "===", "=====", "===", "=====", "===", "=====",
                          "===", "=====", "===", "=====", "=====", "======","==============\n");
        
        // printing out the results of simulations
        
        for (int i=0; i < nSims; i++) {
            
            // defining if fwy is faster
            // counting the number of times when fwy is faster
            if (fwyTotalTime[i] < cityTotalTime[i]) {
                fastestRoute = "freeway faster";
                counterFwyFaster += 1;
            }
            // defining if city route is faster
            // counting the number of times when city route is faster
            else if (fwyTotalTime[i] > cityTotalTime[i]) {
                fastestRoute = "city faster";
                counterCityFaster += 1;
            }
            // defining if fwy and city route takes the same time
            // counting the number of times when city route and fwy takes the same time
            else {
                fastestRoute = "the same time";
                counterEquilTime += 1;
            } 
        
            // printing speed and time on each of fwy and city segments,
            // total time on fwy and city route, and defining the fastest route
            System.out.printf("%4d%4d%6.2f%4d%6.2f%4d%6.2f%10d%6.2f%4d%6.2f%12.2f%7.2f%17s",
                              (i+1), citySpeed1[i], cityTime1[i],citySpeed2[i], cityTime2[i],  
                              citySpeed3[i],cityTime3[i], fwySpeed1[i],fwyTime1[i], fwySpeed2[i],
                              fwyTime2[i], cityTotalTime[i],fwyTotalTime[i], fastestRoute); 
            System.out.println();           
        } 
        
        System.out.println();
        
        //printing the array with city distance segments and calculating the total distance
        System.out.printf("%30s", "city travel segments (miles): ");
        
        for (int i= 0; i < distCity.length; i++) { 
            System.out.printf("%4.1f ", distCity[i]);
            totalCityDistance += distCity[i];
        }
        
        
        //printing the array with freeway distance segments and calculating the total distance
        System.out.printf("\n%30s", "fwy travel segments (miles): ");
        
        for (int i= 0; i < distFwy.length; i++) { 
            System.out.printf("%4.1f ", distFwy[i]);
            totalFwyDistance += distFwy[i];
           
        }
        
        System.out.println();
        System.out.println();     

        // printing out the summary 
        System.out.println("================================");
        System.out.println("OVERALL SUMMARY OF RESULTS");
        System.out.println("================================");
        System.out.println();
        
        // printing out total distance on fwy and city route and range of speeds on fwy and in a city
        System.out.println(nSims + " trips taken");
        System.out.printf("%9.1f%12s speed range %2d-%2d MPH\n", 
                          totalCityDistance, "city miles,", citySpeed[0], citySpeed[1]);
        System.out.printf("%9.1f%12s speed range %2d-%2d MPH\n", 
                          totalFwyDistance,"fwy miles,", fwySpeed[0], fwySpeed[1]);
        System.out.println();
        
        // summary how many times and % each route is faster 
        System.out.printf("%17s%15d/%d times (%.2f%%)\n", "city route faster", 
                          counterCityFaster, nSims, (double)counterCityFaster / nSims * 100);
        System.out.printf("%17s%15d/%d times (%.2f%%)\n", "fwy route faster",
                          counterFwyFaster, nSims, (double)counterFwyFaster / nSims * 100);
        System.out.printf("%17s%15d/%d times (%.2f%%)\n", "a toss-up", counterEquilTime, 
                           nSims, (double)counterEquilTime / nSims * 100);
        
        
    } // end main
    
} // end class