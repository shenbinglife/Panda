package io.github.shenbinglife.panda.service

import io.github.shenbinglife.panda.entity.BaseEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

open class BaseService<T : BaseEntity, Repository : JpaRepository<T, Long>> {
    protected val LOGGER: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    protected lateinit var baseDao: Repository

    fun getAll(): List<T> {
        return baseDao.findAll()
    }

    fun create(t: T): T {
        t.createTime = Date()
        t.updateTime = Date()
        baseDao.saveAndFlush(t)
        return t
    }

    fun update(t: T): T {
        t.updateTime = Date()
        baseDao.saveAndFlush(t)
        return t
    }

    fun delete(id: Long) {
        try {
            baseDao.deleteById(id)
        } catch (e: EmptyResultDataAccessException) {
            LOGGER.info("entity not exists.")
        }
    }

    fun delete(t: T) {
        baseDao.delete(t)
    }

    fun delete(ids: List<Long>) {
        for (id in ids) {
            this.delete(id)
        }
    }
}