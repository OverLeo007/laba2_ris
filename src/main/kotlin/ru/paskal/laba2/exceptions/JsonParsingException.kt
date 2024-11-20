package ru.paskal.laba2.exceptions

import com.fasterxml.jackson.databind.JsonNode

class JsonParsingException(json: JsonNode) : RuntimeException("Error while parsing json: $json")