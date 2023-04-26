
# Java8新特性

1. 接口：引入default默认方法、static静态方法，两者都可有方法体
2. 日期：LocalDateTime，解决Date和Calendar的遗留问题
3. Collection：底层Object[]创建时间延迟到new对象以后；Map引入红黑树
4. Lambda、Stream、方法引用、函数式接口、Optional类

# cleancode with Java8

1. 如果lambda表达式的方法体过多，抽取成方法，并进行方法引用::，抽取出来的方法可以放在
    * 同一个类
    * 工具类
2. 对于可能为null的参数T, 使用Optional\<T>包装，并使用.stream().map().orElseGet(()->{...})调用
3. 对于可能返回null的方法，应抛出异常取代之，或返回Optional\<T>. 即使对getter方法也如此，如客户依赖于信用卡，但某客户可能未办卡
4. loan pattern：把业务方法转移给另一个对象，让另一个对象负责处理，可以解耦业务逻辑与基础方法，方便分开测试
    * export.exportFile("filename", xxxWriter::write);
    * jdbcTemplate.query("select * from tbl", rowMapper::mapRow);
5. 尽量抛出RuntimeException，非RuntimeException的捕捉并抛出RuntimeException(如lombok的@SneakyThrows)
6. 成员枚举类 + Function实现方法多态
