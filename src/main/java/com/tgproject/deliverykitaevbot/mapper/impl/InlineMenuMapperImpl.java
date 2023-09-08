package com.tgproject.deliverykitaevbot.mapper.impl;

import com.tgproject.deliverykitaevbot.dto.InlineMenuDto;
import com.tgproject.deliverykitaevbot.mapper.InlineMenuMapper;
import com.tgproject.deliverykitaevbot.model.InlineMenu;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class InlineMenuMapperImpl implements InlineMenuMapper {
    ModelMapper mapper = new ModelMapper();
    @Override
    public InlineMenuDto toDto(InlineMenu inlineMenu) {
        return mapper.map(inlineMenu, InlineMenuDto.class);
    }

    @Override
    public InlineMenu toEntity(InlineMenuDto dto) {
        return mapper.map(dto, InlineMenu.class);
    }
}
