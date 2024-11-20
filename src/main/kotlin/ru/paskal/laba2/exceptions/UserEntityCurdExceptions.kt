package ru.paskal.laba2.exceptions

class UserEntityNotCreatedException(
    msg: String
) : BaseNotCreatedException("UserEntity", msg)

class UserEntityNotFoundException : BaseNotFoundException {
    constructor(id: Int) : super("UserEntity", id)
    constructor(msg: String) : super("UserEntity", msg)
}

class UserEntityNotUpdatedException(
    msg: String
) : BaseNotUpdatedException("UserEntity", msg)

class UserEntityNotDeletedException(
    msg: String
) : BaseNotDeletedException("UserEntity", msg)