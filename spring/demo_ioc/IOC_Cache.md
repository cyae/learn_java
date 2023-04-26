
# IOC循环依赖

* 通过构造器方法进行依赖注入, 如果存在循环依赖, 则一定无法避免
* 通过setter方法进行依赖注入, 如果存在循环依赖
  * 如果bean创建方式是单例的, 则通过三级缓存避免
  * 如果bean创建方式是原型的, 则无法避免

* 相关问题
  * 为什么使用3级?2级1级可以吗?
  * 可以关闭3级缓存吗?
  * 多例的创建流程

# IOC三级缓存

![image](assets/%E4%B8%89%E7%BA%A7%E7%BC%93%E5%AD%98%E6%80%BB%E6%B5%81%E7%A8%8B.png)

* 只有单例bean才会通过三级缓存提前暴露, 原型bean每次都创建新的对象, 不会进三级缓存
* 假设AB互相依赖
  1. 创建A时, 需要依赖B, 依次查询123级缓存, 没找到B, 于是将A(的工厂bean, 是一个Supplier)迁移至3级缓存, 等待B创建
  2. 创建B时, 需要依赖A, 依次查询123级缓存, 在3级缓存找到A, 将其实例化并迁移至2级缓存, 并赋予给B的依赖属性, 此时B暂时位于3级缓存
  3. B实例化完成, 将其迁移至1级缓存
  4. 继续创建A, 依次查询123级缓存, 在1级缓存找到B, 赋予给A的依赖属性
  5. A实例化完成, 将其迁移至1级缓存

```java
class DefaultSingletonBeanRegistry {
  // 一级缓存, 即IOC容器, 存放已经初始化的bean对象
  Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

  // 二级缓存, 存放已经实例化, 但属性还未初始化的bean对象
  Map<String, Objcet> earlySingletonObjcts = new HashMap<>(16);

  // 三级缓存, 存放工厂bean, 其对应需要创建的bean对象还未实例化
  Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>(16);

  // 获取bean对象
  protected Object getSingleton(String beanName, boolean allowEarlyReference) {
    // 先从1级缓存中查找, this.singletonObjects = ConcurrentHashMap一级缓存
    Object singletonObject = this.singletonObjects.get(beanName);
    // 1级缓存未命中, 且若是正在创建bean, 则从23级缓存中查找
    if (singletonObject == null && isSingletonCurrentlyInCreation(beanName)) {
      synchronized (this.singletonObjects) {
        singletonObject = this.earlySingletonObjects.get(beanName);
        // 2级缓存未命中, 且允许提前引用, 则从3级缓存中查找
        if (singletonObject == null && allowEarlyReference) {
        ObjectFactory<?> singletonFactory = this.singletonFactories.get(beanName);
        // 3级缓存命中, 则将其实例化并迁移至2级缓存
        if (singletonFactory != null) {
          singletonObject = singletonFactory.getObject();
          this.earlySingletonObjcts.put(beanName, singletonObject);
          this.singletonFactories.remove(beanName);
        }
      }
    }

    // 1级缓存未命中, 且不是正在创建bean, 则返回null并执行doCreatBean()
    return singletonObject;
  }

  // 创建bean对象
  // bean模板在spring底层封装为RootBeanDefinition
  protected Object doCreateBean(String beanName, RootBeanDefinition mbd, Object[] args) {
    // 创建bean对象
    BeanWrapper instanceWrapper = null;
    // 如果是单例bean, 则在创建之前先清除已有同名bean对象
    if (mbd.isSingleton()) {
      instanceWrapper = this.createBeanInstanceCache.remove(beanName);
    }

    // 如果不存在同名bean, 则先实例化, 再包装类型
    // 如果已存在同名bean(上一步清理出来的), 则跳过实例化, 直接包装类型
    if (instanceWrapper == null) {
      // 通过反射调用构造器方法实例化bean对象
      instanceWrapper = createBeanInstance(beanName, mbd, args); 
    }

    // 包装类型
    Object bean = instanceWrapper.getWrappedInstance();
    Class<?> beanType = instanceWrapper.getWrappedClass();
    if (beanType != NullBean.class) {
      mbd.resolveTargetType = beanType;
    }
    return bean;
  }

  // 将待初始化bean的工厂迁移至3级缓存
  protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
    synchronized (this.singletonFactories) {
      if (!this.singletonObjects.containsKey(beanName)) {
        this.singletonFactories.put(beanName, singletonFactory);
        this.earlySingletonObjcts.remove(beanName);
        this.registeredSingletons.add(beanName);
      }
    }
  }

  // 初始化bean对象
  protected void populateBean(String beanName, RootBeanDefinition mbd, BeanWrapper bw) {
    // 获取bean的属性列表
    PropertyValues pvs = mbd.getPropertyValues();
    if (pvs != null && !pvs.isEmpty()) {
      // 注入属性
      // 在此方法applyPropertyValues中, 会遍历属性列表, 并从3级缓存找依赖属性的bean的工厂
      // 回调依赖属性的getSingletion()方法
      applyPropertyValues(beanName, mbd, bw, pvs);
    }
  }

  // 将实例化好的bean迁移至1级缓存
  protected void addSingleton(String beanName, Object singletonObject) {
    synchronized (this.singletonObjects) {
      this.singletonObjects.put(beanName, singletonObject);
      this.singletonFactories.remove(beanName);
      this.earlySingletonObjcts.remove(beanName);
      this.registeredSingletons.add(beanName);
    }
  }
}
```

![image](assets/%E6%80%BB%E7%BB%931.jpg)

![image](assets/%E6%80%BB%E7%BB%932.jpg)
