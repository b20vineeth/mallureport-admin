<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <nav>
    <ul class="nav">
	   <li><a href="#">Home</a></li>
		 <li><a href="#" data-toggle="collapse" data-target="#submenu1">Link 2</a><a href="#" class="toggle-custom" id="btn-1" data-toggle="collapse" data-target="#submenu1" aria-expanded="false"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a>
			  <ul class="nav collapse" id="submenu1" role="menu" aria-labelledby="btn-1">
				  <li><a href="#">Link 2.1</a></li>
				  <li><a href="#">Link 2.2</a></li>
				  <li><a href="#">Link 2.3</a></li>
			  </ul>
		</li>
		<li><a href="#">Link 3</a></li>
		<li><a href="#">Link 4</a><a href="#" class="toggle-custom" id="btn-4" data-toggle="collapse" data-target="#submenu4" aria-expanded="false"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a>
			  <ul class="nav collapse" id="submenu4" role="menu" aria-labelledby="btn-1">
				  <li class="active"><a href="#" >Link 4.1</a></li>
				  <li><a href="#">Link 4.2</a></li>
				  <li><a href="#">Link 4.3</a></li>
			  </ul>
	 </li>
	</ul>
</nav>