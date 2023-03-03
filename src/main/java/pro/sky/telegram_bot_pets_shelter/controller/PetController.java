package pro.sky.telegram_bot_pets_shelter.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegram_bot_pets_shelter.entity.Pet;
import pro.sky.telegram_bot_pets_shelter.service.PetService;
import java.util.List;

/**
 * PetController Контроллер с CRUD операциями для сущности <b>Pet</b>
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    final private PetService service;

    public PetController(PetService service) {
        this.service = service;
    }

    @Operation(
            summary = "Добавление нового питомца",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Добавлен новый питомец с параметрами",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Pet.class)
                            )
                    )
            },
            tags = "Работа с питомцами",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Параметры нового питомца",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Pet.class)
                    )
            )
    )

    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
        return ResponseEntity.ok(service.createPet(pet));
    }

    @Operation(
            summary = "Поиск питомца по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найден питомец с параметрами",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Pet.class)
                            )
                    )
            },
            tags = "Работа с питомцами"
    )

    @GetMapping("{id}")
    public ResponseEntity<Pet> findPet(@Parameter(description = "id питомца", example = "1")@PathVariable Long id) {
        return ResponseEntity.ok(service.findPet(id));
    }

    @Operation(
            summary = "Редактирование питомца",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Обновленный питомец с параметрами",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Pet.class)
                            )
                    )
            },
            tags = "Работа с питомцами",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Новые параметры питомца",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Pet.class)
            )
            )
    )

    @PutMapping
    public ResponseEntity<Pet> editPet(@RequestBody Pet pet) {
        return ResponseEntity.ok(service.editPet(pet));
    }

    @Operation(
            summary = "Удаление питомца по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Удален питомец с параметрами",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Pet.class)
                            )
                    )
            },
            tags = "Работа с питомцами"
    )

    @DeleteMapping("{id}")
    public ResponseEntity<Pet> deletePet(@Parameter(description = "id питомца", example = "1")@PathVariable Long id) {
        return ResponseEntity.ok(service.deletePet(id));
    }

    @Operation(
            summary = "Список питомцев",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список питомцев",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Pet.class)
                            )
                    )
            },
            tags = "Работа с питомцами"
    )

    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets() {
        return ResponseEntity.ok(service.getAllPets());
    }

}
