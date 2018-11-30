package io.github.shenbinglife.panda

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@ServletComponentScan
@EnableJpaAuditing
@EnableJpaRepositories("io.github.shenbinglife.panda.dao")
@EntityScan("io.github.shenbinglife.panda.entity")
@SpringBootApplication(scanBasePackages = ["io.github.shenbinglife.panda"])
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}