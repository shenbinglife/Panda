package io.github.shenbinglife.panda.validator

import io.github.shenbinglife.panda.utils.BaseService
import io.github.shenbinglife.panda.validator.validation.ObjectExistsValidation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import java.lang.reflect.ParameterizedType
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class ObjectExistsValidator : ConstraintValidator<ObjectExistsValidation, Long> {
    lateinit var annotation: ObjectExistsValidation

    @Autowired
    lateinit var context: ApplicationContext

    override fun initialize(constraintAnnotation: ObjectExistsValidation) {
        this.annotation = constraintAnnotation
    }

    override fun isValid(value: Long?, p1: ConstraintValidatorContext): Boolean {
        if (value == null)
            return true
        val beansOfType = context.getBeansOfType(BaseService::class.java)
        val serviceInstance = beansOfType.values.find {
            val superClass = it.javaClass.genericSuperclass
            val superType = superClass as ParameterizedType
            superType.actualTypeArguments[0] == this.annotation.entityType
        }
        if (serviceInstance == null) {
            throw RuntimeException("No entity service instance for ${annotation.entityType}")
        } else {
            return serviceInstance.getById(value) != null
        }

    }
}