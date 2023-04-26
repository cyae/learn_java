# Spring IOC注解

## 创建bean对象
开启注解扫描后，使用以下注解修饰实现类（不能修饰接口，注解无法被实现类继承）：
* @Component
* @Controller
* @Service
* @Repository

## bean属性注入
* 注入对象
    * @Autowired 根据属性类型自动注入, 最多修饰一个接口实现类
    * @Qualifier("实现类名") 根据属性名称自动注入，可修饰多个接口实现类, 必须和@Autowired联合使用
    * @Resource 以上两者都可:
      * @Resource = @Autowired
      * @Resource(name = "实现类名") = @Qualifier("实现类名")
      * 此注解由jdk提供，在jdk11后由javax包转移至jakarta包，因此可能产生冲突，慎用
* 注入普通属性
    * @Value("值")

## 纯注解开发(取消xml配置)
现在xml文件里还剩注解扫描配置，springBoot使用：
* @Configuration
* @ComponentScan