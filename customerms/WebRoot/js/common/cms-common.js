function addFavorite(url, title) {
	try {
		window.external.addFavorite(url, title);
	} catch (e){
		try {
			window.sidebar.addPanel(title, url, '');
        	} catch (e) {
			alert("请按 Ctrl+D 键添加到收藏夹");
		}
	}
}

function setHomepage(sURL) {
	if(navigator.userAgent.indexOf("MSIE")>0){
		document.body.style.behavior = 'url(#default#homepage)';
		document.body.setHomePage(sURL);
	} else {
		alert("非 IE 浏览器请手动将本站设为首页");
	}
}
function AutoScroll(obj){
    $(obj).find("ul:first").animate({
            marginTop:"-25px"
    },500,function(){
            $(this).css({marginTop:"0px"}).find("li:first").appendTo(this);
    });
}
function currentTime(){
	var d = new Date(),str = '';
	str += d.getFullYear()+'年';
	str += d.getMonth() + 1+'月';
	str += d.getDate()+'日 ';
	var week; 
	if(d.getDay()==0)          week="星期日";
	if(d.getDay()==1)          week="星期一";
	if(d.getDay()==2)          week="星期二" ;
	if(d.getDay()==3)          week="星期三";
	if(d.getDay()==4)          week="星期四";
	if(d.getDay()==5)          week="星期五";
	if(d.getDay()==6)          week="星期六";
		str += week+' ';
	str += d.getHours()+'时'; 
	str += d.getMinutes()+'分'; 
	str+= d.getSeconds()+'秒'; 
	return str;
}