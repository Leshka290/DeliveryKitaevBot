package com.tgproject.deliverykitaevbot.controller;

import com.tgproject.deliverykitaevbot.dto.RestaurantDto;
import com.tgproject.deliverykitaevbot.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Operation(summary = "Find all restaurants.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Get all Restaurant",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Collection.class)
                    )
            )
    })
    @GetMapping
    public List<RestaurantDto> getAll() {
        return restaurantService.getAll();
    }

    @Operation(summary = "Add restaurant")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Create Restaurant",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RestaurantDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Restaurant not found"
            )
    })
    @PostMapping
    public ResponseEntity<RestaurantDto> create(@Valid @RequestBody RestaurantDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.create(dto));
    }

    @Operation(summary = "Find restaurant.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Find Restaurant by id",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RestaurantDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Restaurant not found"
            )
    })
    @GetMapping("/{id}")
    public RestaurantDto read(@PathVariable Long id) {
        return restaurantService.read(id);
    }

    @Operation(summary = "Update restaurant.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Update Restaurant by id",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RestaurantDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Restaurant not found"
            )
    })
    @PutMapping("/{id}")
    public RestaurantDto update(@PathVariable Long id, @Valid @RequestBody RestaurantDto dto) {
        return restaurantService.update(id, dto);
    }

    @Operation(summary = "Delete restaurant.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Delete restaurant by id",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RestaurantDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Restaurant not found"
            )
    })
    @DeleteMapping("/{id}")
    public RestaurantDto delete(@PathVariable Long id) {
        return restaurantService.delete(id);
    }
}
