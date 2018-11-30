module.exports = {
    devServer: {
        // 设置主机地址
        host: "0.0.0.0",
        // 设置默认端口
        port: 38884,
        // 设置代理
        proxy: {
            "/api": {
                // 目标 API 地址
                target: "http://47.52.144.109:9000",
                // 如果要代理 websockets
                ws: true,
                // 将主机标头的原点更改为目标URL
                changeOrigin: true,
                pathRewrite: {
                    "^/api": "/panda"
                }
            }

        }
    }
};
