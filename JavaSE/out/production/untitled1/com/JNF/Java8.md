# Java8新特性

1. 接口：引入default默认方法、static静态方法，两者都可有方法体
2. 日期：LocalDateTime，解决Date和Calendar的遗留问题
3. Collection：底层Object[]创建时间延迟到new对象以后；Map引入红黑树
4. Lambda、Stream、方法引用、函数式接口、Optional类

# CleanCode with Java8
1. 将boilerplate的lambda表达式替换为函数式接口, 这些接口可以放在
    * 同一个类
    * 方法类
2. 可能为null的参数类型T, 应使用Optional\<T\>包装, 并使用stream().map().orElseGet()做业务处理
3. 可能返回null的方法, 应抛异常, 或返回Optional\<T\>\(适用于getter方法, 如customer.getCreditCard(), 顾客可能没有信用卡)
4. monad的操作, 应使用flatMap()
5. 尽量抛出RuntimeException, 对Exception，应捕捉后抛出RuntimeException(lombok使用@SneakyThrows实现)
6. 使用loan模式将基础模板代码和业务代码解耦，便于分开测试:
    * exporter.exportFile("fileName.format", xxxWriter::writer)
    * jdbcTemplate.query("select * from tbl", xxxRowMapper::mapRow)