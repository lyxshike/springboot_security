# SpringSecurity做了2件事      用户认证和用户授权


#基于注解的: 
      
     @Secured("ROLE_VIP1")
     需要在主启动类上添加@EnableGlobalMethodSecurity(securedEnabled=true).
     
     @RolesAllowed("ROLE_VIP1")
     需要在主启动类上添加@EnableGlobalMethodSecurity(jsr250Enabled=true).
     
     
     @PreAuthorize("hasRole('ROLE_VIP1')") 用来在方法的调用前进行权限检查或者参数校验
     @PreAuthorize("#request.getParameter('str').length() > 2")
     需要在主启动类上添加@EnableGlobalMethodSecurity(prePostEnabled=true).
     
     @PostAuthorize("returnObject.length() > 2")
     
     @PostFilter("filterObject>2") 对集合类型的参数或返回值进行过滤，spring security将移除对应表达式的结果为false的元素
           filterObject 是使用@PreFilter, @PostFilter时的一个内置表达式，表示集合中的当前对象
     需要在主启动类上添加@EnableGlobalMethodSecurity(prePostEnabled=true).
     
     @PreFilter(filterTarget="ids", value="filterObject%2 == 0") 
          当PreFilter标注的方法拥有多个集合类型的参数时，需要通过filterTarget属性制定当前@PreFilter是针对哪个参数过滤的
          
     上面所有注解一起用，@EnableGlobalMethodSecurity(jsr250Enabled=true, securedEnabled=true, prePostEnabled=true)
     
     
#基于资源的应用保护

      1. 自己定义一个config类， 继承WebSecurityConfigurerAdapter
      2. 自定义的config类添加@EnableWebSecurity
      3. 重写用户授权的方法configure(HttpSecurity http)
      4. 重写用户认证的方法configure(AuthenticationManagerBuilder auth)
      
      
#JWT   先mark下，有机会了， 再来完成吧~~~      
