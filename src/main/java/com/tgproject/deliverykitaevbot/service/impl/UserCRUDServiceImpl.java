package com.tgproject.deliverykitaevbot.service.impl;

import com.tgproject.deliverykitaevbot.dto.UserCRUDDto;
import com.tgproject.deliverykitaevbot.mapper.UserCRUDMapper;
import com.tgproject.deliverykitaevbot.model.User;
import com.tgproject.deliverykitaevbot.repository.RestaurantRepository;
import com.tgproject.deliverykitaevbot.repository.UserRepository;
import com.tgproject.deliverykitaevbot.service.UserCRUDService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserCRUDServiceImpl implements UserCRUDService {

    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserCRUDMapper userMapper;

    @Override
    public UserCRUDDto getUser(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public Collection<UserCRUDDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public UserCRUDDto updateUser(UserCRUDDto userDto) {
        restaurantRepository.findById(userDto.getRestaurantId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
    }

    @Override
    public UserCRUDDto deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User not found"));
        userRepository.delete(user);
        return userMapper.toDto(user);
    }
}