
# Redis数据类型

Redis命令不区分大小写, 但存储内容区分大小写

* string
  * 底层结构`Map<String, Object>`
  * set/get, mset/mget, strlen
  * 商品编号, 订单号等, 使用incr key生成递增的value
  * 点赞数, 关注数, 粉丝数等
* list
  * 底层结构`List<Map<String, Object>>`
  * lrange, lpush, lpop, llen
  * 微信公众号列表
* set
  * 底层结构`Set<Map<String, Object>>`
  * sadd, srem, smembers, scard(inality)
  * 随机弹出元素: srandmember(保留), spop(不保留)
  * 集合运算: sdiff, sinter, sunion
  * 抽奖(一人抽一次): 参与(sadd), 总人数(scard), 抽奖(spop)
  * 点赞(一人赞一次): sadd, srem, smembers, scard(inality)
  * 朋友圈共同好友sinter, 用户推荐sdiff
* zset
  * 底层结构`LinkedSkipListSet<Map<String, Object>>`
  * zadd, zrange, zscore, zrem, zcard(inality), zrangebyscore, zcount, zincrby
  * 排行榜, 热搜
* hash
  * 底层结构`Map<String, Map<Object, Object>>`
  * hset/hget, hmset/hmget, hgetall, hlen, hdel
  * 访问量不大的app: 新增商品hset, 增加商品数量hincrby, 删除商品hdel, 商品总数hlen, 商品列表hgetall
* hyperloglog
* stream
* bitmap
* geospatial
* bloomfilter
