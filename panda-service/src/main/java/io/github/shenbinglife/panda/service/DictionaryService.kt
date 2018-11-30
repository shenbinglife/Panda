package io.github.shenbinglife.panda.service

import io.github.shenbinglife.panda.dao.DictionaryRepository
import io.github.shenbinglife.panda.entity.Dictionary
import io.github.shenbinglife.panda.utils.BaseService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DictionaryService : BaseService<Dictionary, DictionaryRepository>() {


}