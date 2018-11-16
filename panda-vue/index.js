var proxy = require('koa2-proxy');
const webpack = require('webpack');
const config = require('./build/webpack.dev.conf.js');
const compiler = webpack(config);
const koaWebpack = require('koa-webpack');

koaWebpack({ compiler })
.then((middleware) => {
  proxy.app.use(middleware);
});

// 本地静态服务器
proxy.static(__dirname);

// 本地模拟文件
proxy.mockfile(__dirname + '/index.html');

// 转发请求到指定host
proxy.when('/panda-api', function(ctx) {
  ctx.request.host = '127.0.0.1:8080';
  ctx.request.protocol = 'http';
});

// 配置代理请求结束后修改body
proxy.when({url: 'index.html'}, function(ctx) {
  // ctx.response.body += '<div>test</div>';
});

// 请求开始时转发本地请求到网络
proxy.on('start', function (ctx) {
  console.log('start: ', ctx.request.url, ctx.isLocal());
  // ctx.request.host = 'www.koa2.com';
});
// 请求结束时输出状态
proxy.on('end', function (ctx) {
  console.log('end: ' + ctx.response.status);
  console.log('end: ' + ctx.response.get('content-type'));
  console.log('-------------------------------')
  // console.log('end: ' + ctx.response.body);
});

// 监听端口
proxy.listen(3010);
