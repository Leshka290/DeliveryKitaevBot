package com.tgproject.deliverykitaevbot.mapper;

import com.tgproject.deliverykitaevbot.dto.InlineMenuDto;
import com.tgproject.deliverykitaevbot.model.InlineMenu;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InlineMenuMapper {

    InlineMenuDto map(InlineMenu inlineMenu);

    @InheritInverseConfiguration
    InlineMenu map(InlineMenuDto dto);
}
