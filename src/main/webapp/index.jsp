<%--
  Created by IntelliJ IDEA.
  User: imsti
  Date: 15.02.2018
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Main</title>
  </head>
  <body>
  <a href="/main" methods="GET">Просмотр всех Тегов</a>
  <br/>
  <%--<a href="/main" methods="PUT">Обновление тега</a>--%>
  <%--<br/>--%>

  <form action="/main" method="PUT">
      <h1>Обновление тега</h1>
      <h3>id тега</h3>
      <input type="text" value="" name="tagId"/>
      <h3>наименование</h3>
      <h1><input type="text" value="" name="tagName"/></h1>
      <br/>
      <input type="submit" value="send"/>
  </form>

  $END$
  </body>
</html>
