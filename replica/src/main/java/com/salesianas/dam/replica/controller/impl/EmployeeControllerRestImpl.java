package com.salesianas.dam.replica.controller.impl;

import com.salesianas.dam.replica.controller.EmployeeControllerRest;
import com.salesianas.dam.replica.utils.constant.RestConstantsUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Employee", description = "Employee rest")
@RequestMapping(value = RestConstantsUtils.API_VERSION_1 + RestConstantsUtils.RESOURCE_EMPLOYEES)
public class EmployeeControllerRestImpl implements EmployeeControllerRest {
}
