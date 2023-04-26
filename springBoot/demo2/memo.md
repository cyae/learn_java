# 静态资源访问

* 因为boot已经默认配置过default-servlet, 所以和springMVC一样, 先找controller映射路径, 不匹配再使用default-servlet找静态资源, 再不匹配报404
* 静态资源的映射路径是根目录下resources/\*\*, 表明此文件夹下所有静态资源都可访问到
* 默认扫描顺序:
    1. resources/META-INF/resources/\*\*
    2. resources/resources/\*\*
    3. resources/static/\*\*
    4. resources/public/\*\*
* 访问静态资源默认是无前缀的, 即local:port/资源名 即可访问, 但这不利于filter过滤动态请求(/\*\*), 会导致连同静态资源一起过滤
  * 因此人为给静态资源访问添加前缀, 以使filter放行静态资源访问
  * spring.mvc.static-path-pattern: /res/**
