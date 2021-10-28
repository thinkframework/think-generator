#think-generator

[![Build Status](https://travis-ci.org/think-projects/think-generator.svg?branch=master)](https://travis-ci.org/think-projects/think-framework)
[![codecov](https://codecov.io/gh/think-projects/think-generator/branch/master/graph/badge.svg?token=OTIN6XE7J6)](https://codecov.io/gh/think-projects/think-generator)

基于spring-boot开发,仅依赖spring-boot-starter-freemarker,方便基于spring boot 1.x或者2.x切换
引入依赖,忽略spring-boot-starter-freemarker,引入自己的版本即可,不影响使用方spring boot版本

```xml

    <dependencies>
        <dependency>
            <groupId>io.github.thinkframework</groupId>
            <artifactId>generator-core</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-freemarker</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-freemarker</artifactId>
        </dependency>
    </dependencies>
```
