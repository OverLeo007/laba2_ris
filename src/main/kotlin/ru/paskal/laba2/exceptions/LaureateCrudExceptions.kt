package ru.paskal.laba2.exceptions

class LaureateNotCreatedException(
    msg: String
) : BaseNotCreatedException("Laureate", msg)

class LaureateNotFoundException : BaseNotFoundException {
    constructor(id: Int) : super("Laureate", id)
    constructor(msg: String) : super("Laureate", msg)
}

class LaureateNotUpdatedException(
    msg: String
) : BaseNotUpdatedException("Laureate", msg)

class LaureateNotDeletedException(
    msg: String
) : BaseNotDeletedException("Laureate", msg)
