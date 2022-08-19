package com.drool.userstore

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import javax.validation.constraints.NotBlank


@Configuration
@ConfigurationProperties(prefix = "drool-config")
class RuleConfig {

    @NotBlank
    var url: String= ""
    @NotBlank
    var user: String= ""
    @NotBlank
    var password: String= ""
    @NotBlank
    var containerId: String= ""
}