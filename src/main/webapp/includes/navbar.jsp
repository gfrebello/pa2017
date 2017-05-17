<%-- 
    Document   : navbar
    Created on : May 14, 2017, 10:09:08 PM
    Author     : gabriel
--%>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">CAD</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="/home">Copacabana</a></li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Casa<span class="caret"></span></a>
        <ul class="dropdown-menu">
            <c:forEach items="${dto.getDevices()}" var="device">
                ${device.getName()}<br>
            </c:forEach>
            <!--  <li><a href="#">Banheiro 1</a></li>
          <li><a href="#">Banheiro 2</a></li>
          <li><a href="#">Escritório</a></li>
          <li><a href="#">Quarto 1</a></li>
          <li><a href="#">Quarto 2</a></li>
          <li><a href="#">Sala</a></li>
          <li><a href="#">Suíte</a></li>
         -->
        </ul>
      </li>
      <li><a href="#">Cena</a></li>
      <li><a href="#">Automação</a></li>
    </ul>
  </div>
</nav>