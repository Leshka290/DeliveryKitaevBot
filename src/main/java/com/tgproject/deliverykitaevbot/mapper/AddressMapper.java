package com.tgproject.deliverykitaevbot.mapper;

import com.tgproject.deliverykitaevbot.dto.AddressDto;
import com.tgproject.deliverykitaevbot.model.Address;

public interface AddressMapper {

    Address toEntity(AddressDto dto);

    AddressDto toDto(Address address);
}
