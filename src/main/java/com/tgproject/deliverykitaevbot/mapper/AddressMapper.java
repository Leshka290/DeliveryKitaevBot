package com.tgproject.deliverykitaevbot.mapper;

import com.tgproject.deliverykitaevbot.dto.AddressDto;
import com.tgproject.deliverykitaevbot.model.Address;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDto map(Address address);

    @InheritInverseConfiguration
    Address map(AddressDto dto);
}
