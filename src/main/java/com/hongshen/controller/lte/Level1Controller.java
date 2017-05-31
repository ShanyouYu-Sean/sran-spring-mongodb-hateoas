package com.hongshen.controller.lte;

import com.hongshen.configuration.WebMvcConfig;
import com.hongshen.entity.resourceEntity.Level1Entity;
import com.hongshen.exception.SranException;
import com.hongshen.exception.SranExceptionEntity;
import com.hongshen.service.lte.Level1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by a7289 on 2017/3/23 0023.
 */
@RepositoryRestController
@ExposesResourceFor(Level1Entity.class)
@RequestMapping(value = "/{userName}/lte/tac")
public class Level1Controller {
    @Autowired
    private Level1Service level1Service;

    public HttpEntity getUrl(){
        return null;
    }

    @RequestMapping(value = "links")
    public HttpEntity getLinks(@PathVariable @NotNull String userName){
        List<Link> links = new ArrayList<>();
        Link link1 = linkTo(methodOn(Level1Controller.class).getTacsAndStationsByUserName(userName)).withSelfRel();
        links.add(link1);
        return new HttpEntity(new Resources<>(links));
    }

    @RequestMapping(value = "/tacs-stations",method = RequestMethod.GET)
    public ResponseEntity getTacsAndStationsByUserName(@PathVariable @NotNull String userName){
        try {
            List<Resource> level1Resources = level1Service.getTacsAndStationsByUserName(userName);

            Resources<Resource> level1HttpResources = new Resources<>(level1Resources);
            level1HttpResources.add(linkTo(methodOn(Level1Controller.class).getTacsAndStationsByUserName(userName)).withSelfRel());
            return new ResponseEntity(level1HttpResources, HttpStatus.OK);

        }catch (Exception e){
            Resource<SranExceptionEntity> exceptionResource = new Resource<>(new SranExceptionEntity(e.getMessage()));
            Link errorLink = new Link(linkTo(WebMvcConfig.class).toString()+"/logout","errorLink");
            exceptionResource.add(errorLink);
            return new ResponseEntity(exceptionResource, HttpStatus.SERVICE_UNAVAILABLE);
        }

    }

}
