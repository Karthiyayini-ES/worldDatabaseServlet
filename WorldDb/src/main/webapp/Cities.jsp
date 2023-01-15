<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css2?family=Combo&family=Island+Moments&family=PT+Sans:ital@1&family=Roboto:ital,wght@1,700&display=swap" rel="stylesheet">
</head>
<style>
*{
margin:0;
padding:0;
box-sizing:border-box;
}
body{
display:flex;
justify-content:center;
align-items:center;
height:100vh;
flex-direction:column;
}
.content-buttons{
 font-family: 'Roboto', sans-serif;
 padding:5px;
}
.stateDiv{
border:1px solid gray;
width:300px;
height:350px;
 overflow: scroll;
 margin-top:20px;
 background-color:#3c3c3c;
 font-family: 'Roboto', sans-serif;
 	 scrollbar-height: none;
 	 border-radius:10px;


}
#state{
border:1px solid gray;
text-align:center;
color:white;
padding:5px;
margin:5px;
 border-radius:10px;



}

 .stateDiv::-webkit-scrollbar {
    display: none;
} 
button{
padding:5px;
border-radius:8px;
 background-color:#444;
 color:white;
}
.selectBox{
padding:5px;
border-radius:8px;
 background-color:#444;
 color:white;
}
</style>
<body>

<form action="getcountry">

<select name="country" class="selectBox" >

  <%
  
   ArrayList a =(ArrayList)request.getAttribute("ArraytlistObject"); 
  if(a!=null){
  for(Object i:a){
     
      out.println("<option value='" + i + "'  class='content-buttons'>" + i + "</option>");
  }
  }
  
  %>
       
  
  

</select>
<button type="submit">Submit</button>
</form>
<div class="stateDiv">
 <%
   ArrayList b =(ArrayList)request.getAttribute("ArrayObject"); 
  if(b!=null){
  for(Object j:b){
     
      out.println("<h5 id='state'>"+j+"</h5>");
  }
  }
  
  %>
</div>

</body>
</html>