package com.tgproject.deliverykitaevbot.mapper.impl;

import com.tgproject.deliverykitaevbot.dto.AddressDto;
import com.tgproject.deliverykitaevbot.mapper.AddressMapper;
import com.tgproject.deliverykitaevbot.model.Address;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AddressMapperImpl implements AddressMapper {

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public Address toEntity(AddressDto dto) {
        return mapper.map(dto, Address.class);
    }

    @Override
    public AddressDto toDto(Address address) {
        return mapper.map(address, AddressDto.class);
    }
}
