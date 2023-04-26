
# request到controller流程

1. 浏览器发送request请求到服务器
2. 若requestURL匹配到web.xml中的url-pattern, 则调用DispatcherServlet
3. DispatcherServlet读取SpringMVC配置文件, 通过组件扫描找到控制器Controller
4. 若requestURL匹配到@RequestMapping的value值, 则调用对应Controller的方法
5. Controller方法依次向下调用Service, Mapper, DB, 完成业务操作
6. 接着Controller方法返回String类型的视图名称, 被Thymeleaf视图解析器添加视图前后缀, 组成响应视图路径
7. Thymeleaf渲染视图, 转发/重定向到响应视图

# @RequestMapping注解

@RequestMapping注解用于匹配请求和Controller方法, 匹配规则分为:

* 必选规则:
  * value或path: 匹配请求路径host:port/artifact/path, **唯一标识**一个Controller方法
    * path支持ant风格的模糊通配符
      * ? 匹配一个字符, 如/artifact/path/user?
      * \* 匹配0或多个字符, 如/artifact/path/*
      * **匹配0或多层目录, 必需单独作为目录, 如/artifact/**/path
    * path支持占位符, 占位符用{}包裹
      * 在controller方法形参列表中, 用@PathVariable注解绑定
  * 假如匹配失败, 则报404未找到资源
  * 假如存在多个Controller方法, 且路径都相同
    * 如果method匹配规则也相同, 则报500服务器内部错误(❌多个方法, 同一路径, 同一请求方法)
    * 如果method匹配规则不同, 则是RESTful风格的资源路径(✔多个方法, 同一路径, 不同请求方法)
* 可选规则:
  * method: 匹配请求方法GET/POST/PUT/DELETE, 默认接受所有请求方法
    * 假如与浏览器的请求方法不匹配, 则报405请求方法不被允许
  * params: 匹配请求参数
    * 匹配表达式 param, !param, param=value, param!=value
    * 假如匹配失败, 则报400请求参数错误
  * headers: 匹配请求头里的键值对
    * ![header](assets\header.jpg)
    * 匹配表达式 key, !key, key=value, key!=value
    * 假如匹配失败, 则报404未找到资源

## @RequestMapping注解派生, 用于RestController

* @GetMapping: 匹配GET请求
* @PostMapping: 匹配POST请求
* @PutMapping: 匹配PUT请求
* @DeleteMapping: 匹配DELETE请求

# 参数传递

## 普通参数传递

将url请求参数与controller方法形参绑定

* 通过Servlet原生的request.getParameter()方法获取请求参数
  * 缺点: 不适用于restful风格的资源路径, 因为没有键值对, getParameter()找不到键名
* 通过Controller方法的形参获取
  * 缺点: 形参与请求参数名必须一致, 否则获取到null值
* 通过SpringMVC的@RequestParam(args...)注解, 建立形参与请求参数映射
  * args参数包括:
    * value 请求参数名, 可以与形参名不一致
    * required 默认为true, 表示被@RequestParam注解的参数必须传值, 否则报错400; 如果设为false, 表示传参可为null
    * defaultValue 不传值则以此默认值代替

## 请求头传递, Cookie传递

将请求头键值对信息/Cookie与controller方法形参绑定
分别使用@RequestHeader和@CookieValue注解, 注解参数同@RequestParam相同

## model属性传递

如果请求参数名和pojo类属性名一一对应, 则Controller方法的形参可以是pojo类

# 使用SpringMVC管理域对象

## 域种类

* page域, 一次页面内共享
* request域, 一次请求内共享
* session域, 一次会话内共享, 直到浏览器关闭或超时一直存在
* application/context域, 一次应用内共享, 直到tomcat关闭或超时一直存在
* 越往下粒度越大, 占用内存时长越长, 对吞吐量影响越大

## request域共享方法

使用各种方法存储键值对
![header](E:\Documents\JavaProjects\ModelMap.jpg)
无论使用以下哪种类或接口, 最终都被封装为ModelAndView

* 通过Servlet原生的request.setAttribute(key, value)
* 通过springMVC的ModelAndView.addAttribute(key, value)
* 通过springMVC的Model接口.addAttribute(key, value)
* 通过springMVC的ModelMap类.addAttribute(key, value)
* 通过Map.put(key, value)

## session域共享

* 使用Servlet原生的session.setAttribute(key, value)
* 通过Servlet原生的request.getSession().setAttribute(key, value)

## application/context域共享

* 使用Servlet原生的session.getServletContext().setAttribute(key, value)

# 视图

```java
public class DispatcherServlet() {
    
    public void func(HttpRequest request, HttpResponse response) {
      // 在DispatcherServlet中调用controller的具体方法
      ModelAndView mv = ha.handler(request, response, getHandler());
      if (mv != null) {
          // 进行渲染
          render(mv);
      }
    }
    
    public void render(ModelAndView mv, HttpRequest request) {
        // 设置文字-国家编码
        Locale locale = "zh-CN";
        
        // viewName即controller方法返回的字符串
        String viewName = mv.getViewName();
        
        View view;
        
        if (viewName != null) {
            // 创建视图
            // viewName可能是转发类型"forward:xxx", 解析为ForwardView
            // 重定向"redirect:xxx", 解析为RedirectView
            // "xxx"默认是转发类型, 但如果配置了thymeleaf, 则被thymeleaf视图解析器解析为thymeleafView
            view = resolveViewName(viewName, mv.getModelInternal(), locale, request);
        }
    }
    
}

```

转发与重定向的区别:

* 转发是浏览器发送了一次请求, 服务器内部跳转, 地址栏不变; 重定向是服务器拒绝当前请求, 浏览器重新发送请求, 地址栏改变
* 转发能获得request域中数据; 重定向由于新开了request, 所以不能
* 转发能访问WEB-INF中的资源, 因为是由服务器内部转发请求, 发起者是服务器本身, 有权访问WEB-INF中的安全资源; 重定向不行, 因为发起者是浏览器
* 转发不能跨域, 仅限于访问服务器内部资源; 重定向可以跨域到外部服务器, 比如重定向到www.baidu.com

# REST

是一种规范, 将服务器视为离散资源的集合, 这些集合的路径是确定的, 通过表现层的资源状态转移描述操作
RESTful操作资源 = 资源在服务器-客户端之间**转移**(GET/POST/PUT/DELETE) + 所操作资源的**状态**(动态/静态资源的路径)

| non-REST url          | REST url           |
|-----------------------|--------------------|
| selectUserById?id=666 | user/666, 使用GET    |
| insertUser            | user, 使用POST       |
| deleteUserById?id=666 | user/666, 使用DELETE |
| updateUser            | user, 使用PUT        |

* 由于部分浏览器不支持PUT和DELETE, 所以springMVC使用了**适配器模式**, 见springMVC第53话:

```java
    public class HiddenHttpMethodFilter() {
        // 过滤器使用FilterChain对request和response进行拦截或放行
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            // 真正使用的请求requestToUse
            HttpServletRequest requestToUse = request;
            
            // 如果请求方式为POST
            if ("POST".equals(request.getMethod())) {
                // 则将隐藏域键this.methodParam = _method对应的value解析出来, 这个value:
                // 可以是null, 对应POST请求
                // 可以是PUT, 对应PUT请求
                // 可以是DELETE, 对应DELETE请求
                String paramValue = request.getParameter(this.methodParam);
                // 如果paramValue == null, 表明本身就是POST请求, 无需进重新包装
                if (StringUtils.hasLength(paramValue)) {
                    String method = paramValue.toUpperCase(Locale.ENGLISH);
                    if (ALLOWED_METHODS.contains(method)) {
                        // 否则, 重新包装请求方式为value值
                        requestToUse = new HttpMethodRequestWrapper(request, method);
                    }
                }
            }
            
            // 过滤器使用FilterChain对真正使用的请求requestToUse和response进行拦截或放行
            filterChain.doFilter((ServletRequest)requestToUse, response);
        }
    }
    
```

* 发送PUT和DELETE请求时, 将method设置为POST. 但同时也携带hidden参数_method = 真实的PUT或DELETE
* 设置过滤器HiddenHttpMethodFilter, 等服务器接收到伪装的POST请求后, 将其重新设置为真实的PUT或DELETE

# 获取请求响应报文

## @RequestBody

功能上, 将请求体重json格式的键值对绑定到controller方法形参上, 一次绑定**请求体**中的**所有键值对**

* 与@PathVariable区别在于: 后者是将请求url中的占位符绑定到controller方法形参上, 一次只能绑定一个属性
* 与@RequestParam区别在于: 后者只能获取值, 无法获取键名

## RequestEntity<?>

作为形参, 包含请求头 + 请求体, 即RequestEntity = @RequestBody + @RequestHeader(value = {...})

## @ResponseBody

服务器向浏览器发送响应体

```java

@Controller
class xxxController {
  @RequestMapping("")
  public String index() {
      // 使用thymeleaf/redirect/forward作为响应, 响应体即为webapp下的html页面
      return "index";
  }
}

```

* 使用字符串形式的页面跳转, 响应体为html
* 使用servlet原生HttpServletResponseAPI, 响应体可任意定制
* 使用springMVC的@ResponseBody修饰controller方法, 返回值不再被thymeleaf/redirect/forward解析, 而是整体作为响应体
  * 当响应体为java类, 需要先转换为json格式, 否则浏览器无法识别java的toString\()
  * 使用jackson/gson/fastjson依赖 + spring配置注解驱动, 自动在HandlerAdaptor中开启消息转换器MappingJackson2HttpMessageConverter
  * JSON元素分为对象{}和数组\[], 对象以键值对形式存储java类的属性和属性值
    * Object, Map -> Json对象
    * List -> Json数组
* 在微服务中, 子服务之间靠json通信, 所以每一个服务器的controller都要@ResponseBody ==> @RestController = @Controller + @ResponseBody

## ResponseEntity<?>

* 用作controller方法的返回类型, 且返回值会直接作为响应报文, 不会被解析
* 用于文件下载, 下载方法返回值为ResponseEntity<byte[]>, 其中byte[]内存储文件

## 文件上传使用MultipartFile

# 拦截器

过滤器用于浏览器请求和DispatcherServlet之间  
拦截器用于DispatcherServlet和Controller(Handler)之间, 在doDispatch方法内执行  
拦截器可以拦截请求, 使用了责任链设计模式, 分为:

* preHandler
* postHandler
* afterCompletion

```java

class DispatcherServlet extends HttpServlet {
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) {
      try {
        ModelAndView mv = null;
        try {
            // mappedHandler = List[Controller方法, 1个springMVC默认拦截器, 自定义拦截器]
          mappedHandler = getHandler(request);
          HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());

          // 顺序执行preHandler
          if (!mappedHandler.applyPreHandle(processedRequest, response)) {
            return;
          }

          //执行controller方法
          mv = ha.handle(processedRequest, response, mappedHandler.getHandler());

          // 逆序执行postHandler
          mappedHandler.applyPostHandle(processedRequest, response, mv);
        } catch (Exception e) {
          e.printStackTrace();
        }
        //渲染视图, render+locale
        processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchException);
      } catch (Exception ex) {
        // 逆序执行afterCompletion
        triggerAfterCompletion(processedRequest, response, mappedHandler, ex);
      }
    }
}

```

## 多拦截器执行流程

一旦责任链上某拦截器的preHandle返回false, 则所有postHandle不会执行, 除当前拦截器外的afterCompletion仍会执行

# springMVC异常处理

* 基于配置, 在springMVC核心配置文件中使用SimpleMappingExceptionResolver, 以页面形式跳转
* 基于errorController, 需要注解@ControllerAdvice + @ExceptionHandler
* 基于全注解开发的配置

# SpringMVC执行流程

## 1、SpringMVC常用组件

* DispatcherServlet：**前端控制器**，不需要工程师开发，由框架提供

作用：统一处理请求和响应，整个流程控制的中心，由它调用其它组件处理用户的请求

* HandlerMapping：**处理器(Handler=Controller)映射器**，不需要工程师开发，由框架提供

作用：根据请求的url、method等信息查找Controller方法, 即RequestMapping/GetMapping/PostMapping...

* Handler：**处理器**，需要工程师开发

作用：在DispatcherServlet的控制下Controller对具体的用户请求进行处理, 向下调用Service

* HandlerAdapter：**处理器适配器**，不需要工程师开发，由框架提供

作用：通过HandlerAdapter对Controller方法进行执行, 真正的执行者, mv = ha.invoke

* ViewResolver：**视图解析器**，不需要工程师开发，由框架提供

作用：进行视图解析，得到相应的视图，例如：ThymeleafView、InternalResourceView(forward:)、RedirectView(redirect:)

* View：**视图**

作用：将模型数据通过页面展示给用户

## 2、DispatcherServlet初始化过程

DispatcherServlet继承了FrameworkServlet继承了HttpServletBean继承了HttpServlet继承了Servlet  
所以天然的遵循 Servlet 的生命周期。

![images](assets/img005.png)

### a>初始化IOC容器   WebApplicationContext(对比jar包的容器是ApplicationContext)

所在类：org.springframework.web.servlet.FrameworkServlet

```java
class FrameworkServlet {
  protected WebApplicationContext initWebApplicationContext() {
    WebApplicationContext rootContext =
            WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    WebApplicationContext wac = null;

    // 初始化阶段, IOC容器一般为空, 所以本if不执行, 其内部的wac赋值操作也不执行, wac保持空
    if (this.webApplicationContext != null) {
      // 否则, 初始化时IOC容器就非空, 则对wac进行赋值
      wac = this.webApplicationContext;
      if (wac instanceof ConfigurableWebApplicationContext) {
        ConfigurableWebApplicationContext cwac = (ConfigurableWebApplicationContext) wac;
        if (!cwac.isActive()) {
          // The context has not yet been refreshed -> provide services such as
          // setting the parent context, setting the application context id, etc
          if (cwac.getParent() == null) {
            // The context instance was injected without an explicit parent -> set
            // the root application context (if any; may be null) as the parent
            cwac.setParent(rootContext);
          }
          configureAndRefreshWebApplicationContext(cwac);
        }
      }
    }
    if (wac == null) {
      // wac保持空, 则先查找是否有spring父容器, 有则直接使用
      wac = findWebApplicationContext();
    }
    if (wac == null) {
      // wac仍然保持空, 则创建IOC容器, 过程详见b
      wac = createWebApplicationContext(rootContext);
    }

    if (!this.refreshEventReceived) {
      // Either the context is not a ConfigurableApplicationContext with refresh
      // support or the context injected at construction time had already been
      // refreshed -> trigger initial onRefresh manually here.
      synchronized (this.onRefreshMonitor) {
        // 创建完IOC容器了, 进行刷新, 详见c
        onRefresh(wac);
      }
    }

    if (this.publishContext) {
      // Publish the context as a servlet context attribute.
      // 将IOC容器在Tomcat应用域共享
      String attrName = getServletContextAttributeName();
      getServletContext().setAttribute(attrName, wac);
    }

    return wac;
  }
}

```

### b>创建IOC容器WebApplicationContext

所在类：org.springframework.web.servlet.FrameworkServlet

```java
class FrameworkServlet {
  protected WebApplicationContext createWebApplicationContext(@Nullable ApplicationContext parent) {
    Class<?> contextClass = getContextClass();
    if (!ConfigurableWebApplicationContext.class.isAssignableFrom(contextClass)) {
      throw new ApplicationContextException(
              "Fatal initialization error in servlet with name '" + getServletName() +
                      "': custom WebApplicationContext class [" + contextClass.getName() +
                      "] is not of type ConfigurableWebApplicationContext");
    }
    // 通过反射创建 IOC 容器对象
    ConfigurableWebApplicationContext wac =
            (ConfigurableWebApplicationContext) BeanUtils.instantiateClass(contextClass);

    wac.setEnvironment(getEnvironment());
    // 设置父容器
    // 为什么要设置父容器? 因为大多数时候, spring框架和springMVC框架需要独立的两套IOC容器, 而不是共用一套IOC容器
    // 那么, ApplicationContext parent 就是spring框架的IOC容器, 它作为springMVC IOC容器WebApplicationContext的父容器
    wac.setParent(parent);
    String configLocation = getContextConfigLocation();
    if (configLocation != null) {
      wac.setConfigLocation(configLocation);
    }
    configureAndRefreshWebApplicationContext(wac);

    return wac;
  }

}
```

### c>DispatcherServlet初始化策略

* 以上可知, FrameworkServlet创建WebApplicationContext后，刷新容器，调用onRefresh(wac)
* 此方法在DispatcherServlet中进行了重写，调用了initStrategies(context)方法
* 初始化策略，即初始化DispatcherServlet的各个组件

所在类：org.springframework.web.servlet.DispatcherServlet

```java
class DispatcherServlet {
  protected void initStrategies(ApplicationContext context) {
    initMultipartResolver(context); // 初始化文件上传解析器
    initThemeResolver(context); // 初始化主题解析器
    initHandlerMappings(context); // 初始化Controller映射
    initHandlerAdapters(context); // 初始化Controller适配器
    initHandlerExceptionResolvers(context); // 初始化异常处理器
    initViewResolvers(context); // 初始化视图解析器
    
    
    initLocaleResolver(context); // 初始化国际化解析器
    initRequestToViewNameTranslator(context); // 初始化请求转换器
    initFlashMapManager(context);
  }

}
```

## 3、DispatcherServlet调用Controller组件处理请求

在HttpServlet的doService()方法中, 根据不同的请求类型XXX, 调用不同的doXXX():  

* 如果是GET/POST/DELETE/PUT和除了PATCH以外的请求, 则分别在doXXX()中调用processRequest  
* 否则是PATCH请求或请求为null, 重新调用父类HttpServlet的doService()方法

### a>processRequest()

子类FrameworkServlet重写父类HttpServlet中的service()和doGet()/doPost()...  
这些方法都调用了processRequest(request, response)

所在类：org.springframework.web.servlet.FrameworkServlet

```java
class FrameworkServlet {
  protected final void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    long startTime = System.currentTimeMillis();
    Throwable failureCause = null;

    LocaleContext previousLocaleContext = LocaleContextHolder.getLocaleContext();
    LocaleContext localeContext = buildLocaleContext(request);

    RequestAttributes previousAttributes = RequestContextHolder.getRequestAttributes();
    ServletRequestAttributes requestAttributes = buildRequestAttributes(request, response, previousAttributes);

    WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);
    asyncManager.registerCallableInterceptor(FrameworkServlet.class.getName(), new RequestBindingInterceptor());

    initContextHolders(request, localeContext, requestAttributes);

    try {
      // 执行服务，doService()是一个抽象方法，在子类DispatcherServlet中进行了重写
      doService(request, response);
    }
    catch (ServletException | IOException ex) {
      failureCause = ex;
      throw ex;
    }
    catch (Throwable ex) {
      failureCause = ex;
      throw new NestedServletException("Request processing failed", ex);
    }

    finally {
      resetContextHolders(request, previousLocaleContext, previousAttributes);
      if (requestAttributes != null) {
        requestAttributes.requestCompleted();
      }
      logResult(request, response, failureCause, asyncManager);
      publishRequestHandledEvent(request, response, startTime, failureCause);
    }
  }
}

```

### b>doService()

所在类：org.springframework.web.servlet.DispatcherServlet

```java
class DispatcherServlet {
  @Override
  protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
    logRequest(request);

    // Keep a snapshot of the request attributes in case of an include,
    // to be able to restore the original attributes after the include.
    Map<String, Object> attributesSnapshot = null;
    if (WebUtils.isIncludeRequest(request)) {
      attributesSnapshot = new HashMap<>();
      Enumeration<?> attrNames = request.getAttributeNames();
      while (attrNames.hasMoreElements()) {
        String attrName = (String) attrNames.nextElement();
        if (this.cleanupAfterInclude || attrName.startsWith(DEFAULT_STRATEGIES_PREFIX)) {
          attributesSnapshot.put(attrName, request.getAttribute(attrName));
        }
      }
    }

    // Make framework objects available to handlers and view objects.
    request.setAttribute(WEB_APPLICATION_CONTEXT_ATTRIBUTE, getWebApplicationContext());
    request.setAttribute(LOCALE_RESOLVER_ATTRIBUTE, this.localeResolver);
    request.setAttribute(THEME_RESOLVER_ATTRIBUTE, this.themeResolver);
    request.setAttribute(THEME_SOURCE_ATTRIBUTE, getThemeSource());

    if (this.flashMapManager != null) {
      FlashMap inputFlashMap = this.flashMapManager.retrieveAndUpdate(request, response);
      if (inputFlashMap != null) {
        request.setAttribute(INPUT_FLASH_MAP_ATTRIBUTE, Collections.unmodifiableMap(inputFlashMap));
      }
      request.setAttribute(OUTPUT_FLASH_MAP_ATTRIBUTE, new FlashMap());
      request.setAttribute(FLASH_MAP_MANAGER_ATTRIBUTE, this.flashMapManager);
    }

    RequestPath requestPath = null;
    if (this.parseRequestPath && !ServletRequestPathUtils.hasParsedRequestPath(request)) {
      requestPath = ServletRequestPathUtils.parseAndCache(request);
    }

    try {
      // 处理请求和响应
      doDispatch(request, response);
    }
    finally {
      if (!WebAsyncUtils.getAsyncManager(request).isConcurrentHandlingStarted()) {
        // Restore the original attribute snapshot, in case of an include.
        if (attributesSnapshot != null) {
          restoreAttributesAfterInclude(request, attributesSnapshot);
        }
      }
      if (requestPath != null) {
        ServletRequestPathUtils.clearParsedRequestPath(request);
      }
    }
  }

}
```

### c>doDispatch()

所在类：org.springframework.web.servlet.DispatcherServlet

```java
class DispatcherServlet {
  protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
    HttpServletRequest processedRequest = request;
    HandlerExecutionChain mappedHandler = null;
    boolean multipartRequestParsed = false;

    WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);

    try {
      ModelAndView mv = null;
      Exception dispatchException = null;

      try {
        processedRequest = checkMultipart(request);
        multipartRequestParsed = (processedRequest != request);

        // Determine handler for the current request.
            /*
             mappedHandler：调用链
                包含handler、interceptorList、interceptorIndex
             handler：浏览器发送的请求所匹配的控制器方法
             interceptorList：处理控制器方法的所有拦截器集合
             interceptorIndex：拦截器索引，控制拦截器afterCompletion()的执行
            */
        mappedHandler = getHandler(processedRequest);
        if (mappedHandler == null) {
          noHandlerFound(processedRequest, response);
          return;
        }

        // Determine handler adapter for the current request.
        // 通过控制器方法创建相应的处理器适配器，调用所对应的控制器方法
        HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());

        // Process last-modified header, if supported by the handler.
        String method = request.getMethod();
        boolean isGet = "GET".equals(method);
        if (isGet || "HEAD".equals(method)) {
          long lastModified = ha.getLastModified(request, mappedHandler.getHandler());
          if (new ServletWebRequest(request, response).checkNotModified(lastModified) && isGet) {
            return;
          }
        }

        // 调用拦截器的preHandle()
        if (!mappedHandler.applyPreHandle(processedRequest, response)) {
          return;
        }

        // Actually invoke the handler.
        // 由处理器适配器ha调用具体的Controller方法，最终获得ModelAndView对象
        // 适配器模式: 将多个种类的Controller方法的调用统一成处理器适配器的调用
        // 每一种Controller方法都对应一个处理器适配器, 比如
        // RequestMapping -- RequestMappingHandlerAdapter 
        // GetMapping -- SimpleControllerHandlerAdapter
        // 而这些适配器又实现了统一的HandlerAdapter接口, 通过多态实现了统一调用
        
        // 同时, ha还进行了其他处理, 如向Controller方法传递参数, 进行参数类型转换等
        mv = ha.handle(processedRequest, response, mappedHandler.getHandler());

        if (asyncManager.isConcurrentHandlingStarted()) {
          return;
        }

        applyDefaultViewName(processedRequest, mv);
        // 调用拦截器的postHandle()
        mappedHandler.applyPostHandle(processedRequest, response, mv);
      }
      catch (Exception ex) {
        dispatchException = ex;
      }
      catch (Throwable err) {
        // As of 4.3, we're processing Errors thrown from handler methods as well,
        // making them available for @ExceptionHandler methods and other scenarios.
        dispatchException = new NestedServletException("Handler dispatch failed", err);
      }
      // 后续处理：处理模型数据和渲染视图
      processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchException);
    }
    catch (Exception ex) {
      triggerAfterCompletion(processedRequest, response, mappedHandler, ex);
    }
    catch (Throwable err) {
      triggerAfterCompletion(processedRequest, response, mappedHandler,
              new NestedServletException("Handler processing failed", err));
    }
    finally {
      if (asyncManager.isConcurrentHandlingStarted()) {
        // Instead of postHandle and afterCompletion
        if (mappedHandler != null) {
          mappedHandler.applyAfterConcurrentHandlingStarted(processedRequest, response);
        }
      }
      else {
        // Clean up any resources used by a multipart request.
        if (multipartRequestParsed) {
          cleanupMultipart(processedRequest);
        }
      }
    }
  }

}
```

### d>processDispatchResult()渲染视图

```java
class DispatcherServlet {

  private void processDispatchResult(HttpServletRequest request, HttpServletResponse response,
                                     @Nullable HandlerExecutionChain mappedHandler, @Nullable ModelAndView mv,
                                     @Nullable Exception exception) throws Exception {

    boolean errorView = false;

    // 如果执行controller方法过程发生了异常, 则进行处理
    if (exception != null) {
      if (exception instanceof ModelAndViewDefiningException) {
        logger.debug("ModelAndViewDefiningException encountered", exception);
        mv = ((ModelAndViewDefiningException) exception).getModelAndView();
      }
      else {
        Object handler = (mappedHandler != null ? mappedHandler.getHandler() : null);
        mv = processHandlerException(request, response, handler, exception);
        errorView = (mv != null);
      }
    }

    // Did the handler return a view to render?
    if (mv != null && !mv.wasCleared()) {
      // 处理模型数据和渲染视图
      render(mv, request, response);
      if (errorView) {
        WebUtils.clearErrorRequestAttributes(request);
      }
    }
    else {
      if (logger.isTraceEnabled()) {
        logger.trace("No view rendering, null ModelAndView returned.");
      }
    }

    if (WebAsyncUtils.getAsyncManager(request).isConcurrentHandlingStarted()) {
      // Concurrent handling started during a forward
      return;
    }

    if (mappedHandler != null) {
      // Exception (if any) is already handled..
      // 调用拦截器的afterCompletion()
      mappedHandler.triggerAfterCompletion(request, response, null);
    }
  }
}
```

## 4、SpringMVC的执行流程

1) 用户向服务器发送请求，请求被SpringMVC 前端控制器 DispatcherServlet捕获。

2) DispatcherServlet对请求URL进行解析，得到请求资源标识符（URI），判断请求URI对应的映射：

    * 假如不存在, 则判断是否配置了mvc:default-servlet-handler

      * 如果没配置，则控制台报映射查找不到，客户端展示404错误

      * 如果有配置，则访问目标资源（一般为静态资源，如：JS,CSS,HTML），找不到客户端也会展示404错误

    * 假如存在则执行下面的流程

      * 根据该URI，调用HandlerMapping获得该Handler配置的所有相关的对象（包括Handler对象以及Handler对象对应的拦截器），最后以HandlerExecutionChain执行链对象的形式返回。

      * DispatcherServlet 根据获得的Handler，选择一个合适的HandlerAdapter。

      * 如果成功获得HandlerAdapter，此时将开始执行拦截器的preHandler(…)方法【正向】

      * 提取Request中的模型数据，填充Handler入参，开始执行Handler（Controller)方法，处理请求。在填充Handler的入参过程中，根据你的配置，Spring将帮你做一些额外的工作：

        * HttpMessageConverter： 将请求消息（如Json、xml等数据）转换成一个对象，将对象转换为指定的响应信息

        * 数据转换：对请求消息进行数据转换。如String转换成Integer、Double等

        * 数据格式化：对请求消息进行数据格式化。 如将字符串转换成格式化数字或格式化日期等

        * 数据验证： 验证数据的有效性（长度、格式等），验证结果存储到BindingResult或Error中

3) Handler执行完成后，向DispatcherServlet 返回一个ModelAndView对象。

4) 此时将开始执行拦截器的postHandle(...)方法【逆向】。

5) 根据返回的ModelAndView（此时会判断是否存在异常：如果存在异常，则执行HandlerExceptionResolver进行异常处理）选择一个适合的ViewResolver(Thymeleaf/forward/redirect)进行视图解析，根据Model和View，来渲染视图。

6) 渲染视图完毕执行拦截器的afterCompletion(…)方法【逆向】。

7) 将渲染结果返回给客户端。
