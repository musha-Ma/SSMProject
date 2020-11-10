当DispacherServlet的url-pattern为"/"时静态资源的访问问题
1、我们现在的中央调度器的url-pattern都是为"*.do"这样的，当发出的请求匹配url-pattern时会调用相应的
   servlet来处理；那些不匹配的请求会交由tomcat内置的默认servlet这个类来处理（例如静态资源css、jsp等）
   但是当中央调度器的url-pattern为"/"时（表示拦截一切），此时的servlet的默认servlet就被替代了，
   中央调度器默认是没有处理静态资源的能力，所以会出现静态资源找不到的问题。

2、 解决方案：
    方案1、在springmvc配置文件中加入标签<mvc:default-servlet-handler>
          原理：再加入这个标签时，在创建springmvc容器时，会创建一个DefaultServletHttpRequestHandler对象
               该对象会将所有的静态资源的访问交由tomcat内置的defaultservlet来处理（内部是请求转发形式）
          注意：该方式会将所有的请求交由tomcat默认servlet去处理，会覆盖@RequestMapping的请求，需要加入
               注解驱动标签

    方案2、

