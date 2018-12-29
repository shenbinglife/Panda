package io.github.shenbinglife.panda.config

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import java.util.*
import javax.validation.Validator


@Configuration
class SpringConfiguration {

    @Bean
    fun getMessageSource(): MessageSource {
        val messageSource = ReloadableResourceBundleMessageSource()
        messageSource.setBasename("classpath:i18n/messages")
        messageSource.setDefaultEncoding("UTF-8")
        return messageSource
    }

    @Bean
    @Primary
    fun getValidator(): Validator {
        val validator = LocalValidatorFactoryBean()
        validator.setValidationMessageSource(getMessageSource())
        return validator
    }

}