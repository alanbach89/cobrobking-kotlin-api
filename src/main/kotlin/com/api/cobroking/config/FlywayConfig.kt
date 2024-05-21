package com.api.cobroking.config

import org.flywaydb.core.Flyway
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(FlywayProperties::class)
class FlywayConfig {

    @Bean(initMethod = "migrate")
    fun configureFlyway(flywayProperties: FlywayProperties): Flyway? {
        return Flyway.configure()
            .dataSource(
                flywayProperties.url,
                flywayProperties.user,
                flywayProperties.password
            )
            .locations(*flywayProperties.locations.toTypedArray())
            .baselineOnMigrate(true)
            .load()
    }
}

@ConfigurationProperties(prefix = "flyway")
data class FlywayProperties(
    var url: String = "",
    var user: String = "",
    var password: String = "",
    var locations: List<String> = emptyList()
)