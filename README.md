sample-application-using-uid-generator-by-baidu
===

Sample application (based Spring Boot with Kotlin) using uid-generator by Baidu

What is this?
---

Sometimes, you need an Unique ID in your system, don't you.

This sample application is maybe useful for you when that.

Overview
---

this is one way, for generate unique id in your system.

I think this is easy way if your application based on Spring Boot with Kotlin.

First this sample is using \`[uid-generator](https://github.com/baidu/uid-generator)\` by Baidu.

Because this library is so easy for us when using RDB.

It's only need one table on your database.

In addition, this library is `snowflake` like.

It's strong and easy to customize.

In this time, set each value of three fields with reference to \`[sonyflake](https://github.com/sony/sonyflake)\`.

```
delta seconds (time in units): 39bits
worker id (machine id): 16 bits
sequence: 8bits
```

Because it can meet the requirements in many cases.
