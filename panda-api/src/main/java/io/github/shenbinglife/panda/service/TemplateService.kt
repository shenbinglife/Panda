package io.github.shenbinglife.panda.service

import freemarker.template.Configuration
import freemarker.template.Template
import io.github.shenbinglife.panda.domain.TemplateModel
import io.github.shenbinglife.panda.entity.Menu
import io.github.shenbinglife.panda.exception.BusinessException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.datasource.DataSourceUtils
import org.springframework.stereotype.Service
import org.springframework.util.ResourceUtils
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.lang.StringBuilder
import java.nio.charset.StandardCharsets
import java.sql.Date
import java.sql.JDBCType
import javax.sql.DataSource

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/11/30
 * @since since
 */


@Service
class TemplateService {
    companion object {
        val LOGGER = LoggerFactory.getLogger(TemplateService::class.java)
        val FREEMARKER_CONFIG = Configuration(Configuration.VERSION_2_3_28)
        const val SERVICE_PATH = "panda-service/src/main/java/io/github/shenbinglife/panda/service"
        const val ENTITY_PATH = "panda-base/src/main/java/io/github/shenbinglife/panda/entity"
        const val CONTROLLER_PATH = "panda-api/src/main/java/io/github/shenbinglife/panda/web/controller"
        const val REPOSITORY_PATH = "panda-dao/src/main/java/io/github/shenbinglife/panda/dao"
        const val VUE_PATH = "panda-vue/src/components"

        val JDBC_TYPE_MAPPING = HashMap<JDBCType, String>()

        init {
            FREEMARKER_CONFIG.defaultEncoding = StandardCharsets.UTF_8.displayName()
            val file = ResourceUtils.getFile("classpath:templates")
            FREEMARKER_CONFIG.setDirectoryForTemplateLoading(file)
            FREEMARKER_CONFIG.templateUpdateDelayMilliseconds = 0L

            JDBC_TYPE_MAPPING.put(JDBCType.NVARCHAR,  String::class.simpleName!!)
            JDBC_TYPE_MAPPING.put(JDBCType.VARCHAR,  String::class.simpleName!!)
            JDBC_TYPE_MAPPING.put(JDBCType.BIGINT,  Long::class.simpleName!!)
            JDBC_TYPE_MAPPING.put(JDBCType.INTEGER,  Int::class.simpleName!!)
            JDBC_TYPE_MAPPING.put(JDBCType.BIT,  Boolean::class.simpleName!!)
            JDBC_TYPE_MAPPING.put(JDBCType.BLOB,  ByteArray::class.simpleName!!)
            JDBC_TYPE_MAPPING.put(JDBCType.BINARY,  ByteArray::class.simpleName!!)
            JDBC_TYPE_MAPPING.put(JDBCType.LONGVARBINARY,  ByteArray::class.simpleName!!)
            JDBC_TYPE_MAPPING.put(JDBCType.CHAR, String::class.simpleName!!)
            JDBC_TYPE_MAPPING.put(JDBCType.DATE, Date::class.simpleName!!)
            JDBC_TYPE_MAPPING.put(JDBCType.TIMESTAMP, Date::class.simpleName!!)
            JDBC_TYPE_MAPPING.put(JDBCType.FLOAT, Float::class.simpleName!!)
            JDBC_TYPE_MAPPING.put(JDBCType.DOUBLE, Double::class.simpleName!!)
            JDBC_TYPE_MAPPING.put(JDBCType.DECIMAL, Double::class.simpleName!!)
            JDBC_TYPE_MAPPING.put(JDBCType.NUMERIC, Double::class.simpleName!!)
        }
    }


    @Autowired
    lateinit var dataSource: DataSource

    @Autowired
    lateinit var menuService: MenuService

    fun build(model: TemplateModel) {
        val tableMeta = getTableMeta(model.table)
        tableMeta.tablePrefix = model.tablePrefix

        val workdir = System.getProperty("user.dir")

        if (model.generateMenu) {
            val menu = Menu()
            menu.code = tableMeta.table.toUpperCase()
            menu.parentId = -1
            menu.description = tableMeta.remarks
            menu.name = if(tableMeta.remarks.isNullOrBlank()) tableMeta.table else tableMeta.remarks
            menu.sort = 99
            menu.url = "/${tableMeta.variableName}s"

            val existsMenu = menuService.getByCode(menu.code!!)
            if(existsMenu != null) {
                throw BusinessException("the menu code exists already, can not create again: ${menu.code}")
            }
            menuService.create(menu)
        }
        if (model.generateVue) {
            val vue = FREEMARKER_CONFIG.getTemplate("vue.ftl")
            doTemplate(tableMeta, vue, File(workdir, VUE_PATH))
        }
        if (model.generateBackEnd) {
            val controlFtl = FREEMARKER_CONFIG.getTemplate("controller.ftl")
            val serviceFtl = FREEMARKER_CONFIG.getTemplate("service.ftl")
            val entityFtl = FREEMARKER_CONFIG.getTemplate("entity.ftl")
            val daoFtl = FREEMARKER_CONFIG.getTemplate("repository.ftl")

            val controlKt = File(File(workdir, CONTROLLER_PATH), "${tableMeta.className}Controller.kt")
            val serviceKt = File(File(workdir, SERVICE_PATH), "${tableMeta.className}Service.kt")
            val entityKt = File(File(workdir, ENTITY_PATH), "${tableMeta.className}.kt")
            val daoKt = File(File(workdir, REPOSITORY_PATH), "${tableMeta.className}Repository.kt")

            doTemplate(tableMeta, controlFtl, controlKt)
            doTemplate(tableMeta, serviceFtl, serviceKt)
            doTemplate(tableMeta, entityFtl, entityKt)
            doTemplate(tableMeta, daoFtl, daoKt)
        }
    }

    /**
     * 渲染FreeMarker模版
     */
    private fun doTemplate(map: Any, template: Template, outFiile: File) {
        OutputStreamWriter(FileOutputStream(outFiile)).use {
            template.process(map, it)
        }
    }

    /**
     * 使用当前DataSource获取数据库元数据信息
     */
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
                        val column = Column(columnName, columnRemarks, dataType,
                                "YES" == nullable, length, defaultValue)
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

    fun getTables(): List<String> {
        val connection = dataSource.connection
        val tableNames = ArrayList<String>()
        try {
            val metaData = connection.metaData
            val tables = metaData.getTables(null, "%", "%", arrayOf("TABLE"))
            while (tables.next()) {
                val name = tables.getString("TABLE_NAME")
                tableNames.add(name)
            }
            return tableNames
        } finally {
            DataSourceUtils.doCloseConnection(connection, dataSource)
        }
    }
}

class TableMeta {
    lateinit var table: String
    var tablePrefix: String? = null
    var remarks: String? = null
    var columns: ArrayList<Column> = ArrayList()
    override fun toString(): String {
        return "TableMeta(table=$table, remarks=$remarks, columns=$columns)"
    }

    val className: String
        get() {
            var tmpName = table.toLowerCase()
            if (!tablePrefix.isNullOrBlank()) {
                tmpName = table.removePrefix(tablePrefix!!)
            }
            val builder = StringBuilder()
            var upper = true
            tmpName.forEach {
                if (it == '_') {
                    upper = true
                } else {
                    val char = if (upper) it.toUpperCase() else it
                    builder.append(char)
                    upper = false
                }
            }
            return builder.toString()
        }
    val variableName: String
        get() {
            var tmp = this.className
            return tmp[0].toLowerCase() + tmp.substring(1)
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
    val fieldName: String
        get() {
            val builder = StringBuilder()
            var upper = false
            name.toLowerCase().forEach {
                if (it == '_') {
                    upper = true
                } else {
                    val tmp = if (upper) it.toUpperCase() else it
                    builder.append(tmp)
                    upper = false
                }
            }
            return builder.toString()
        }
    val fieldType: String
        get() {
            val jdbcType = JDBCType.valueOf(dataType)
            return TemplateService.JDBC_TYPE_MAPPING.getOrElse(jdbcType) {
                TemplateService.LOGGER.warn("can not resolve column[$dataType] jdbcType: $jdbcType")
                "Unknown"
            }
        }

    override fun toString(): String {
        return "Column(name='$name', remarks='$remarks', dataType=$dataType, " +
                "length=$length, nullable=$nullable, defaultValue=$defaultValue)"
    }

}