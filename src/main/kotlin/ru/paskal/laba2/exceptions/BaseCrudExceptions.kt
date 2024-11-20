package ru.paskal.laba2.exceptions

abstract class BaseNotCreatedException(
    entityType: String,
    msg: String
) : RuntimeException("Entity $entityType not created because: $msg")

abstract class BaseNotFoundException : RuntimeException {

    constructor(entityType: String, id: Int) :
            super("Entity $entityType with id: $id not found!")

    constructor(entityType: String, msg: String) :
            super("Entity $entityType not found because: $msg")
}

abstract class BaseNotUpdatedException(
    entityType: String,
    msg: String
) : RuntimeException("Entity $entityType not updated because: $msg")

abstract class BaseNotDeletedException(
    entityType: String,
    msg: String
) : RuntimeException("Entity $entityType not deleted because: $msg")