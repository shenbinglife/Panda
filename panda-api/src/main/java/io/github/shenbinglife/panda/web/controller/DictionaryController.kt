package io.github.shenbinglife.panda.web.controller

import io.github.shenbinglife.panda.entity.Dictionary
import io.github.shenbinglife.panda.service.DictionaryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("dictionaries")
class DictionaryController {
    @Autowired
    lateinit var dictionaryService: DictionaryService

    @GetMapping
    fun getDictionaries(@RequestParam(defaultValue = "1") page: Int,
                        @RequestParam(defaultValue = "10") pageSize: Int,
                        name: String?): Page<Dictionary> {
        return dictionaryService.getByPage(page, pageSize, name)
    }

    @PutMapping
    fun update(@RequestBody dictionary: Dictionary): Dictionary {
        return dictionaryService.update(dictionary)
    }

    @DeleteMapping
    fun delete(@RequestParam("ids") ids: List<Long?>) {
        val idsNotNull = ids.filterNotNull()
        dictionaryService.delete(idsNotNull)
    }
}
