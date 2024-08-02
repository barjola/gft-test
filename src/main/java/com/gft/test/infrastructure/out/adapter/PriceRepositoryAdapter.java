package com.gft.test.infrastructure.out.adapter;

import com.gft.test.domain.port.out.PriceRepository;
import com.gft.test.infrastructure.out.repository.entity.Price;
import com.gft.test.infrastructure.out.repository.jpa.PriceJpaRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepository {

    private final PriceJpaRepository priceJpaRepository;

    @Override
    public Optional<Price> findFirstPriceBetweenDates(LocalDateTime date, Long productId, Long brandId) {
        return priceJpaRepository.findFirstPriceBetweenDates(date, productId, brandId);
    }
}
