package io.github.shenbinglife.panda.web.config

import io.github.shenbinglife.common.base.util.UUIDGenerator
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter

@WebFilter("/*")
class AuthFilter: Filter {
    override fun doFilter(request: ServletRequest, resposne: ServletResponse, chain: FilterChain) {
        request.setAttribute("Request-Id", UUIDGenerator.generate())
        chain.doFilter(request, resposne)
    }

}