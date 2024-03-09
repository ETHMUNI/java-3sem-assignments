package org.example.week5.JavalinWithCRUD;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import io.javalin.validation.BodyValidator;
import org.example.week5.exceptions.ApiException;

public class DogController {

    Map<UUID, DogDTO> dogs = new HashMap<>(Map.of(
            UUID.randomUUID(), new DogDTO("Buddy", "Labrador Retriever", DogDTO.Gender.MALE, 5),
            UUID.randomUUID(), new DogDTO("Luna", "Golden Retriever", DogDTO.Gender.FEMALE, 3),
            UUID.randomUUID(), new DogDTO("Max", "German Shepherd", DogDTO.Gender.MALE, 4),
            UUID.randomUUID(), new DogDTO("Bella", "Beagle", DogDTO.Gender.FEMALE, 2),
            UUID.randomUUID(), new DogDTO("Charlie", "Poodle", DogDTO.Gender.MALE, 6),
            UUID.randomUUID(), new DogDTO("Daisy", "Siberian Husky", DogDTO.Gender.FEMALE, 4)
    ));


    public Handler getAll() {
        return ctx -> {
            ctx.json(dogs);
        };
    }


    public Handler getById() {
        return ctx -> {
            ctx.pathParamAsClass("id", String.class)
                    .check(id -> id.length() == 36, "Id must be UUID with 36 characters"); // Use a path param validator
            String id = ctx.pathParam("id");
            if(!dogs.containsKey(id))
                throw new ApiException(404, "No dog with that id");
            ctx.json(dogs.get(id));
        };
    }


    public Handler create() {
        return ctx -> {
            BodyValidator<DogDTO> validator = ctx.bodyValidator(DogDTO.class);
            validator.check(person -> person.getName().length() > 0, "Name must be longer than 0");
            DogDTO dog = ctx.bodyAsClass(DogDTO.class);
            UUID id = UUID.randomUUID();
            dogs.put(id, dog);
            dog.setId(id);
            ctx.json(dog).status(HttpStatus.CREATED);
        };
    }


    public Handler update() {
        return ctx -> {
            ctx
                    .pathParamAsClass("id", String.class) // returns a validator
                    .check(id -> id.length() == 36, "Id must be UUID with 36 characters");
            UUID id = UUID.fromString(ctx.pathParam("id"));
            DogDTO person = ctx.bodyAsClass(DogDTO.class);
            this.dogs.put(id, person);
            ctx.json(person);
        };
    }


    public Handler delete() {
        return ctx -> {
            String id = ctx.pathParam("id");
            if(!dogs.containsKey(id)){
                ctx.status(404);
                ctx.attribute("msg", String.format("No dog with id: {id}", id));
                return;
            }
            DogDTO dog = this.dogs.remove(id);
            ctx.json(dog);
        };
    }
}
