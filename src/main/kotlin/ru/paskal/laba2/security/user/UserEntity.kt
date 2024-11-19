package ru.paskal.laba2.security.user

import jakarta.persistence.Entity
import jakarta.persistence.Table
import ru.paskal.laba2.entities.BaseEntity

@Entity
@Table(name = "user_entity")
class UserEntity : BaseEntity() {
    var username: String? = null
    var password: String? = null
    var role: String? = null
    var enabled: Boolean? = null
}