package com.gft.test.domain.port.out;

import com.gft.test.infrastructure.out.repository.entity.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {

    Optional<Price> findFirstPriceBetweenDates(
            LocalDateTime date,
            Long productId,
            Long brandId);
}
