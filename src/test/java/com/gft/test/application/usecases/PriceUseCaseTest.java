package com.gft.test.application.usecases;

import com.gft.test.domain.exception.PriceNotFoundException;
import com.gft.test.infrastructure.out.repository.jpa.PriceJpaRepository;
import com.gft.test.infrastructure.out.repository.mapper.PriceMapper;
import com.gft.test.utils.MockUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceUseCaseTest {

    @Mock
    private PriceJpaRepository priceJpaRepository;

    @InjectMocks
    private PriceUseCase priceUseCase;

    @Mock
    private PriceMapper priceMapper;

    @Test
    void queryPrices_whenExists_shouldReturnPrice() {
        var date = LocalDateTime.now();
        var productId = 11L;
        var brandId = 22L;

        var price = MockUtils.aPrice();
        var priceModel = MockUtils.aPriceModel();

        when(priceJpaRepository.findFirstPriceBetweenDates(date, productId, brandId))
                .thenReturn(Optional.of(price));

        when(priceMapper.toDto(price)).thenReturn(priceModel);

        var result = priceUseCase.queryPrice(date, productId, brandId);

        assertNotNull(result);
        assertEquals(priceModel, result);

    }

    @Test
    void queryPrices_whenNotExists_shouldThrowException() {
        var date = LocalDateTime.now();
        var productId = 11L;
        var brandId = 22L;

        when(priceJpaRepository.findFirstPriceBetweenDates(date, productId, brandId))
                .thenReturn(Optional.empty());

        assertThrows(PriceNotFoundException.class, () ->
                priceUseCase.queryPrice(date, productId, brandId));
    }

}