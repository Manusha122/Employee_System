package com.example.employeems.service;

import com.example.employeems.dto.EmployeeDTO;
import com.example.employeems.entity.Employee;
import com.example.employeems.repo.EmployeeRepo;
import com.example.employeems.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

public class EmployeeService {
    @Autowired
    private EmployeeRepo EmployeeRepo;

    @Autowired
    private ModelMapper modelMapper;
    public String saveEmployee(EmployeeDTO employeeDTO){
        if (EmployeeRepo.existsById(employeeDTO.getEmpID ())){
           return VarList.RSP_DUPLICATED;

        }else {
            EmployeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateEmployee(EmployeeDTO employeeDTO){
        if (EmployeeRepo.existsById(employeeDTO.getEmpID())){
            EmployeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            return VarList.RSP_SUCCESS;

        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
    public List<EmployeeDTO> getAllEmployee(){
        List<Employee> employeeList = EmployeeRepo.findAll();
        return modelMapper.map(employeeList,new TypeToken<ArrayList<EmployeeDTO>>(){
        }.getType());
    }
    public EmployeeDTO searchEmployee(int empID){
        if (EmployeeRepo.existsById(empID)){
            Employee employee =EmployeeRepo.findById(empID).orElse(null);
            return modelMapper.map(employee,EmployeeDTO.class);
        }else {
            return null;
        }
    }
    public String deleteEmployee(int empID){
        if (EmployeeRepo.existsById(empID)){
            EmployeeRepo.deleteById(empID);
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}

