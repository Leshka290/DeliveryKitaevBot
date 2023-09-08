package com.tgproject.deliverykitaevbot.service;

import com.tgproject.deliverykitaevbot.dto.InlineMenuDto;

import java.util.Collection;

public interface InlineMenuService {

    /**
     * Получить пункт меню по ID
     *
     * @param id Long
     * @return InlineMenuDto
     */
    InlineMenuDto getInlineMenu(Long id);

    /**
     * Получить все пункты меню
     *
     * @return Collection<InlineMenuDto>
     */
    Collection<InlineMenuDto> getAllInlineMenu();

    /**
     * Обновить пункт меню
     *
     * @param inlineMenuDto InlineMenuDto
     * @return InlineMenuDto
     */
    InlineMenuDto updateInlineMenu(InlineMenuDto inlineMenuDto);

    /**
     * Удалить пункт меню
     *
     * @param id Long
     * @return InlineMenuDto
     */
    InlineMenuDto deleteInlineMenu(Long id);
}
