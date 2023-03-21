package com.dongdongshop.filter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dongdongshop.data.Result;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Set;

@Component
@RefreshScope
public class TokenFilter implements GlobalFilter, Ordered {

    @Value("#{'${gateway.exclusions.url}'.empty ? null : '${gateway.exclusions.url}'.split(',')}")
    private Set<String> urls;

    @Autowired
    private JwtUtil jwtUtil;

    private PathMatcher pathMatcher = new AntPathMatcher();

    /*过滤器功能*/
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //通过请求取出token
        ServerHttpRequest request = exchange.getRequest();

        String path = request.getURI().getPath();

        for (String eurl : urls) {
            if (pathMatcher.match(eurl, path)) {
                return chain.filter(exchange);
            }
        }


        String token = request.getHeaders().getFirst("zhuqifa");//获取请求头中的token
        if (token == null || "".equals(token)) {
            return getVoidMono(exchange);
        }
        //如果token存在,检验token是否合法
        try {
            jwtUtil.parseJwt(token);
        } catch (Exception e) {
            return getVoidMono(exchange);
        }
        return chain.filter(exchange);
    }

    /*如果网关有多个过滤器 , 此数字表示过滤器的执行顺序,数字越小越先执行*/
    @Override
    public int getOrder() {
        return 0;
    }

    private Mono<Void> getVoidMono(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        //这里在返回头添加编码，否则中文会乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        byte[] bytes = JSONObject.toJSONBytes(Result.result(ResultEnum.INVALID_TOKEN), SerializerFeature.WriteMapNullValue);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Mono.just(buffer));
    }


}
