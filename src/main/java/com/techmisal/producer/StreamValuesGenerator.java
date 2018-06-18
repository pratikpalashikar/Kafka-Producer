package com.techmisal.producer;

import com.techmisal.StreamGenerator;

import java.util.Random;

public class StreamValuesGenerator {

    static long currUser = 1;

    static String[] names = {"Kira","Naruto","Sakura", "Saitama", "L", "Jiraya", "Tsunade", "Shikamaru", "Tim", "Bobby"};
    static String[] sex = {"M","F"};

    public static StreamGenerator getNext() {
        StreamGenerator eventGenerator = new StreamGenerator();
        int rollNo =(int) currUser % 256;
        Random r = new Random();
        eventGenerator.setName(names[r.nextInt(names.length)]);
        eventGenerator.setId("1"+rollNo);
        eventGenerator.setSex(sex[r.nextInt(sex.length)]);
        eventGenerator.setAge("12");
        eventGenerator.setRollNo(Long.parseLong(""+rollNo));
        currUser += 1;
        return eventGenerator;
    }


}
