package com.hongshen.controller.lte;

import com.hongshen.configuration.WebMvcConfig;
import com.hongshen.entity.nomalEntity.lte.Station4g;
import com.hongshen.entity.resourceEntity.Level2Entity;
import com.hongshen.entity.resourceEntity.Level3Entity;
import com.hongshen.exception.SranExceptionEntity;
import com.hongshen.repository.lte.Station4gRepository;
import com.hongshen.service.lte.Level3Service;
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
 * Created by a7289 on 2017/3/28 0028.
 */
@RepositoryRestController
@ExposesResourceFor(Level3Entity.class)
@RequestMapping("/{userName}/lte/tac/{tacName}/node/{nodeName}/cell")
public class Level3Controller {

    @Autowired
    private Level3Service level3Service;
    @Autowired
    private Station4gRepository station4gRepository;

    public HttpEntity getUrl(){
        return null;
    }

    @RequestMapping(value = "links")
    public HttpEntity getLinks(@PathVariable @NotNull String userName, @PathVariable @NotBlank String tacName,@PathVariable @NotBlank String nodeName){
        List<Link> links = new ArrayList<>();
        Link link1 = linkTo(methodOn(Level3Controller.class).getNodesAndStationsByTacName(userName, tacName, nodeName)).withSelfRel();
        links.add(link1);
        return new HttpEntity(new Resources<>(links));
    }

    @RequestMapping(value = "/cells-stations",method = RequestMethod.GET)
    public ResponseEntity getNodesAndStationsByTacName(@PathVariable @NotBlank String userName, @PathVariable @NotBlank String tacName,@PathVariable @NotBlank String nodeName){
        try {
            List<Resource> level3Resources = level3Service.getCellsAndStationsByNodeName(userName,tacName,nodeName);

//            获得基站基础数据，用来加载地图，但是没有cell各自的状态（看前台设计再决定是否保留）
            Station4g station4g = station4gRepository.findByNodeName(nodeName);
            level3Resources.add(new Resource(station4g));

            Resources<Resource> level3HttpResources = new Resources<>(level3Resources);
            level3HttpResources.add(linkTo(methodOn(Level3Controller.class).getNodesAndStationsByTacName(userName, tacName, nodeName)).withSelfRel());
            return new ResponseEntity<>(level3HttpResources, HttpStatus.OK);
        }catch (Exception e){
            Resource<SranExceptionEntity> exceptionResource = new Resource<>(new SranExceptionEntity(e.getMessage()));
            exceptionResource.add(linkTo(Level2Controller.class,userName,tacName).withRel("errorLink"));
            return new ResponseEntity(exceptionResource, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
