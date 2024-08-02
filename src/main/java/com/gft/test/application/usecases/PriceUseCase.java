package com.gft.test.application.usecases;

import com.gft.test.domain.exception.PriceNotFoundException;
import com.gft.test.domain.model.PriceModel;
import com.gft.test.infrastructure.out.repository.jpa.PriceJpaRepository;
import com.gft.test.infrastructure.out.repository.mapper.PriceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PriceUseCase {

    private final PriceJpaRepository priceJpaRepository;
    private final PriceMapper priceMapper;

    public PriceModel queryPrice(LocalDateTime date, Long productId, Long brandId) {
        return priceJpaRepository.findFirstPriceBetweenDates(date, productId, brandId)
                .map(priceMapper::toDto)
                .orElseThrow(() -> new PriceNotFoundException(date, productId, brandId));
    }

}
