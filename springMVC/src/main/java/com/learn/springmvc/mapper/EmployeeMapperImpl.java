package com.learn.springmvc.mapper;

import com.learn.springmvc.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;

@Repository
public class EmployeeMapperImpl implements EmployeeMapper {

    private static Map<Integer, Employee> employees = null;

    static {
        employees = new java.util.HashMap<>();
        employees.put(1001, new Employee(1001, "John", "abc@gmail.com", 0));
        employees.put(1002, new Employee(1002, "Mary", "ddd@gmail.com", 1));
        employees.put(1003, new Employee(1003, "Peter", "xxx@dsf.com", 0));
        employees.put(1004, new Employee(1004, "Sara", "iij@sfn.com", 1));
        employees.put(1005, new Employee(1005, "Mark", "ccsd@tt.com", 0));
    }

    private static Integer initId = 1006;

    @Override
    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }
        employees.put(employee.getId(), employee);
    }

    @Override
    public Collection<Employee> getAll() {
        return employees.values();
    }

    @Override
    public Employee getById(Integer id) {
        return employees.get(id);
    }

    @Override
    public void deleteById(Integer id) {
        employees.remove(id);
    }
}
