package com.shivaya.EualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static String getAddress(Map map, Employee emp)
    {
        Address adrs = (Address)map.get(emp);
        return adrs.getAddress();
    }
    public static void main(String[] args)
    {

        Employee emp1 = new Employee(110, "Sajid Ali Khan");
        Address adrs1 = new Address(304, "adrs1",
                "Mumbai", 400069);
        Address adrs3 = new Address(123, "adrs3", "Cityville", 12345);
        Address adrs4 = new Address(789, "adrs4", "Metropolis", 56789);
        Employee emp2 = new Employee(111, "Jaspreet Singh");
        Address adrs2 = new Address(203, "adrs2", "Mumbai",
                400093);

        Map<Employee, Address> map = new HashMap<>();
//        map.put(emp1, adrs1);
//        map.put(emp2, adrs2);
        map.put( new Employee(110, "Ram Ram Bhai"),adrs1);
        map.put( new Employee(110, "Ram Ram Bhai1"),adrs2);
        map.put( new Employee(120, "GuruDevvcbb"),adrs3);
        map.put( new Employee(110, "Sajid Ali Khansdsd5656565"),adrs4);
        map.put( new Employee(110, "GuruDevvcbb"),adrs3);
        System.out.println(Test.getAddress(map, new Employee(110, "Ram Ram Bhai")));
        System.out.println(Test.getAddress(map, new Employee(111,
                "Sajid Ali Khandfsdfds")));
    }
}