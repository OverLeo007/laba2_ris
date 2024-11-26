package ru.paskal.laba2.security.user

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.paskal.laba2.exceptions.UserEntityNotCreatedException
import ru.paskal.laba2.exceptions.UserEntityNotFoundException

private val log = KotlinLogging.logger {}

@Service
@Transactional(readOnly = true)
class UserEntityService(
    private val repository: UserEntityRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun getByUsername(username: String?): UserEntity? {
        return repository.findByUsername(username)
    }

    fun getById(id: Int): UserEntity {
        return repository.findById(id).orElseThrow { UserEntityNotFoundException(id) }
    }

    @Transactional
    fun save(user: UserEntity): UserEntity {
         return repository.save(user)
    }

    @Transactional
    fun createUser(username: String, password: String, role: String): UserEntity {
        if (repository.existsByUsername(username)) {
            throw UserEntityNotCreatedException("User with username $username already exists")
        }
        val user = UserEntity()
        user.username = username
        user.password = passwordEncoder.encode(password)
        user.role = "ROLE_USER"
        return save(user)
    }


}