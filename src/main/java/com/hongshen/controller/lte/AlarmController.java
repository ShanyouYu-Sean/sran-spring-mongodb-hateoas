package com.hongshen.controller.lte;

import com.hongshen.entity.nomalEntity.lte.Station4g;
import com.hongshen.entity.resourceEntity.AlarmEntity;
import com.hongshen.entity.resourceEntity.Level3Entity;
import com.hongshen.exception.SranError;
import com.hongshen.exception.SranExceptionEntity;
import com.hongshen.service.lte.AlarmService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotNull;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by a7289 on 2017/3/29 0029.
 */
@RepositoryRestController
@ExposesResourceFor(AlarmEntity.class)
@RequestMapping("/{userName}/lte/tac/{tacName}/node/{nodeName}/alarm")
public class AlarmController {
    @Autowired
    private AlarmService alarmService;
    @RequestMapping(value = "/nodes-alarms")
    public ResponseEntity getNodeAlarmsByNodeName(@PathVariable @NotNull String userName, @PathVariable @NotBlank String tacName, @PathVariable @NotBlank String nodeName){
        try {
            List<Resource> alarmResources = alarmService.getNodeAlarmsByNodeName(userName,tacName,nodeName);

            Resources<Resource> alarmHttpResources = new Resources<>(alarmResources);
            alarmHttpResources.add(linkTo(methodOn(AlarmController.class).getNodeAlarmsByNodeName(userName, tacName, nodeName)).withSelfRel());
            return new ResponseEntity<>(alarmHttpResources, HttpStatus.OK);
        }catch (Exception e){
            Resource<SranExceptionEntity> exceptionResource = new Resource<>(new SranExceptionEntity(e.getMessage()));
            exceptionResource.add(linkTo(Level3Controller.class,userName,tacName,nodeName).withRel("errorLink"));
            if (e.getMessage().equals(SranError.NO_ALARM_FOUND_UNDER_THIS_NODE)){
                return new ResponseEntity(exceptionResource, HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity(exceptionResource, HttpStatus.SERVICE_UNAVAILABLE);
            }
        }
    }
}
