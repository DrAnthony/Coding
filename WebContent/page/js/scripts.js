function log(){
	var username = document.getElementById("username")
	var password = document.getElementById("password")
    var password2 = document.getElementById("password2")
    var email = document.getElementById("email")
    var email_code = document.getElementById("email_code")
	
	if (username.value == ""){
		alert ("用户名不能为空！");
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
		alert("登陆成功")
		return true;
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
function sendCode(){
    var email=document.getElementById("email");
    var ajax=ajaxObject();
    ajax.open("post","sendCode.do".true);
    ajax.send("email_add="+email);
    
}