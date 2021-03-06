package ru.ikupdev.library.util;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.ikupdev.library.dto.*;
import ru.ikupdev.library.model.Book;
import ru.ikupdev.library.model.Bookshelf;
import ru.ikupdev.library.model.Role;
import ru.ikupdev.library.model.User;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Ilya V. Kupriyanov
 * @version 22.02.2022
 */
@Component
@RequiredArgsConstructor
public class MapperUtil {
    private final ModelMapper modelMapper;

    public static <R, E> List<R> convertList(List<E> list, Function<E, R> converter) {
        return list.stream().map(converter).collect(Collectors.toList());
    }

    public static <R, E> Set<R> convertSet(Set<E> set, Function<E, R> converter) {
        return set.stream().map(converter).collect(Collectors.toSet());
    }

    public UserViewDto convertUserToUserViewDto(User user) {
        return modelMapper.map(user, UserViewDto.class);
    }

    public UserResponseDto convertUserToUserResponseDto(User user) {
        UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);
        userResponseDto.setRoles(convertSet(user.getRoles(), this::convertRoleToRoleViewDto));
        return userResponseDto;
    }

    public RoleViewDto convertRoleToRoleViewDto(Role role) {
        return modelMapper.map(role, RoleViewDto.class);
    }

    public RoleResponseDto convertRoleToRoleResponseDto(Role role) {
        return modelMapper.map(role, RoleResponseDto.class);
    }

    public Role convertRoleRequestDtoToRole(RoleRequestDto roleRequestDto) {
        return modelMapper.map(roleRequestDto, Role.class);
    }

    public BookshelfResponseDto convertBookshelfToBookshelfResponseDto(Bookshelf bookshelf) {
        return modelMapper.map(bookshelf, BookshelfResponseDto.class);
    }

    public BookResponseDto convertBookToBookResponseDto(Book book) {
        return modelMapper.map(book, BookResponseDto.class);
    }

}
