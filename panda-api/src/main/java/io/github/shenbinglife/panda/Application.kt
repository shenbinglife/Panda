package io.github.shenbinglife.panda

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration
import org.springframework.boot.validation.MessageInterpolatorFactory
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import java.util.*

@ServletComponentScan
@EnableJpaAuditing
@EnableJpaRepositories("io.github.shenbinglife.panda.dao")
@EntityScan("io.github.shenbinglife.panda.entity")
@SpringBootApplication(scanBasePackages = ["io.github.shenbinglife.panda"])
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}