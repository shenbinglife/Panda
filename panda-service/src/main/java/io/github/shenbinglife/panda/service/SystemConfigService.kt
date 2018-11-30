package io.github.shenbinglife.panda.service

import io.github.shenbinglife.panda.dao.SystemConfigRepository
import io.github.shenbinglife.panda.entity.SystemConfig
import io.github.shenbinglife.panda.utils.BaseService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class SystemConfigService : BaseService<SystemConfig, SystemConfigRepository>() {

}