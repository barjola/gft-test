package com.gft.test.infrastructure.out.repository.mapper;

import com.gft.test.domain.entity.Price;
import com.gft.test.domain.model.PriceModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceModel toDto(Price entity);
}