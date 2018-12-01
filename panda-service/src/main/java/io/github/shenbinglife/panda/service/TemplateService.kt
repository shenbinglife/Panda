package io.github.shenbinglife.panda.service

import com.fasterxml.jackson.databind.util.BeanUtil
import freemarker.template.Configuration
import freemarker.template.Template
import io.github.shenbinglife.common.base.util.JsonUtil
import io.github.shenbinglife.panda.domain.TemplateModel
import io.github.shenbinglife.panda.entity.Menu
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.datasource.DataSourceUtils
import org.springframework.stereotype.Service
import org.springframework.util.ResourceUtils
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets
import javax.sql.DataSource

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/11/30
 * @since since
 */
fun JsonUtil.toMap() :Unit  {

}

@Service
class TemplateService {
    companion object {
        val cfg = Configuration(Configuration.VERSION_2_3_28)
        init {
            cfg.defaultEncoding = StandardCharsets.UTF_8.displayName()
            val file = ResourceUtils.getFile("classpath:templates")
            cfg.setDirectoryForTemplateLoading(file)
        }
    }



    @Autowired
    lateinit var dataSource: DataSource

    @Autowired
    lateinit var menuService: MenuService

    fun build(model: TemplateModel) {
        val tableMeta = getTableMeta(model.table!!)
        val modelMap =JsonUtil.

        val workdir = System.getProperty("user.dir")

        if(modelMap.generateBackEnd) {
            val control = cfg.getTemplate("controller.ftl")
            val service = cfg.getTemplate("service.ftl")
            val entity = cfg.getTemplate("entity.ftl")
            doTemplate(modelMap, control, File(workdir, "panda-api\\src\\main\\java\\io\\github\\shenbinglife\\panda\\web\\controller"))
            doTemplate(modelMap, service, File(workdir, "panda-service\\src\\main\\java\\io\\github\\shenbinglife\\panda\\service"))
            doTemplate(modelMap, entity, File(workdir, "panda-base\\src\\main\\java\\io\\github\\shenbinglife\\panda\\entity"))
        }
        if(modelMap.generateVue) {
            val vue = cfg.getTemplate("vue.ftl")
            doTemplate(modelMap, vue, File(workdir, "panda-vue/src/components"))
        }
        if(modelMap.generateMenu) {
            val menu = Menu()
            menu.name = modelMap
        }
    }

    private fun doTemplate(map: Map<String, Any>, template: Template, outFiile: File) {
        OutputStreamWriter(FileOutputStream(outFiile)).use {
            template.process(template, it)
        }
    }

    fun getTableMeta(table: String): TableMeta {
        val connection = dataSource.connection
        try {
            val metaData = connection.metaData
            val tables = metaData.getTables(null, "%", table, arrayOf("TABLE"))
            while (tables.next()) {
                val name = tables.getString("TABLE_NAME")
                if (name == table) {
                    val tableMeta = TableMeta()
                    tableMeta.table = table
                    tableMeta.remarks = tables.getString("REMARKS")
                    val columns = metaData.getColumns(null, "%", table, "%")
                    while (columns.next()) {
                        val columnName = columns.getString("COLUMN_NAME")
                        val columnRemarks = columns.getString("REMARKS")
                        val dataType = columns.getInt("DATA_TYPE")
                        var length = columns.getInt("COLUMN_SIZE")
                        var nullable = columns.getString("IS_NULLABLE")
                        var defaultValue = columns.getString("COLUMN_DEF")
                        val column = Column(columnName, columnRemarks, dataType, "YES" == nullable, length, defaultValue)
                        tableMeta.columns.add(column)
                    }
                    return tableMeta
                }
            }
        } finally {
            DataSourceUtils.doCloseConnection(connection, dataSource)
        }
        throw RuntimeException("not found table:$table")
    }
}

class TableMeta {
    var table: String? = null
    var remarks: String? = null
    var columns: ArrayList<Column> = ArrayList()
    override fun toString(): String {
        return "TableMeta(table=$table, remarks=$remarks, columns=$columns)"
    }


}

class Column(name: String, remarks: String, dataType: Int, nullable: Boolean = true,
             length: Int = 0, defaultValue: String? = null) {
    var name = name
    var remarks = remarks
    var dataType = dataType
    var length = length
    var nullable = nullable
    var defaultValue = defaultValue
    override fun toString(): String {
        return "Column(name='$name', remarks='$remarks', dataType=$dataType, length=$length, nullable=$nullable, defaultValue=$defaultValue)"
    }

}

