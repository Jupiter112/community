## Angel community 论坛系统

## 资料
[Spring 文档](https://spring.io/guides)  
[Spring Web](https://spring.io/guides/gs/serving-web-content/)  
[es](https://elasticsearch.cn/explore)  
[Github deploy key](https://docs.github.com/en/authentication/connecting-to-github-with-ssh/managing-deploy-keys#deploy-keys)  
[Bootstrap 文档](https://v3.bootcss.com/getting-started/)  
[Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)  
[Spring Boot](https://docs.spring.io/spring-boot/docs/3.1.1/reference/htmlsingle/)  
[Thymeleaf](https://www.thymeleaf.org/)

## 工具
[Git](https://git-scm.com/download)  
[Visual Paradigm](https://www.visual-paradigm.com/cn/)  
[Lombok](https://projectlombok.org/)  


## 脚本
```sql
create table user
(
    id int auto_increment primary key,
    account_id   varchar(100) null,
    name         varchar(50)  null,
    token        char(36)     null,
    gmt_create   bigint       null,
    gmt_modified bigint       null
);
```