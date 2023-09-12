package com.tgproject.deliverykitaevbot.service.impl;

import com.tgproject.deliverykitaevbot.dto.UserStateDto;
import com.tgproject.deliverykitaevbot.mapper.UserStateMapper;
import com.tgproject.deliverykitaevbot.model.UserState;
import com.tgproject.deliverykitaevbot.repository.UserStateRepository;
import com.tgproject.deliverykitaevbot.service.UserStateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserStateServiceImpl implements UserStateService {

    private final UserStateRepository stateRepository;
    private final UserStateMapper userStateMapper;
    private final String LOG_SAMPLE = "Service method {} was invoked with parameter :: {}";
    private final String NOT_FOUND = "User state doesn't exist";
    private final String NOT_ACCEPTABLE = "User state field should be assigned by admins only!";
    private final String FORBIDDEN = "This state creation is not allowed!";

    @Override
    public Collection<UserStateDto> getAllUserStates() {
        Collection<UserStateDto> all = stateRepository.findAll()
                .stream()
                .map(userStateMapper::map)
                .collect(Collectors.toCollection(ArrayList::new));
        log.debug(LOG_SAMPLE, "getAll", Void.TYPE);
        return all;
    }

    @Override
    public UserStateDto getUserState(Long id) {
        log.debug(LOG_SAMPLE, "getUserState", id);
        return stateRepository.findById(id)
                .map(userStateMapper::map)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
    }

    @Override
    public UserStateDto save(UserStateDto userStateDto) {
//        if (Objects.nonNull(userStateDto.getTagSpecial())) {
//            throw new IllegalArgumentException(NOT_ACCEPTABLE);
//        } else if (stateRepository.findById(userStateDto.getId()).isPresent() ||
//                userStateDto.getRestaurantId() < 1) {
//            throw new EntityExistsException(FORBIDDEN);
//        }
        log.debug(LOG_SAMPLE, "save", userStateDto);
        return userStateMapper.map(stateRepository.save(userStateMapper.map(userStateDto)));
    }

    @Override
    public UserStateDto update(UserStateDto userStateDto) {
        if (Objects.nonNull(userStateDto.getTagSpecial())) {
            throw new IllegalArgumentException(NOT_ACCEPTABLE);
        } else if (stateRepository.findById(userStateDto.getId()).isEmpty() ||
                userStateDto.getId() < 0 ||
                userStateDto.getRestaurantId() < 1) {
            throw new EntityExistsException(FORBIDDEN);
        }
        log.debug(LOG_SAMPLE, "update", userStateDto);
        return userStateMapper.map(stateRepository.save(userStateMapper.map(userStateDto)));
    }

    @Override
    public UserStateDto removeUserState(Long id) {
        log.debug(LOG_SAMPLE, "removeUserState", Void.TYPE);
        UserState state = stateRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(NOT_FOUND));
        UserStateDto stateDto = userStateMapper.map(state);
        stateRepository.deleteById(id);
        return stateDto;
    }
}
