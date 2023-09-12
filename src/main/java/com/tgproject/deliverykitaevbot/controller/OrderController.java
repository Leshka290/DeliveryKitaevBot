package com.tgproject.deliverykitaevbot.controller;

import com.tgproject.deliverykitaevbot.dto.OrderDto;
import com.tgproject.deliverykitaevbot.dto.UserCRUDDto;
import com.tgproject.deliverykitaevbot.service.OrderService;
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

@RestController()
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Find all orders.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Get all order",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Collection.class)
                    )
            )
    })
    @GetMapping
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @Operation(summary = "Find all orders.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Create order",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrderDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "order not found"
            )
    })
    @PostMapping
    public ResponseEntity<OrderDto> create(@Valid @RequestBody OrderDto dto, @Valid @RequestBody UserCRUDDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(dto, userDto));
    }

    @Operation(summary = "Find order.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Find order by id",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrderDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "order not found"
            )
    })
    @GetMapping("/{id}")
    public OrderDto read(@PathVariable Long id) {
        return orderService.read(id);
    }

    @Operation(summary = "Update order.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Update order by id",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrderDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "order not found"
            )
    })
    @PutMapping("/{id}")
    public OrderDto update(@PathVariable Long id, @Valid @RequestBody OrderDto dto) {
        return orderService.update(id, dto);
    }

    @Operation(summary = "Delete order.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Delete order by id",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrderDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "order not found"
            )
    })
    @DeleteMapping("/{id}")
    public OrderDto delete(@PathVariable Long id) {
        return orderService.delete(id);
    }
}
