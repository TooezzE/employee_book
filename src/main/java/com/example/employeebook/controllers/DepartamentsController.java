package com.example.employeebook.controllers;

import com.example.employeebook.exceptions.DepartamentNotFoundException;
import com.example.employeebook.services.DepartamentsService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/departament")
public class DepartamentsController {

    private DepartamentsService departamentsService;

    public DepartamentsController(DepartamentsService departamentsService) {
        this.departamentsService = departamentsService;
    }

    @RequestMapping(value = "/{id}/employees", method = RequestMethod.GET)
    @ResponseBody
    public String employeesInDep(@PathVariable String id){
        try{
            return departamentsService.getEmployeesInDep(Integer.parseInt(id)).toString();
        } catch (DepartamentNotFoundException e){
            return "Departament with this \"id\" does not exists";
        }
    }

    @RequestMapping(value = "/{id}/salary/sum", method = RequestMethod.GET)
    public String salarySumInDep(@PathVariable String id){
        try{
            return Integer.toString(departamentsService.getSalarySumInDep(Integer.parseInt(id)));
        } catch (DepartamentNotFoundException e){
            return "Departament with this \"id\" does not exists";
        }
    }

    @RequestMapping(value = "/{id}/salary/min", method = RequestMethod.GET)
    public String minSalaryInDep(@PathVariable String id){
        try{
            return Integer.toString(departamentsService.getMinSalaryInDep(Integer.parseInt(id)));
        } catch (DepartamentNotFoundException e){
            return "Departament with this \"id\" does not exists";
        }
    }

    @RequestMapping(value = "/{id}/salary/max", method = RequestMethod.GET)
    public String maxSalaryInDep(@PathVariable String id){
        try{
            return Integer.toString(departamentsService.getMaxSalaryInDep(Integer.parseInt(id)));
        } catch (DepartamentNotFoundException e){
            return "Departament with this \"id\" does not exists";
        }
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String employeesGroupedByDeps(){
        return departamentsService.getEmployeesGroupedByDeps().toString();
    }
}
