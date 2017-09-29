package com.dewangan.jyotirmay.dao;

/**
 * Created by jyotirmay.d on 27/09/17.
 */

import com.dewangan.jyotirmay.representations.Employee;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class EmployeeDB {

    public static HashMap<Integer, Employee> employees = new HashMap<Integer, Employee>();
    static{
        employees.put(1, new Employee(1, "Lokesh", "Gupta", "India"));
        employees.put(2, new Employee(2, "John", "Gruber", "USA"));
        employees.put(3, new Employee(3, "Melcum", "Marshal", "AUS"));
        employees.put(5, new Employee(5, "Jyotirmay", "Dewangan", "AUS"));
    }

    public static List<Employee> getEmployees(){
        return new ArrayList<Employee>(employees.values());
    }

    public static Employee getEmployee(Integer id){
        return employees.get(id);
    }

    public static void updateEmployee(Integer id, Employee employee){
        employees.put(id, employee);
    }

    public static void removeEmployee(Integer id){
        employees.remove(id);
    }
}