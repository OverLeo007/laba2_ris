package ru.paskal.laba2.security.user;

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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
        return repository.findById(id).orElseThrow { IllegalArgumentException("User with id $id not found") }
    }

    @Transactional
    fun save(user: UserEntity): UserEntity {
         return repository.save(user)
    }

    @Transactional
    fun createUser(username: String, password: String, role: String): UserEntity {
        if (repository.existsByUsername(username)) {
            throw IllegalArgumentException("User with username $username already exists")
        }
        val user = UserEntity()
        user.username = username
        user.password = passwordEncoder.encode(password)
        user.role = "ROLE_USER"
        return save(user)
    }


}