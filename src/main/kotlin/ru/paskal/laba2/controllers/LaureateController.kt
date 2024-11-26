package ru.paskal.laba2.controllers

import io.github.oshai.kotlinlogging.KotlinLogging
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import ru.paskal.laba2.client.Requester
import ru.paskal.laba2.dtos.LaureateDto
import ru.paskal.laba2.dtos.mapToEntity
import ru.paskal.laba2.dtos.responses.UpdateFromApiResponse
import ru.paskal.laba2.exceptions.AccessForbiddenException
import ru.paskal.laba2.security.user.UserPrincipal
import ru.paskal.laba2.services.LaureateService


private val log = KotlinLogging.logger {}

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api/laureates")
@Tag(name = "Laureates", description = "API базы данных нобелевских лауреатов")
@SecurityRequirement(name = "bearer-key")
class LaureateController(
    private val requester: Requester,
    private val laureateService: LaureateService
) {

    @GetMapping
    @Secured("ROLE_USER")
    @Operation(summary = "Получить лауреатов", description = "Возвращает список лауреатов с " +
            "возможностью фильтрации по одному любому полю и мета-данными, " +
            "при необходимости (указать Query параметр 'meta')")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Успешное получение списка лауреатов"),
            ApiResponse(responseCode = "400", description = "Неверный запрос"),
            ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            ApiResponse(responseCode = "404", description = "Ресурс не найден"),
            ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
        ]
    )
    fun getLaureates(
        @AuthenticationPrincipal principal: UserPrincipal?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "Параметры запроса, включающие необязательный пааметр 'meta' и одно поле для фильтрации",
            examples = [
                ExampleObject(name = "C метаданными", value = "{\"firstname\": \"testNew\", \"meta\": \"\"}"),
                ExampleObject(name = "Без метаданых", value = "{\"firstname\": \"testNew\"}")
            ]
        )
        @RequestParam params: Map<String, String>,
    ): Any {
        if (principal == null) {
            log.debug { "Unauthorized request" }
            throw AccessForbiddenException("Unauthorized")
        }
        log.debug { "Authorized request" }
        log.debug { "Try to get laureates" }
        val isMeta = params.keys.contains("meta")
        val filteredParams = params.filterKeys { it != "meta" }
        val key = filteredParams.keys.firstOrNull()
        val value = filteredParams.values.firstOrNull()
        return if (isMeta) {
            laureateService.getAllWithMeta(key, value)
        } else {
            laureateService.getFullGraph(key, value)
        }
    }

    @PostMapping
    @Secured("ROLE_USER")
    @Operation(summary = "Обовить базу данных", description = "Обновляет базу данных с внешенго API, добавляя отсутствующие строки")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Таблица успешно обновлена"),
            ApiResponse(responseCode = "400", description = "Неверный запрос"),
            ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
        ]
    )
    fun addLaureate(
        @AuthenticationPrincipal principal: UserPrincipal?,
        @RequestBody laureate: LaureateDto
    ) {
        if (principal == null) {
            log.debug { "Unauthorized request" }
            throw AccessForbiddenException("Unauthorized")
        }
        log.debug { "Authorized request" }
        log.debug { "Try to add laureate" }
        laureateService.saveOne(laureate.mapToEntity())

    }

    @PostMapping("/update")
    @Secured("ROLE_USER")
    @Operation(summary = "Добавить лауреата", description = "Добавляет нового лауреата")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Таблица успешно обновлена"),
            ApiResponse(responseCode = "400", description = "Неверный запрос"),
            ApiResponse(responseCode = "403", description = "Доступ запрещен"),
            ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
        ]
    )
    fun updateFromApi(
        @AuthenticationPrincipal principal: UserPrincipal?
    ): UpdateFromApiResponse {
        if (principal == null) {
            log.debug { "Unauthorized request" }
            throw AccessForbiddenException("Unauthorized")
        }
        log.debug { "Authorized request" }
        log.debug { "Try to update data from api" }
        return requester.fetchRequest()
    }

}