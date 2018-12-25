package io.github.shenbinglife.panda.utils

import io.github.shenbinglife.panda.entity.BaseEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Transactional
class BaseService<T : BaseEntity, Repository : JpaRepository<T, Long>> {
    protected val LOGGER: Logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    protected lateinit var baseDao: Repository

    fun getByPage(page: Int, size: Int, name: String?): Page<T> {
        val pageRequest = PageRequest.of(page - 1, size, Sort.by("updateTime").descending())
        val userDao = baseDao as JpaSpecificationExecutor<T>
        return userDao.findAll({ root, query, builder ->
            if (name != null && name.isNotBlank()) {
                query.where(builder.like(root.get("name"), "%$name%"))
            }
            query.restriction
        }, pageRequest)
    }

    @Transactional(readOnly = true)
    open fun getAll(): List<T> {
        return baseDao.findAll()
    }

    open fun create(t: T): T {
        baseDao.saveAndFlush(t)
        return t
    }

    open fun update(t: T): T {
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

    fun getById(id: Long): T? {
        return baseDao.findById(id).orElse(null)
    }
}