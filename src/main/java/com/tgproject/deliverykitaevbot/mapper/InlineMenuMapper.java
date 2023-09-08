package com.tgproject.deliverykitaevbot.mapper;

import com.tgproject.deliverykitaevbot.dto.InlineMenuDto;
import com.tgproject.deliverykitaevbot.model.InlineMenu;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InlineMenuMapper {

    InlineMenuDto toDto(InlineMenu inlineMenu);

    InlineMenu toEntity(InlineMenuDto dto);
}
