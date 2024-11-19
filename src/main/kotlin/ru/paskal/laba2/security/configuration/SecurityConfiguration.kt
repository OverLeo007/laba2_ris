package ru.paskal.laba2.security.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.*
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import ru.paskal.laba2.security.handlers.UnauthorizedHandler
import ru.paskal.laba2.security.jwtTools.JwtAuthFilter
import ru.paskal.laba2.security.user.UserDetailsServiceImpl

@Configuration
@EnableWebSecurity
@ComponentScan("ru.paskal.laba2")
class SecurityConfiguration(
    private val jwtAuthFilter: JwtAuthFilter,
    private val userDetailsService: UserDetailsServiceImpl,
    private val unauthorizedHandler: UnauthorizedHandler
) {
    @Bean
    @Throws(Exception::class)
    fun authManager(http: HttpSecurity): AuthenticationManager {
        val authenticationManagerBuilder =
            http.getSharedObject(AuthenticationManagerBuilder::class.java)
        authenticationManagerBuilder.userDetailsService<UserDetailsService>(userDetailsService)
        return authenticationManagerBuilder.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        configuration.allowedMethods = listOf("*")
        configuration.allowedHeaders = listOf("*")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Bean
    @Throws(java.lang.Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
        http
            .csrf { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() }
            .cors { cors: CorsConfigurer<HttpSecurity?> -> cors.configurationSource(corsConfigurationSource()) } //        .cors(AbstractHttpConfigurer::disable)
            .sessionManagement { conf: SessionManagementConfigurer<HttpSecurity?> ->
                conf.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            }
            .formLogin { obj: FormLoginConfigurer<HttpSecurity> -> obj.disable() }
            .exceptionHandling { conf: ExceptionHandlingConfigurer<HttpSecurity?> ->
                conf
                    .authenticationEntryPoint(unauthorizedHandler)
            }
            .securityMatcher("/**")
            .authorizeHttpRequests { registry ->
                registry
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/api/auth/login").permitAll()
                    .requestMatchers("/api/auth/register").permitAll()
                    .requestMatchers("/api/**").authenticated()
            }

        return http.build()
    }

}