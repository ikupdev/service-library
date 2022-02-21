package ru.ikupdev.library.service;

import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;
import ru.ikupdev.library.dto.BookshelfRequestDto;
import ru.ikupdev.library.dto.BookshelfUpdateDto;
import ru.ikupdev.library.dto.RestResponseDto;
import ru.ikupdev.library.model.Bookshelf;

import java.util.List;

/**
 * @author Ilya V. Kupriyanov
 * @version 16.02.2022
 */
public interface IBookshelfService {
    RestResponseDto<Bookshelf> addNewBookshelf(Long userId, BookshelfRequestDto dto);

    void saveBookshelf(Bookshelf bookshelf);

    Bookshelf findByBookshelfName(String bookshelfName);

    RestResponseDto<List<Bookshelf>> getBookshelfList(Long userId, MultiValueMap<String, String> parameters, Pageable pageable);

    RestResponseDto<Bookshelf> getBookshelf(Long bookshelfId);

    Bookshelf findById(Long id);

    void deleteBookshelf(Long userId, Long bookshelfId);

    Bookshelf updateBookshelf(Long bookId, BookshelfUpdateDto bookshelfUpdateDto);

    Bookshelf getBookshelfByBookshelfNameOrElseNull(String bookshelfName);
}