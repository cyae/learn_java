# IO

IO泛指使用字符流(按编码方式utf-8\GBK, 文本文件)或字节流(8bit, 二进制文件)的形式，输入或输出内存中的文件。

输入输出方可以是另一个文件，磁盘，网络，数据库。。。

## 修饰器设计模式

包装流 BufferedReader/BufferedWriter 继承了 Reader/Writer 抽象类，并且将 Reader/Writer 作为成员传入构造器，因此可以使用 Reader/Writer 的任意子类节点流(fileReader, charArrayReader, PipedReader, StringReader...)传入构造器，统一操作。

BufferedInputStream/BufferedOutputStream 同理。

包装流本身不接触数据源，仅对传入的节点流进行修饰

## 序列化 反序列化

实际应用中，不仅需要保存值，还需要保存其数据类型。

比如1.txt中有100，是int?String?char[]?

也需要保存自定义类型Dog(123, "name")。

这就需要io时保存值和类型，并根据值和类型还原自定义类型

这些类及其成员应该实现serializable或externalizable接口, 读写顺序一致, static/transient成员不序列化
