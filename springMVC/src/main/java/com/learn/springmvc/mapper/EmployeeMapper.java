package com.learn.springmvc.mapper;

import com.learn.springmvc.model.Employee;

import java.util.Collection;

public interface EmployeeMapper {

    /**
     * @param employee the employee to insert or update
     */
    void save(Employee employee);

    /**
     * @return all employees
     */
    Collection<Employee> getAll();

    /**
     * @param id the id of the employee to get
     * @return the employee with the given id
     */
    Employee getById(Integer id);

    /**
     * @param id the id of the employee to delete
     */
    void deleteById(Integer id);
}
