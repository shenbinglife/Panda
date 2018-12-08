package io.github.shenbinglife.panda.web.config

import java.util.*
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter

@WebFilter("/*")
class AuthFilter: Filter {
    override fun doFilter(request: ServletRequest, resposne: ServletResponse, chain: FilterChain) {
        //TODO 替换为SnowFlower 算法
        request.setAttribute("Request-Id", UUID.randomUUID().toString())
        chain.doFilter(request, resposne)
    }

}