package io.github.shenbinglife.panda.validator.validation

import io.github.shenbinglife.panda.entity.BaseEntity
import io.github.shenbinglife.panda.validator.ObjectExistsValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ObjectExistsValidator::class])
annotation class ObjectExistsValidation(
        val message: String,
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = [],

        val entityType: KClass<BaseEntity>
)
