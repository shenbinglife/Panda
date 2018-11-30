package io.github.shenbinglife.panda.validation

import io.github.shenbinglife.panda.utils.BaseService
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class ObjectExistsValidation (
        val message: String,
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = [],

        val serviceClass: KClass<BaseService<*, *>>
)
