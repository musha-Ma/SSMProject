拦截器：
1、与servlet中的过滤器类似，只是二者的侧重点不同；过滤器处理的请求参数、比如设置字符编码；而拦截器
   用于对用户的请求进行预处理，常用与在验证用户是否已经登陆时非常有用（session）
2、实现过滤器（springmvc）
   1）实现springmvc中的HandlerInterceptor接口，重写三个方法
   2）在springmvc配置文件中注册拦截器对象