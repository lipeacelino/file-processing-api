package com.github.lipeacelino.fileconvertapi.controllers;

import com.github.lipeacelino.fileconvertapi.dto.OrderDetailResponseDTO;
import com.github.lipeacelino.fileconvertapi.dto.ParametersInputDTO;
import com.github.lipeacelino.fileconvertapi.dto.ProcessingResultDTO;
import com.github.lipeacelino.fileconvertapi.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/upload")
    @CacheEvict(allEntries = true, value = "orderDetail")
    public ProcessingResultDTO saveOrderDetailFromFile(@RequestParam MultipartFile file) {
                return orderService.saveOrderDetailFromFile(file);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<OrderDetailResponseDTO> findAllOrderDetail(
                             @SortDefault.SortDefaults({
                                @SortDefault(sort = "userId", direction = Sort.Direction.ASC)
                             })
                             Pageable pageable,
            ParametersInputDTO parametersInputDTO){
    return orderService.findAllOrderDetail(pageable, parametersInputDTO);
    }
}
