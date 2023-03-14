package pro.sky.telegram_bot_pets_shelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegram_bot_pets_shelter.entity.Cat;
import pro.sky.telegram_bot_pets_shelter.entity.Dog;
import pro.sky.telegram_bot_pets_shelter.exception_handling.CatNotFoundException;
import pro.sky.telegram_bot_pets_shelter.exception_handling.ShelterIncorrectData;
import pro.sky.telegram_bot_pets_shelter.service.CatService;

import java.util.List;


@RestController
@RequestMapping("/cat")
@Tag(name = "CatController", description = "Взаимодействие с котиками приюта")
@Slf4j
public class CatController {

    final private CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @Operation(
            summary = "Поиск котика по id",
            description = "Позволяет найти котика по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Поиск котика по id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Cat.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ShelterIncorrectData.class)
                            )
                    )
            }
    )
    @GetMapping("{id}")
    public ResponseEntity<Cat> findCat(@PathVariable Long id) {
        Cat persistentCat = catService.findCat(id);
        if (persistentCat == null) {
            String message = "There is no cat with ID =" + id + " in Database";
            log.error(message);
            throw new CatNotFoundException();
        }
        return ResponseEntity.ok(persistentCat);
    }

    @Operation(
            summary = "Сохранение нового котика в базу данных",
            description = "Позволяет сохранить котика в базу данных",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Сохранение котика",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Cat.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Сохранение нового котика в базу данных",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Cat.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ShelterIncorrectData.class)
                            )

                    )
            }
    )
    @PostMapping
    public ResponseEntity<Cat> createCat(@RequestBody Cat cat) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(catService.createCat(cat));
    }

    @Operation(
            summary = "Изменение скилов котика в базе данных",
            description = "Позволяет изменить параметры котика",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Редактируемый котик",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Cat.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Изменение скилов котика в базе данных",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Cat.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ShelterIncorrectData.class)
                            )

                    )
            }
    )
    @PutMapping
    public ResponseEntity<Cat> editCat(@RequestBody Cat cat) {
        return ResponseEntity.ok(catService.editCat(cat));
    }

    @Operation(
            summary = "Удаление котика по id",
            description = "Позволяет удалить котика по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Удаление котика по id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Cat.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ShelterIncorrectData.class)
                            )
                    )
            }
    )
    @DeleteMapping("{id}")
    public ResponseEntity<Cat> deleteCat(@PathVariable Long id) {
        return ResponseEntity.ok(catService.deleteCat(id));
    }

    @Operation(
            summary = "Наличие всех котиков в приюте",
            description = "Позволяет показать всех котиков приюта",
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "Наличие всех котиков в приюте",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Dog.class)
                            )
                    )
            )
    )
    @GetMapping
    public ResponseEntity<List<Cat>> getAllCats() {
        return ResponseEntity.ok(catService.getAllCatsFree());
    }
}
