package io.github.shenbinglife.panda.service

import io.github.shenbinglife.panda.entity.BaseEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional
open class BaseService<T : BaseEntity, Repository : JpaRepository<T, Long>> {
    protected val LOGGER: Logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    protected lateinit var baseDao: Repository

    @Transactional(readOnly = true)
    open fun getAll(): List<T> {
        return baseDao.findAll()
    }

    open fun create(t: T): T {
        t.createTime = Date()
        t.updateTime = Date()
        baseDao.saveAndFlush(t)
        throw RuntimeException("xxx")
        return t
    }

    open fun update(t: T): T {
        t.updateTime = Date()
        baseDao.saveAndFlush(t)
        return t
    }

    open fun delete(id: Long) {
        try {
            baseDao.deleteById(id)
        } catch (e: EmptyResultDataAccessException) {
            LOGGER.info("entity not exists.")
        }
    }

    open fun delete(t: T) {
        baseDao.delete(t)
    }

    fun delete(ids: List<Long>) {
        for (id in ids) {
            this.delete(id)
        }
    }
}