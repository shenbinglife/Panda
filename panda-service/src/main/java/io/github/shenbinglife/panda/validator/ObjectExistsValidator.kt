package io.github.shenbinglife.panda.validator

import io.github.shenbinglife.panda.validation.ObjectExistsValidation
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

@Component
class ObjectExistsValidator : ConstraintValidator<ObjectExistsValidation, Long>, ApplicationContextAware {
    var context: ApplicationContext? = null
    var annotation: ObjectExistsValidation? = null

    override fun initialize(constraintAnnotation: ObjectExistsValidation) {
        this.annotation = constraintAnnotation
    }

    override fun isValid(value: Long?, p1: ConstraintValidatorContext): Boolean {
        if (value == null)
            return true
        val serviceClass = annotation!!.serviceClass
        val service = context!!.getBean(serviceClass.java)
        return service.getById(value) != null

    }

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.context = applicationContext
    }
}