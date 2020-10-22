#think-generator

[![Build Status](https://travis-ci.org/thhink-project/think-framework.svg?branch=develop)](https://travis-ci.org/hdhxby/think-framework)
[![Sonar Quality Gate](https://img.shields.io/sonar/https/sonarcloud.io/think-framework/quality_gate.svg)](https://sonarcloud.io/dashboard?id=think-framework)
[![Sonar Coverage](https://img.shields.io/sonar/https/sonarcloud.io/think-framework/coverage.svg)](https://sonarcloud.io/dashboard?id=think-framework)
基于xml的配置

设计模式:

| 序号   |      名称      |  实现 |
|----------|:-------------:|------:|
| 1 |  Factory Method（工厂方法）| CommandFactory |
| 2 |  Abstract Factory（抽象工厂） |    |
| 3 | Builder（建造者）|    GeneratorProperties |
| 4 |  Prototype（原型） | GeneratorProperties |
| 5 | Singleton（单例） |    |
|  | | |
| 6 |    Adapter Class/Object（适配器）   |    |
| 7 | Bridge（桥接） |   @think-jdbc |
| 8 |  Composite（组合） |  |
| 9 |    Decorator（装饰）   | RemakrsDecroate   |
| 10 |  Facade（外观） | GeneratorFacade |
| 11 |  Flyweight（享元） |  |
| 12 |    Proxy（代理）   |   RemarksInvocationHandler |
| | | | 
| 13 | Interpreter（解释器） |     |
| 14 |  Template Method（模板方法） |  |
| 15 |    Chain of Responsibility（责任链）   |    |
| 16 |  Command（命令） | TableGeneratorCommand |
| 17 |    Iterator（迭代器）   |    |
| 18 | Mediator（中介者） |    GeneratorContext |
| 19 |  Memento（备忘录） |  |
| 20 |    Observer（观察者）   |    |
| 21 |    State（状态）   |    |
| 22 |    Strategy（策略）   |  GeneratorStrategy  |
| 23 |    Visitor（访问者）   |    |
