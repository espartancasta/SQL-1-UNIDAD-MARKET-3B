package com.tecdesoftware.market.web.controller;

import com.tecdesoftware.market.domain.Purchase;
import com.tecdesoftware.market.domain.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Operation(summary = "Get all purchases", description = "Returns all purchases from the system")
    @ApiResponse(responseCode = "200", description = "Purchases retrieved successfully")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/all")
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get purchases by client ID", description = "Returns all purchases associated with a specific client ID")
    @ApiResponse(responseCode = "200", description = "Purchases retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Client or purchases not found")
    @ApiResponse(responseCode = "400", description = "Invalid client ID")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/client/{idClient}")
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable("idClient") String clientId) {
        return purchaseService.getByClient(clientId)
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @Operation(summary = "Save a new purchase", description = "Registers a new purchase in the system")
    @ApiResponse(responseCode = "201", description = "Purchase created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid purchase data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping("/save")
    public ResponseEntity<Purchase> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Purchase object to be saved",
                    required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    value = """
                                        {
                                          "clientId": "123456",
                                          "date": "2025-07-21T10:00:00",
                                          "paymentMethod": "Credit Card",
                                          "comment": "First purchase",
                                          "state": "CREATED",
                                          "items": [
                                            {
                                              "productId": 1,
                                              "quantity": 2,
                                              "total": 199.99,
                                              "active": true
                                            },
                                            {
                                              "productId": 2,
                                              "quantity": 1,
                                              "total": 99.99,
                                              "active": true
                                            }
                                          ]
                                        }
                                        """
                            )
                    )
            )
            @RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }

}
