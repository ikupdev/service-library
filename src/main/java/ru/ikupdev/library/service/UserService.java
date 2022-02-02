package ru.ikupdev.library.service;

/**
 * @author Ilya V. Kupriyanov
 * @version 15.12.2021
 */
//@Service
//@RequiredArgsConstructor
//public class UserService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final UserConverter userConverter;
//
//    public List<User> findAll() {
//        return userRepository.findAll();
//    }
//
//    public User findById(Long userId) {
//        return getUserByIdOrElseThrow(userId);
//    }
//
//    public boolean checkIsExistByLogin(String login) {
//        try {
//            getUserByLoginOrElseThrow(login);
//            return true;
//        } catch (NotFoundException e) {
//            return false;
//        }
//    }
//
//    public User findByLogin(String login) {
//        return getUserByLoginOrElseThrow(login);
//    }
//
//    public User updateUser(User user) {
//        return userRepository.save(user);
//    }
//
//    public void updateUser(Long userId, UserTO userTO) {
//        User existUser = getUserByIdOrElseThrow(userId);
//        User user;
//        if (passwordEncoder.matches(userTO.getPassword(), existUser.getHashPassword())) {
//            user = userConverter.fromUserTO(userTO, false);
//            user.setHashPassword(existUser.getHashPassword());
//        } else {
//            user = userConverter.fromUserTO(userTO, true);
//        }
//        user.setId(userId);
//        userRepository.save(user);
//    }
//
//    private User getUserByIdOrElseThrow(Long id) {
//        return userRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("Пользователь с id " + id + " не найден!"));
//    }
//    private User getUserByLoginOrElseThrow(String login) {
//        return userRepository.findUserByLogin(login)
//                .orElseThrow(() -> new NotFoundException("Пользователь с login = " + login + " не найден!"));
//    }
//
//}