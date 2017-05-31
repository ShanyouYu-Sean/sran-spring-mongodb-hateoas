package com.hongshen.controller.lte;

import com.hongshen.configuration.WebMvcConfig;
import com.hongshen.entity.resourceEntity.Level2Entity;
import com.hongshen.exception.SranException;
import com.hongshen.exception.SranExceptionEntity;
import com.hongshen.service.lte.Level2Service;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by a7289 on 2017/3/23 0023.
 */
@RepositoryRestController
@ExposesResourceFor(Level2Entity.class)
@RequestMapping("/{userName}/lte/tac/{tacName}/node")
public class Level2Controller {

    @Autowired
    private Level2Service level2Service;

    public HttpEntity getUrl(){
        return null;
    }

    @RequestMapping(value = "links")
    public HttpEntity getLinks(@PathVariable @NotNull String userName,@PathVariable @NotBlank String tacName){
        List<Link> links = new ArrayList<>();
        Link link1 = linkTo(methodOn(Level2Controller.class).getNodesAndStationsByTacName(userName,tacName)).withSelfRel();
        links.add(link1);
        return new HttpEntity(new Resources<>(links));
    }

    @RequestMapping(value = "/nodes-stations",method = RequestMethod.GET)
    public ResponseEntity getNodesAndStationsByTacName(@PathVariable @NotBlank String userName,@PathVariable @NotBlank String tacName){
        try {
            List<Resource> level2Resources = level2Service.getNodesAndStationsByTacName(userName,tacName);

            Resources<Resource> level2HttpResources = new Resources<>(level2Resources);
            level2HttpResources.add(linkTo(methodOn(Level2Controller.class).getNodesAndStationsByTacName(userName,tacName)).withSelfRel());
            return new ResponseEntity<>(level2HttpResources, HttpStatus.OK);

        }catch (Exception e){
            Resource<SranExceptionEntity> exceptionResource = new Resource<>(new SranExceptionEntity(e.getMessage()));
            exceptionResource.add(linkTo(Level2Controller.class,userName,tacName).withRel("errorLink"));
            return new ResponseEntity(exceptionResource, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
