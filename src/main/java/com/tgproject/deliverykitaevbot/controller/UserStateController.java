package com.tgproject.deliverykitaevbot.controller;

import com.tgproject.deliverykitaevbot.dto.UserStateDto;
import com.tgproject.deliverykitaevbot.service.UserStateService;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/userState")
public class UserStateController {

    private final UserStateService stateService;

    @Operation(summary = "Add userState.")
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    description = "UserState was created",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserStateDto.class))),
            @ApiResponse(responseCode = "404", description = "UserState not found"),
            @ApiResponse(responseCode = "403", description = "UserState already exist"),
            @ApiResponse(responseCode = "406", description = "UserState state field should be assigned by admins only!")
    })
    @PostMapping
    public ResponseEntity<UserStateDto> addUserState(@RequestBody @Valid UserStateDto userState) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stateService.save(userState));
    }

    @Operation(summary = "Find all userStates.")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Collection of UserStates was returned",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Collection.class))),
    })
    @GetMapping
    public Collection<UserStateDto> getAll() {
        return stateService.getAllUserStates();
    }

    @Operation(summary = "Find userState.")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "UserState was returned",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserStateDto.class))),
            @ApiResponse(responseCode = "404", description = "User state doesn't exist"),
    })
    @GetMapping("/{id}")
    public UserStateDto getUserStateById(@PathVariable Long id) {
        return stateService.getUserState(id);
    }

    @Operation(summary = "Update userState.")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "UserState was updated",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserStateDto.class))),
            @ApiResponse(responseCode = "404", description = "UserState not found"),
            @ApiResponse(responseCode = "406", description = "UserState state field should be assigned by admins only!")
    })
    @PutMapping
    public UserStateDto updateUserState(@RequestBody @Valid UserStateDto userState) {
        return stateService.update(userState);
    }

    @Operation(summary = "Delete userState.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "UserState was removed"),
            @ApiResponse(responseCode = "404", description = "User state doesn't exist")
    })
    @DeleteMapping("/{id}")
    public UserStateDto deleteUserState(@PathVariable Long id) {
        return stateService.removeUserState(id);
    }
}
