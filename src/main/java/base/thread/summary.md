#### 线程的创建
- Thread（）
- Thread（String name)
- Thread（Runnable target)
- Thread(Runnable target String name)

#### 线程的方法
- void start()

 ``` java
static void sleep(long millis)
static void sleep(long millis, int nanos)
```
``` java
 /** 使其他线程等待当前线程终止 **/
 void join()
 void join(long millis)
 void join(long millis, int nanos)
```

```java
/** 当前运行线程释放处理器资源 **/
static void yield()
```

```java
/** 返回当前线程的引用 **/
static Thread currentThread()
```

### 线程池
- ExecutorService 线程池接口
- ThreadPoolExecutor 是ExecutorService的默认实现
``` java

ThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue,
 ThreadFactory threadFactory, RejectedExecutionHandler handler)
```
- corePoolSize：核心池的大小，这个参数跟后面讲述的线程池的实现原理有非常大的关系。在创建了线程池后，默认情况下，线程池中并没有任何线程，而是等待有任务到来才创建线程去执行任务，除非调用了prestartAllCoreThreads()或者prestartCoreThread()方法，从这2个方法的名字就可以看出，是预创建线程的意思，即在没有任务到来之前就创建corePoolSize个线程或者一个线程。默认情况下，在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中；
- maximumPoolSize：线程池最大线程数，这个参数也是一个非常重要的参数，它表示在线程池中最多能创建多少个线程；
- keepAliveTime：表示线程没有任务执行时最多保持多久时间会终止。默认情况下，只有当线程池中的线程数大于corePoolSize时，keepAliveTime才会起作用，直到线程池中的线程数不大于corePoolSize，即当线程池中的线程数大于corePoolSize时，如果一个线程空闲的时间达到keepAliveTime，则会终止，直到线程池中的线程数不超过corePoolSize。但是如果调用了allowCoreThreadTimeOut(boolean)方法，在线程池中的线程数不大于corePoolSize时，keepAliveTime参数也会起作用，直到线程池中的线程数为0；
- unit：参数keepAliveTime的时间单位，有7种取值，在TimeUnit类中有7种静态属性：
- workQueue：一个阻塞队列，用来存储等待执行的任务，这个参数的选择也很重要，会对线程池的运行过程产生重大影响，一般来说，这里的阻塞队列有以下几种选择：
```java
ArrayBlockingQueue;
LinkedBlockingQueue;
SynchronousQueue;
```
- threadFactory：线程工厂，主要用来创建线程；
- handler：表示当拒绝处理任务时的策略，有以下四种取值：
- execute()：执行任务
- submit()：执行任务并返回结果
- shutdown()：关闭线程池 则线程池处于SHUTDOWN状态，此时线程池不能够接受新的任务，它会等待所有任务执行完毕；
- shutdownNow()：关闭线程池 则线程池处于STOP状态，此时线程池不能接受新的任务，并且会去尝试终止正在执行的任务；
- 当线程池处于SHUTDOWN或STOP状态，并且所有工作线程已经销毁，任务缓存队列已经清空或执行结束后，线程池被设置为TERMINATED状态。

### Callable&Future
- 任务是callable，重写call方法
- 把任务放到futuretask异步获取结果
- 用ExecutorService创建线程池，吧future放进去

### 同步 (TraditionalThreadCommunication)
- `wait()` 这是Object的成员方法，每个对象都有这个方法，wait（）的线程会释放锁，然后进入阻塞状态
进入waitset，每个对象都有这个waitset，在方法中wait的时候是线程在当前这个指令被阻塞，如果它重新拿到锁
他会继续往下执行
- `notify()` 唤醒waitset的某个线程
- `notifyAll()` 唤醒waitset的所有线程

### synchronized

- 当两个并发线程访问同一个对象object中的这个synchronized(this)同步代码块时，一个时间内只能有一个线程得到执行。另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块。
- 然而，当一个线程访问object的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该object中的非synchronized(this)同步代码块。
- 尤其关键的是，当一个线程访问object的一个synchronized(this)同步代码块时，其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞。
- 第三个例子同样适用其它同步代码块。也就是说，当一个线程访问object的一个synchronized(this)同步代码块时，它就获得了这个object的对象锁。结果，其它线程对该object对象所有同步代码部分的访问都被暂时阻塞。

### volatile ：
- 网上基本上对violate的解释都是内存可见性，因为每个Thread都有属于它们自己的内存空间，这个线程做了对某些属性的更新操作，另一个线程不一定是看得到的。这个时候就是violate这个的作用了。
- 每次修改volatile变量都会同步到主存中
  每次读取volatile变量的值都强制从主存读取最新的值(强制JVM不可优化volatile变量,如JVM优化后变量读取会使用cpu缓存而不从主存中读取)
### lock
- 可以设置占有锁多久
- 可以读读操作
- 可以知道是否获取了锁
- 需要手动释放，发生异常时不会自动释放
___
### problem

#### Thread.sleep和yield的区别：<br/>

1. yield方法会暂停当前正在执行的线程，让给有同样优先级或者更高优先级的线程执行，如果没有符合条件的线程
那么啥都不做 （让出了cpu的线程进入了就绪状态）
2. sleep方法会暂停当前正在执行的线程，让出cpu使用权（让出了cpu的线程进入了阻塞状态）
___

#### Thread和Runable的联系和区别（设么时候改用什么）：<br/>
1. 在Java中实现多线程无非两种方式，继承Thread类和实现Runnabel接口
2. Runnable接口实现的线程便于资源共享，而通过Thread类实现，各自的线程的资源是独立的，不方便共享。
可以看TicketRunnable和TicketThread
___

#### corePoolSize和maximumPoolSize的区别：<br/>
1. 当任务来了，那么创建线程执行，如果线程数到达corePollSize那么新任务就会进入等待队列。如果队列满了还有新
任务进来，或者这个新任务必须马上执行，那么又会去创建线程，但是数量不能超过maxmumPoolSize，如果还是不行那估计要
报异常了 RejectedExecutionException
2. 当线程数少于corePoolSize，那么有新任务进来，他会取创建新线程执行该任务，就算现在池子里有空闲的线程

___

#### start和run的关系：<br/>
1. run()并不是启动线程，而是简单的方法调用。
2. 并不是一启动线程（调用start()方法）就执行这个线程，而是进入就绪状态，什么时候运行要看CUP
___

#### Thread.sleep是指当前线程睡眠还是所有线程睡眠: <br/>
1. 当前线程进行睡眠，正在运行的线程睡觉了
2. 但是到了sleep睡完，也不一定是他获得线程而是看当时的优先级
3. sleep(0)的作用就是触发操作系统立刻重新进行一次cpu竞争，给别得线程一次机会
___



#### 多个线程数据如何不交叉：
1. threadlocal

___

#### 多个线程数据如何同步保证一致性：
1. 线程执行代码相同，使用同一Runnable对象，Runnable对象中有共享数据
2. 线程执行代码不同，将共享数据封装在另一对象中（操作数据的方法也在该对象完成），将这个对象逐一传递给各个Runnable对象。[本质：共享数据的对象作为参数传入Runnable对象]
3. 线程执行代码不同，将Runnable对象作为某一个类的内部类，共享数据作为这个外部类的成员变量（操作数据的方法放在外部类）。[本质:不同内部类共享外部类数据]
4. 结合上两种方式，将共享数据封装在另一对象中（操作数据的方法也在该对象完成），该对象作为这个外部类的成员变量，将Runnable对象作为内部类
___

#### lock中被中断的线程该去往何处：
1. 中断会抛出InterruptedException异常，线程就走异常路线，走完了就回线程池了




