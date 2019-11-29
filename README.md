#think-generator

基于xml的配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:jdbc="http://www.springframework.org/schema/jdbc"
       xmlns:generator="http://thinkframework.github.io/schema/generator"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
           http://www.springframework.org/schema/jdbc
           http://www.springframework.org/schema/jdbc/spring-jdbc-3.2.xsd
           http://thinkframework.github.io/schema/generator
           http://thinkframework.github.io/schema/generator/think-generator.xsd"
       default-lazy-init="true">
    <description>
        #代码生成器配置文件:
        #1.自动移除generator前缀
        #2.可以引用环境变量:System.getEnv(): ${env.JAVA_HOME} or System.getProperties(): ${user.home}
        #2.会为所有的property生成property_path属性,如pkg=org.think => pkg_path=org/think
    </description>

    <jdbc:embedded-database id="dataSource" type="H2">
        <jdbc:script location="schema-h2.sql"></jdbc:script>
    </jdbc:embedded-database>

    <bean id="app" class="com.alibaba.druid.pool.DruidDataSource" lazy-init="true">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/app"/>
        <property name="username" value="root"/>
        <property name="password" value=""/>
        <property name="connectProperties">
            <props>
                <prop key="useInformationSchema">true</prop>
            </props>
        </property>
    </bean>
    <!-- 监听器 -->
    <bean id="generatorListener" class="io.github.thinkframework.generator.listener.GeneratorListener"></bean>
    <!-- 数据提供者 -->
    <bean id="propertiesGeneratorProvider" class="io.github.thinkframework.generator.provider.PropertiesGeneratorProvider"></bean>

    <bean id="tableGeneratorProvider" class="io.github.thinkframework.generator.provider.TableGeneratorProvider"></bean>

    <generator:generator id="generator">
        <generator:generatorConfiguration>
            <!-- 数据源 -->
            <generator:dataSourceName>dataSource</generator:dataSourceName>
            <!-- 框架包的名称 -->
            <generator:frameName>com.glodon.bim5d</generator:frameName>
            <!--#公司名称-->
            <generator:companyName>com.glodon.bim5d</generator:companyName>
            <!--#应用名称-->
            <generator:appName>app</generator:appName>
            <!--#模块名称-->
            <!--<generator:moduleName>auth</generator:moduleName>-->
            <!--<generator:moduleName>param</generator:moduleName>-->
<!--            <generator:moduleName>costenterprise</generator:moduleName>-->
            <generator:moduleName>costproject</generator:moduleName>
            <!--#作者名称-->
            <!--<generator:authorName>lixiaobin</generator:authorName>-->
            <generator:authorName>unascribed</generator:authorName>
            <!--#jsp namespace: web/${namespace}/${className}/get:jsp-->
            <generator:namespace>views</generator:namespace>
            <!--#模板目录-->
<!--            <generator:template>template/glodon/enterprise</generator:template>-->
            <generator:template>template/glodon/project</generator:template>
            <!--#过滤文件-->
            <generator:extensions>
                <generator:list>
                    <generator:value>java</generator:value>
                    <generator:value>js</generator:value>
                    <generator:value>jsp</generator:value>
                    <generator:value>xml</generator:value>
                    <generator:value>ts</generator:value>
                    <generator:value>html</generator:value>
                    <generator:value>json</generator:value>
                    <generator:value>sql</generator:value>
                </generator:list>
            </generator:extensions>
            <generator:converts>
                <generator:map>
                    <generator:entity key="java.sql.Types.BIGINT" value="java.math.BigInteger"/>
<!--                    <generator:entity key="java.sql.Types.DATE" value="java.time.LocalDate"/>-->
<!--                    <generator:entity key="java.sql.Types.TIMESTAMP" value="java.time.Instant"/>-->
                </generator:map>
                <!--<generator:props>-->
                <!--<generator:prop key="java.sql.Types.DATE">java.time.LocalDate</generator:prop>-->
                <!--<generator:prop key="java.sql.Types.TIMESTAMP">java.time.Instant</generator:prop>-->
                <!--</generator:props>-->
            </generator:converts>
            <!--#需要移除的表名前缀,使用逗号进行分隔多个前缀,示例值: t_,tm_-->
            <generator:prefixs>
                <generator:list>
                    <generator:value>T_</generator:value>
                    <generator:value>RBAC_</generator:value>
                    <generator:value>PARAM_</generator:value>
                    <generator:value>AUTH_</generator:value>
                    <generator:value>PROJECT_</generator:value>
                    <generator:value>bim5d_cost_</generator:value>
                </generator:list>
            </generator:prefixs>
            <!--#需要移除的表名前缀,使用逗号进行分隔多个前缀,示例值: t_,tm_-->
            <generator:ignores>
                <generator:list>
                    <generator:value>sys_create_time</generator:value>
                    <generator:value>sys_modified_time</generator:value>
                    <generator:value>sys_revision</generator:value>
                    <generator:value>sys_creator_id</generator:value>
                    <generator:value>sys_modifier_id</generator:value>
                </generator:list>
            </generator:ignores>
            <!--#输出目录-->
            <generator:output>generator_output</generator:output>
            <!--#主键生成规则-->
            <!--<generator:generatorId>true</generator:generatorId>-->
            <!--#ID生成策略:NONE:不做处理,用字段名,ID:转换成ID-->
            <!--<generator:generatorIdStrategy>NONE</generator:generatorIdStrategy>-->
            <!--#JDBC对catalog和schema属性的获取支持不够-->
            <!--<generator:catalog></generator:catalog>-->
            <!--<generator:schema>THINK</generator:schema>-->
        </generator:generatorConfiguration>
    </generator:generator>
</beans>

```

数据库
```h2
DROP TABLE IF EXISTS TEST;
CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(255));
```

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
| 9 |    Decorator（装饰）   |    |
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
| 22 |    Strategy（策略）   |    |
| 23 |    Visitor（访问者）   |    |
