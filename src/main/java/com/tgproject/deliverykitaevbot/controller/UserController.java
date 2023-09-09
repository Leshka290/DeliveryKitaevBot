package com.tgproject.deliverykitaevbot.controller;

import com.tgproject.deliverykitaevbot.dto.UserCRUDDto;
import com.tgproject.deliverykitaevbot.service.UserCRUDService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserCRUDService crudService;

    @Operation(summary = "Find all users.")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Collection of Users will be returned",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Collection.class))),
    })
    @GetMapping
    public Collection<UserCRUDDto> getAllUsers() {
        return crudService.getAllUsers();
    }

    @Operation(summary = "Find user.")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "User will be returned",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Collection.class))),
    })
    @GetMapping("/{id}")
    public UserCRUDDto getUserById(@PathVariable Long id) {
        return crudService.getUser(id);
    }

    @Operation(summary = "Update user.")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Performed.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserCRUDDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "User not found.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserCRUDDto.class)))
    })
    @PutMapping
    public UserCRUDDto updateUser(@RequestBody UserCRUDDto userDto) {
        return crudService.updateUser(userDto);
    }

    @Operation(summary = "Remove user.")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Performed.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserCRUDDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "User not found.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserCRUDDto.class)))
    })
    @DeleteMapping("{id}")
    public UserCRUDDto removeUser(@PathVariable Long id) {
        return crudService.deleteUser(id);
    }
}
