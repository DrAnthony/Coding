function log(){
	var username = document.getElementById("username")
	var password = document.getElementById("password")
    var password2 = document.getElementById("password2")
    var email = document.getElementById("email")
    var email_code = document.getElementById("email_code")
	//alert(username);
	if (username.value == ""){
		//alert ("用户名不能为空！");
		return false;
        } 
    if (email.value==""){
        alert ("邮箱不能为空！");
		return false;
        }
        if (email_code.value==""){
            alert ("请输入邮箱验证码！");
            return false;
        }
		if (password.value==""){
		alert ("密码不能为空！");
		return false;
		}		
		if (password2.value==""){	
		alert ("请输入确认密码!");		
        return false;}
		if (password.value!=password2.value){
		alert ("两次输入的密码不一致!");		
		return false;}
		checkCode(username.value,password.value,email.value,email_code.value);
		return true;
}
        
//倒计时
function timer(){
	//alert("here");
	
    //循环执行，每隔1秒钟执行一次 1000 
	var count=120;
    var t1=window.setInterval(refreshCount, 1000);
    function refreshCount() {
      document.getElementById("bt_2").disabled=true;
      document.getElementById("bt_2").innerHTML=count+" s";
      count--;
      console.log(count);
      if(count==0){
    	  document.getElementById("bt_2").innerHTML="重新发送验证码";
    	  document.getElementById("bt_2").disabled=false;
    	    //去掉定时器的方法  
    	  window.clearInterval(t1); 
      }
    }
      
 }
 //获取XMLHttpRequest对象
function ajaxObject() {
            var xmlHttp;
            try {
                // Firefox, Opera 8.0+, Safari
                xmlHttp = new XMLHttpRequest();
                } 
            catch (e) {
                // Internet Explorer
                try {
                        xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
                    } catch (e) {
                    try {
                        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                    } catch (e) {
                        alert("您的浏览器不支持AJAX！");
                        return false;
                    }
                }
            }
            return xmlHttp;
        }
function checkCode(username,pwd,email,code){
	//alert(email+code);
	var ajax=ajaxObject();
	ajax.open("post","checkCode.do",true);
    ajax.setRequestHeader( "Content-Type" , "application/x-www-form-urlencoded" );
	
	ajax.onload=function(){
        if (ajax.readyState==4 && ajax.status==200)
        {
        	//alert("I am here");
        	//alert(ajax.getResponseHeader('Content-Result').value);
        	//alert(ajax.getResponseHeader('Content-Result'));
        	//alert(ajax.getAllResponseHeaders());
        	var status=ajax.getResponseHeader('Content-Result');
        	if(status=="true"){
        		alert("注册成功，点击确定进入登陆界面！");
        		window.location.href="login"
        	}		
        }
        else
        	alert("验证码错误！");
      }	
	ajax.send("user_name="+username+"&user_pwd="+pwd+"&email_add="+email+"&email_code="+code);
	//alert("the end");
}
function sendCode(){
	timer();
    var email=document.getElementById("email");
    //alert(email.value);
    //if(email.value=)
    //document.write("");
    var ajax=ajaxObject();
    ajax.open("post","sendCode.do",true);
    ajax.setRequestHeader( "Content-Type" , "application/x-www-form-urlencoded" );
    ajax.send("email_add="+email.value);
}