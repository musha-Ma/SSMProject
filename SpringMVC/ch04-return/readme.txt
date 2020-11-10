关于控制器方法的返回值
1、返回值为ModeAndView（表示视图和数据）
    ModeAndView即可以使用ModeAndView.addObject(key,value)向服务器作用域添加数据，又可以使用
    ModeAndView.setViewName(value)实现页面的跳转（内部是请求转发方式进行的跳转）
2、返回值为String（表示视图）
    String表示返回值是视图，即页面的跳转，若想在页面跳转的同时携带数据，必须手动添加数据，即
    request.setAttribute(key,value)添加数据
3、返回值为void

4、返回值为String（表示数据）

