package es.soee.demo.interfaces.adapter;

import es.soee.demo.interfaces.model.UserResponse;
import es.soee.demo.interfaces.rest.ManageUserRestController;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Gloria R. Leyva Jerez
 */
@Component
public class UserResourceAssembler implements RepresentationModelAssembler<UserResponse, RepresentationModel<UserResponse>> {


    @Override
    public RepresentationModel<UserResponse> toModel(UserResponse entity) {
        entity.add(linkTo(methodOn(ManageUserRestController.class).getUser(String.valueOf(entity.getId()))).withSelfRel());
        return entity;
    }
}
