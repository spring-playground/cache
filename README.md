# cache
Experimenting with Spring Caching Mechanisms

_Base components taken from:_ https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-cache/src/main/resources 

**Run:**

gradle -Dlocal.spring.cache.type=ehcache bootRun

From Windows:
gradlew.bat -Dlocal.spring.cache.type=ehcache build
gradlew.bat -Dlocal.spring.cache.type=ehcache bootRun

**Notes:**

The Spring Boot starter provides a simple cache provider which stores values in an instance of ConcurrentHashMap. This is the simplest possible thread-safe implementation of the caching mechanism.

If the @EnableCaching annotation is present in your app, Spring Boot checks dependencies available on your class path and configures an appropriate CacheManager. Depending on a chosen provider, some additional configuration may be required. You can find all information about configuration in the first link from this answer.

Supported Cache Providers: https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-caching.html

The cache abstraction does not provide an actual store and relies on abstraction materialized by the org.springframework.cache.Cache andorg.springframework.cache.CacheManager interfaces.
If you haven’t defined a bean of type CacheManager or a CacheResolver named cacheResolver (see CachingConfigurer), Spring Boot tries to detect the following providers (in this order):
•	Generic
•	JCache (JSR-107) (EhCache 3, Hazelcast, Infinispan, etc)
•	EhCache 2.x
•	Hazelcast
•	Infinispan
•	Couchbase
•	Redis
•	Caffeine
•	Guava (deprecated)
•	Simple

**Spring Annotations**

**@Cacheable** - Demarcates cacheable methods, can read and write to the cache – only caches when you have a cache miss (not in cache already). 

Key Setting:
1)	Key is set based on the incoming parameters to the annotated method. When no parameters incoming, it adds SimpleKey.EMPTY. We can also implement KeyGenerator interface.
2)	Use SpEL in Caching annotations e.g. @Cacheable(“name”, key=”#something.id”) -> can also use condition/unless from Spring 4.x onwards
3)	You can set a keyGenerator class to generate the unique key.

**@CacheEvict** - Demarcates methods that perform cache eviction, that is methods that act as triggers for removing data from the cache
**@CachePut** - Updates the cache with the annotated method's return value. Will always execute the method
**@Caching** - Allows multiple nested @Cacheable, @CacheEvict and @CachePut annotations to be used on the same method
**@CacheConfig** - Class-level annotation that allows to share the cache names, the custom KeyGenerator, the custom CacheManager, and finally the custom CacheResolver. Does not enable caching.

You can have and use multiple Cache Managers – use cacheManager, cacheResolver in the annotations. Preference is to use a cacheResolver so you can centralize.

Spring 4.x is the first framework to fully support JCache – note there are some difference between Spring and JCache annotations:
JCache (JSR 107) is a standard caching API for Java. It provides an API for applications to be able to create and work with in-memory cache of objects
To enable JCache you just have to add the dependencies in the class path.
