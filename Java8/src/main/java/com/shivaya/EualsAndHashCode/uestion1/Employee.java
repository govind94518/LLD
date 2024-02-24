package com.shivaya.EualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

class Employee extends Object {
    private int empId;
    private String name;

    public Employee(int empId, String name)
    {
        this.empId = empId;
        this.name = name;
    }

    @Override public int hashCode()
    {

        return 1;
    }

    @Override public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee)obj;
        if (empId != other.empId)
            return true;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}
