package com.hongshen.configuration;

import com.hongshen.controller.lte.Level1Controller;
import com.hongshen.controller.lte.Level2Controller;
import com.hongshen.controller.lte.Level3Controller;
import com.hongshen.entity.nomalEntity.lte.Cell4g;
import com.hongshen.entity.nomalEntity.lte.Node4g;
import com.hongshen.entity.nomalEntity.lte.Tac4g;
import com.hongshen.entity.resourceEntity.Level1Entity;
import com.hongshen.entity.resourceEntity.Level2Entity;
import com.hongshen.entity.resourceEntity.Level3Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jersey.ResourceConfigCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.HashMap;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by a7289 on 2017/3/23 0023.
 */
@Configuration
@EnableHypermediaSupport(type = {EnableHypermediaSupport.HypermediaType.HAL})
public class RestResourceConfig {

    @Autowired
    private EntityLinks entityLinks;
;

    @Bean
    public ResourceProcessor<Resource<Tac4g>> tac4gProcesser(){
        return new ResourceProcessor<Resource<Tac4g>>() {
            @Override
            public Resource<Tac4g> process(Resource<Tac4g> tac4gResource) {
                tac4gResource.add(entityLinks.linkToCollectionResource(Tac4g.class).withRel("tacs"));
                tac4gResource.add(entityLinks.linkToCollectionResource(Node4g.class).withRel("nodes"));
                tac4gResource.add(entityLinks.linkToCollectionResource(Cell4g.class).withRel("cells"));
                return tac4gResource;
            }
        };
    }

    @Bean
    public ResourceProcessor<Resource<Node4g>> node4gProcesser(){
        return new ResourceProcessor<Resource<Node4g>>() {
            @Override
            public Resource<Node4g> process(Resource<Node4g> node4gResource) {
                node4gResource.add(entityLinks.linkToCollectionResource(Tac4g.class).withRel("tacs"));
                node4gResource.add(entityLinks.linkToCollectionResource(Node4g.class).withRel("nodes"));
                node4gResource.add(entityLinks.linkToCollectionResource(Cell4g.class).withRel("cells"));
                return node4gResource;
            }
        };
    }

    @Bean
    public ResourceProcessor<Resource<Cell4g>> cell4gProcessor(){
        return new ResourceProcessor<Resource<Cell4g>>() {
            @Override
            public Resource<Cell4g> process(Resource<Cell4g> cell4gResource) {
                cell4gResource.add(entityLinks.linkToCollectionResource(Tac4g.class).withRel("tacs"));
                cell4gResource.add(entityLinks.linkToCollectionResource(Node4g.class).withRel("nodes"));
                cell4gResource.add(entityLinks.linkToCollectionResource(Cell4g.class).withRel("cells"));
                return cell4gResource;
            }
        };
    }

    @Bean
    public ResourceProcessor<Resource<Level1Entity>> level1ResourceProcesser(){
        return new ResourceProcessor<Resource<Level1Entity>>() {
            @Override
            public Resource<Level1Entity> process(Resource<Level1Entity> level1Resource) {
                level1Resource.add(linkTo(methodOn(Level1Controller.class).getUrl()).withRel("level1ViewTemplate"));
                level1Resource.add(linkTo(methodOn(Level2Controller.class).getUrl()).withRel("level2ViewTemplate"));
                level1Resource.add(linkTo(methodOn(Level3Controller.class).getUrl()).withRel("level3ViewTemplate"));
                return level1Resource;
            }
        };
    }

    @Bean
    public ResourceProcessor<Resource<Level2Entity>> level2ResourceProcesser(){
        return new ResourceProcessor<Resource<Level2Entity>>() {
            @Override
            public Resource<Level2Entity> process(Resource<Level2Entity> level2Resource) {
                level2Resource.add(linkTo(methodOn(Level1Controller.class).getUrl()).withRel("level1ViewTemplate"));
                level2Resource.add(linkTo(methodOn(Level2Controller.class).getUrl()).withRel("level2ViewTemplate"));
                level2Resource.add(linkTo(methodOn(Level3Controller.class).getUrl()).withRel("level3ViewTemplate"));
                return level2Resource;
            }
        };
    }

    @Bean
    public ResourceProcessor<Resource<Level3Entity>> level3ResourceProcesser(){
        return new ResourceProcessor<Resource<Level3Entity>>() {
            @Override
            public Resource<Level3Entity> process(Resource<Level3Entity> level3Resource) {
                level3Resource.add(linkTo(methodOn(Level1Controller.class).getUrl()).withRel("level1ViewTemplate"));
                level3Resource.add(linkTo(methodOn(Level2Controller.class).getUrl()).withRel("level2ViewTemplate"));
                level3Resource.add(linkTo(methodOn(Level3Controller.class).getUrl()).withRel("level3ViewTemplate"));
                return level3Resource;
            }
        };
    }

}
