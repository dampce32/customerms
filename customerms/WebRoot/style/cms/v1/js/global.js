/*公共前后台数据*/
var sys_exploer = navigator.appName == "Microsoft Internet Explorer" ? "IE" : "FF";
var str_right = '<span style="color:darkblue;">&#8730;</span>';
var str_wrong = '<span style="color:darkred;">&#215;</span>';
var doc_type = (document.documentElement) ? document.documentElement : document.body;

/*通过JS生成URL信息，仅限动态数据*/
function get_url(cfd,extend)
{
	if(!base_url || !base_ctrl || !base_func || !base_dir)
	{
		return false;
	}
	if(!cfd || cfd == "undefined")
	{
		return base_url;
	}
	var url = base_file + "?";
	var array = cfd.split(",");
	if(array[0] && array[0] != "undefined")
	{
		url += base_ctrl + "=" + encode_utf8(array[0]) + "&";
	}
	if(array[1] && array[1] != "undefined")
	{
		url += base_func + "=" + encode_utf8(array[1]) + "&";
	}
	if(array[2] && array[2] != "undefined")
	{
		url += base_dir + "=" + encode_utf8(array[2]) + "&";
	}
	if(extend && extend != "undefined")
	{
		url += extend + "&";
	}
	return url;
}

//使用短函数getid替代 document.getElementById
function getid(id)
{
	return document.getElementById(id);
}

function getform(formid,id)
{
	if(!formid || formid == "undefined")
	{
		formid = "form";
	}
	return document.forms[formid].elements[id];
}

//预加载图片
function img_load(url,id)
{
	var img = new Image();
	getid(id).src = "images/loading.gif";
	img.src = url;
	if(img.complete)
	{
		getid(id).src = url;
		return true;
	}
	img.onload = function()
	{
		getid(id).src = url;
	}
}

//定义跳转网址
//参数 url 要跳转的网址 frameid要跳转到的框架ID isparent是否是父级框架
function direct(url,frameid,isparent)
{
	if(!isparent || isparent == "" || isparent == "undefined")
	{
		if(frameid)
		{
			window.frames[frameid].location.href = url;
		}
		else
		{
			window.location.href=url;
		}
	}
	else
	{
		if(!frameid || frameid == "" || frameid == "undefined")
		{
			parent.window.location.href = url;
		}
		else
		{
			window.parent.frames[frameid].location.href = url;
		}
	}
}

//设定多长时间运行脚本
//参数 time 是时间单位是毫秒，为0时表示直接运行 大于0小于10毫秒将自动*1000
//参数 js 要运行的脚本
function eval_js(time,js)
{
	time = parseFloat(time);
	if(time < 0.01)
	{
		eval(js);
	}
	else
	{
		if(time < 10)
		{
			time = time*1000;
		}
		window.setTimeout(js,time);
	}
}

//编码网址
function url_encode(str)
{
	return transform(str);
}

function transform(s)
{
	var hex=''
	var i,j,t

	j=0
	for (i=0; i<s.length; i++)
	{
		t = hexfromdec( s.charCodeAt(i) );
		if (t=='25')
		{
			t='';
		}
		hex += '%' + t;
	}
	return hex;
}

function hexfromdec(num)
{
	if (num > 65535)
	{
		return ("err!");
	}
	first = Math.round(num/4096 - .5);
	temp1 = num - first * 4096;
	second = Math.round(temp1/256 -.5);
	temp2 = temp1 - second * 256;
	third = Math.round(temp2/16 - .5);
	fourth = temp2 - third * 16;
	return (""+getletter(third)+getletter(fourth));
}

function getletter(num) 
{
	if (num < 10) 
	{
		return num;
	}
	else
	{
		if (num == 10) { return "A" }
		if (num == 11) { return "B" }
		if (num == 12) { return "C" }
		if (num == 13) { return "D" }
		if (num == 14) { return "E" }
		if (num == 15) { return "F" }
	}
}

/*取得当前网址所在的目录，仅限后台*/
function site_url()
{
	var siteurl = window.location.href;
	//去除?后面的相关参数
	siteurl = siteurl.substring(7,siteurl.indexOf("?"));
	//切分 / 符号
	var sitearray = siteurl.split("/");
	var newurl = "http://";
	for(var i=0;i<(sitearray.length-1);i++)
	{
		newurl += sitearray[i]+"/";
	}
	return newurl;
}

/*Cookie管理*/
function get_cookie(name)
{
	var cookieValue = "";
	var search = name + "=";
	if(document.cookie.length > 0)
	{
		offset = document.cookie.indexOf(search);
		if (offset != -1)
		{
			offset += search.length;
			end = document.cookie.indexOf(";", offset);
			if (end == -1)
			{
				end = document.cookie.length;
			}
			cookieValue = unescape(document.cookie.substring(offset, end));
		}
	}
	return cookieValue;
}

function set_cookie(cookieName,cookieValue,DayValue)
{
	var expire = "";
	var day_value=1;
	if(DayValue!=null)
	{
		day_value=DayValue;
	}
    expire = new Date((new Date()).getTime() + day_value * 86400000);
    expire = "; expires=" + expire.toGMTString();
	document.cookie = cookieName + "=" + escape(cookieValue) +";path=/"+ expire;
}

function del_cookie(cookieName)
{
	var expire = "";
    expire = new Date((new Date()).getTime() - 1 );
    expire = "; expires=" + expire.toGMTString();
	document.cookie = cookieName + "=" + escape("") +";path=/"+ expire;
}

//关闭浏览器错误调试错误
function kill_error()
{
	return true;
}

//去除数组中重复的值
Array.prototype.unique = function()
{
	var a = {};
	var len = this.length;
	for(var i=0; i<len; i++)
	{
		if(typeof a[this[i]] == "undefined")
		a[this[i]] = 1;
	}
	this.length = 0;
	for(var i in a)
	{
		this[this.length] = i;
	}
	return this;
}

//合并字符串
function join_str(str1,str2)
{
	if(str1 == "" && str2 == "" )
	{
		return false;
	}
	if(str1 == "")
	{
		return str2;
	}
	if(str2 == "")
	{
		return str1;
	}
	var string = str1 + "," +str2;
	var array = string.split(",");
	var i = 0;
	var n_array = new Array();
	for(var m=0;m<array.length;m++)
	{
		if(array[m] != "")
		{
			n_array[i] = array[m];
			i++;
		}
	}
	n_array.unique();
	var n_string = n_array.join(",");
	if(n_string)
	{
		return n_string;
	}
	else
	{
		return false;
	}
}

function encode_utf8(str)
{
	return EncodeUtf8(str);
}

function EncodeUtf8(s1)
{
	var s = escape(s1);
	var sa = s.split("%");
	var retV ="";
	if(sa[0] != "")
	{
		retV = sa[0];
	}
	for(var i = 1; i < sa.length; i ++)
	{
		if(sa[i].substring(0,1) == "u")
		{
			retV += Hex2Utf8(Str2Hex(sa[i].substring(1,5)));
			if(sa[i].length>5)
			{
				retV += sa[i].substring(5);
			}
		}
		else
		{
			retV += "%" + sa[i];
		}
	}
	return retV;
}
function Str2Hex(s)
{
	var c = "";
	var n;
	var ss = "0123456789ABCDEF";
	var digS = "";
	for(var i = 0; i < s.length; i ++)
	{
		c = s.charAt(i);
		n = ss.indexOf(c);
		digS += Dec2Dig(eval(n));
	}
	return digS;
}
function Dec2Dig(n1)
{
	var s = "";
	var n2 = 0;
	for(var i = 0; i < 4; i++)
	{
		n2 = Math.pow(2,3 - i);
		if(n1 >= n2)
		{
			s += '1';
			n1 = n1 - n2;
		}
		else
		{
			s += '0';
		}
	}
	return s;
}
function Dig2Dec(s)
{
	var retV = 0;
	if(s.length == 4)
	{
		for(var i = 0; i < 4; i ++)
		{
			retV += eval(s.charAt(i)) * Math.pow(2, 3 - i);
		}
		return retV;
	}
	return -1;
}
function Hex2Utf8(s)
{
	var retS = "";
	var tempS = "";
	var ss = "";
	if(s.length == 16)
	{
		tempS = "1110" + s.substring(0, 4);
		tempS += "10" +  s.substring(4, 10);
		tempS += "10" + s.substring(10,16);
		var sss = "0123456789ABCDEF";
		for(var i = 0; i < 3; i ++)
		{
			retS += "%";
			ss = tempS.substring(i * 8, (eval(i)+1)*8);
			retS += sss.charAt(Dig2Dec(ss.substring(0,4)));
			retS += sss.charAt(Dig2Dec(ss.substring(4,8)));
		}
		return retS;
	}
	return "";
}

var Layer=
{
	init:function(url,divw,divh,vLeft,vTop)
	{
		var width = divw>= 950 ? 950 : divw;
		var height = divw>= 527 ? 527 : divh;
		var l_html = "<div id=\"-phpok-window-bg-\" style=\"position: absolute;width: 100%;background: #000;top: 0;left: 0;height:"+$(document).height()+"px;filter:alpha(opacity=0);opacity:0;z-index: 999\"></div>";
		l_html += "<div id=\"-phpok-window-box-\" style='position: fixed;_position: absolute;border: 5px solid #E9F3FD;background: #FFF;text-align: left;'>";
		l_html += "<div id=\"-phpok-window-close-\" style='position: absolute;right:7px;bottom:7px;cursor: pointer;z-index:1000;' onclick='Layer.over();'><img src='images/close.gif' /></div>";
		l_html += "<div id=\"-phpok-window-content-border-\" style='position: relative;border: 1px solid #A6C9E1;padding: 5px;'><div id=\"-phpok-window-content-\" style='position: relative;overflow: auto;'></div></div>"; 
		l_html += "</div>";
		$("body").append(l_html);
		$("#-phpok-window-content-").ajaxStart(function(){
			$(this).html("<img src='images/loading.gif' style='position: absolute;left:50%;top:50%;margin-left:-8px;margin-top:-8px;' />");
		});
		$.ajax({
			error:function(){
				$("#-phpok-window-content-").html("<p style='text-align:center;height:100px;line-height:100px;'>Load fail...</p>");
			},
			success:function(html){
				$("#-phpok-window-content-").html("<iframe src=\""+url+"\" width=\"100%\" height=\""+parseInt(height)+"px"+"\" scrolling=\"auto\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\" style='display: block;'></iframe>");
			}
		});
		$("#-phpok-window-bg-").show(); //弹出遮罩层
		$("#-phpok-window-bg-").animate({opacity:"0.8"},"normal");//设置透明度
		$("#-phpok-window-box-").show();//弹出内容层
		if( height >= 527 ) {
			$("#-phpok-window-content-").css({width:(parseInt(width)+17)+"px",height:height+"px"});
		}else {
			$("#-phpok-window-content-").css({width:width+"px",height:height+"px"});
		}
		var	cw = document.documentElement.clientWidth,ch = document.documentElement.clientHeight,est = document.documentElement.scrollTop;
		var _version = $.browser.version;
		if ( _version == 6.0 )
		{
			$("#-phpok-window-box-").css({left:"50%",top:(parseInt((ch)/2)+est)+"px",marginTop: -((parseInt(height)+53)/2)+"px",marginLeft:-((parseInt(width)+32)/2)+"px",zIndex: "9999"});
		}else {
			$("#-phpok-window-box-").css({left:"50%",top:"50%",marginTop:-((parseInt(height)+53)/2)+"px",marginLeft:-((parseInt(width)+32)/2)+"px",zIndex: "9999"});
		};
	},
	over:function()
	{
		$("#-phpok-window-bg-").remove();
		$("#-phpok-window-box-").fadeOut("slow",function(){$(this).remove();});
	}
}


function select_all(id)
{
	var t = id && id != "undefined" ? $("#"+id+" input[type*=checkbox]") : $("input[type*=checkbox]");
	t.each(function(){$(this).attr("checked",true);});
}

function select_none(id)
{
	var t = id && id != "undefined" ? $("#"+id+" input[type*=checkbox]") : $("input[type*=checkbox]");
	t.each(function(){$(this).attr("checked",false);});
}

function select_anti(id)
{
	var t = id && id != "undefined" ? $("#"+id+" input[type*=checkbox]") : $("input[type*=checkbox]");
	t.each(function(){if($(this).attr("checked") == true){$(this).attr("checked",false);}else{$(this).attr("checked",true);}});
}

function join_checkbox(id,type)
{
	var cv = id && id != "undefined" ? $("#"+id+" input[type*=checkbox]") : $("input[type*=checkbox]");
	var idarray = new Array();
	var m = 0;
	cv.each(function()
	{
		if(type == "all"){idarray[m] = $(this).val();m++;}
		else if(type == "unchecked")
		{
			if($(this).attr("checked") == false){idarray[m] = $(this).val();m++;}
		}
		else
		{
			if($(this).attr("checked") == true){idarray[m] = $(this).val();m++;}
		}
	});
	var tid = idarray.join(",");
	return tid;
}

/* jQuery Ajax */
// 异步请求及同步请求
function get_ajax(turl,ajax_func,ext)
{
	turl = turl.replace(/&amp;/g,"&");
	turl += "&callback=?";
	if(!ajax_func || ajax_func == "undefined" || ajax_func == ajax_success)
	{
		$.ajax({url:turl,cache:false,async:false,dataType:"script",success:function(){ajax_success(phpok_data,ext)}});
	}
	else
	{
		$.ajax({
			url:turl,
			cache:false,
			dataType:"script",
			success:function()
			{
				ajax_func(phpok_data,ext);
			}
		});
	}
}

// 同步请求
function ajax_get(turl,ajax_func)
{
	turl = turl.replace(/&amp;/g,"&");
	return $.ajax({url:turl,cache:false,async:false,dataType:"text"}).responseText;
}

function ajax_success(msg,ext)
{
	if(!ext || ext == "undefined")
	{
		ext = window.location.href;
	}
	if(msg == "ok")
	{
		window.location.href = ext;
		return true;
	}
	else
	{
		if(!msg) msg = "error!";
		alert(msg);
		return false;
	}
}


function over_tr(v){v.className = "tr_over";}
function out_tr(v){v.className = "tr_out";}


if (!this.JSON) {
    this.JSON = {};
}

(function () {

    function f(n) {
        // Format integers to have at least two digits.
        return n < 10 ? '0' + n : n;
    }

    if (typeof Date.prototype.toJSON !== 'function') {

        Date.prototype.toJSON = function (key) {

            return isFinite(this.valueOf()) ?
                   this.getUTCFullYear()   + '-' +
                 f(this.getUTCMonth() + 1) + '-' +
                 f(this.getUTCDate())      + 'T' +
                 f(this.getUTCHours())     + ':' +
                 f(this.getUTCMinutes())   + ':' +
                 f(this.getUTCSeconds())   + 'Z' : null;
        };

        String.prototype.toJSON =
        Number.prototype.toJSON =
        Boolean.prototype.toJSON = function (key) {
            return this.valueOf();
        };
    }

    var cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        escapable = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        gap,
        indent,
        meta = {    // table of character substitutions
            '\b': '\\b',
            '\t': '\\t',
            '\n': '\\n',
            '\f': '\\f',
            '\r': '\\r',
            '"' : '\\"',
            '\\': '\\\\'
        },
        rep;


    function quote(string) {

// If the string contains no control characters, no quote characters, and no
// backslash characters, then we can safely slap some quotes around it.
// Otherwise we must also replace the offending characters with safe escape
// sequences.

        escapable.lastIndex = 0;
        return escapable.test(string) ?
            '"' + string.replace(escapable, function (a) {
                var c = meta[a];
                return typeof c === 'string' ? c :
                    '\\u' + ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
            }) + '"' :
            '"' + string + '"';
    }


    function str(key, holder) {

// Produce a string from holder[key].

        var i,          // The loop counter.
            k,          // The member key.
            v,          // The member value.
            length,
            mind = gap,
            partial,
            value = holder[key];

// If the value has a toJSON method, call it to obtain a replacement value.

        if (value && typeof value === 'object' &&
                typeof value.toJSON === 'function') {
            value = value.toJSON(key);
        }

// If we were called with a replacer function, then call the replacer to
// obtain a replacement value.

        if (typeof rep === 'function') {
            value = rep.call(holder, key, value);
        }

// What happens next depends on the value's type.

        switch (typeof value) {
        case 'string':
            return quote(value);

        case 'number':

// JSON numbers must be finite. Encode non-finite numbers as null.

            return isFinite(value) ? String(value) : 'null';

        case 'boolean':
        case 'null':

// If the value is a boolean or null, convert it to a string. Note:
// typeof null does not produce 'null'. The case is included here in
// the remote chance that this gets fixed someday.

            return String(value);

// If the type is 'object', we might be dealing with an object or an array or
// null.

        case 'object':

// Due to a specification blunder in ECMAScript, typeof null is 'object',
// so watch out for that case.

            if (!value) {
                return 'null';
            }

// Make an array to hold the partial results of stringifying this object value.

            gap += indent;
            partial = [];

// Is the value an array?

            if (Object.prototype.toString.apply(value) === '[object Array]') {

// The value is an array. Stringify every element. Use null as a placeholder
// for non-JSON values.

                length = value.length;
                for (i = 0; i < length; i += 1) {
                    partial[i] = str(i, value) || 'null';
                }

// Join all of the elements together, separated with commas, and wrap them in
// brackets.

                v = partial.length === 0 ? '[]' :
                    gap ? '[\n' + gap +
                            partial.join(',\n' + gap) + '\n' +
                                mind + ']' :
                          '[' + partial.join(',') + ']';
                gap = mind;
                return v;
            }

// If the replacer is an array, use it to select the members to be stringified.

            if (rep && typeof rep === 'object') {
                length = rep.length;
                for (i = 0; i < length; i += 1) {
                    k = rep[i];
                    if (typeof k === 'string') {
                        v = str(k, value);
                        if (v) {
                            partial.push(quote(k) + (gap ? ': ' : ':') + v);
                        }
                    }
                }
            } else {

// Otherwise, iterate through all of the keys in the object.

                for (k in value) {
                    if (Object.hasOwnProperty.call(value, k)) {
                        v = str(k, value);
                        if (v) {
                            partial.push(quote(k) + (gap ? ': ' : ':') + v);
                        }
                    }
                }
            }

// Join all of the member texts together, separated with commas,
// and wrap them in braces.

            v = partial.length === 0 ? '{}' :
                gap ? '{\n' + gap + partial.join(',\n' + gap) + '\n' +
                        mind + '}' : '{' + partial.join(',') + '}';
            gap = mind;
            return v;
        }
    }

// If the JSON object does not yet have a stringify method, give it one.

    if (typeof JSON.stringify !== 'function') {
        JSON.stringify = function (value, replacer, space) {

// The stringify method takes a value and an optional replacer, and an optional
// space parameter, and returns a JSON text. The replacer can be a function
// that can replace values, or an array of strings that will select the keys.
// A default replacer method can be provided. Use of the space parameter can
// produce text that is more easily readable.

            var i;
            gap = '';
            indent = '';

// If the space parameter is a number, make an indent string containing that
// many spaces.

            if (typeof space === 'number') {
                for (i = 0; i < space; i += 1) {
                    indent += ' ';
                }

// If the space parameter is a string, it will be used as the indent string.

            } else if (typeof space === 'string') {
                indent = space;
            }

// If there is a replacer, it must be a function or an array.
// Otherwise, throw an error.

            rep = replacer;
            if (replacer && typeof replacer !== 'function' &&
                    (typeof replacer !== 'object' ||
                     typeof replacer.length !== 'number')) {
                throw new Error('JSON.stringify');
            }

// Make a fake root object containing our value under the key of ''.
// Return the result of stringifying the value.

            return str('', {'': value});
        };
    }


// If the JSON object does not yet have a parse method, give it one.

    if (typeof JSON.parse !== 'function') {
        JSON.parse = function (text, reviver) {

// The parse method takes a text and an optional reviver function, and returns
// a JavaScript value if the text is a valid JSON text.

            var j;

            function walk(holder, key) {

// The walk method is used to recursively walk the resulting structure so
// that modifications can be made.

                var k, v, value = holder[key];
                if (value && typeof value === 'object') {
                    for (k in value) {
                        if (Object.hasOwnProperty.call(value, k)) {
                            v = walk(value, k);
                            if (v !== undefined) {
                                value[k] = v;
                            } else {
                                delete value[k];
                            }
                        }
                    }
                }
                return reviver.call(holder, key, value);
            }


// Parsing happens in four stages. In the first stage, we replace certain
// Unicode characters with escape sequences. JavaScript handles many characters
// incorrectly, either silently deleting them, or treating them as line endings.

            cx.lastIndex = 0;
            if (cx.test(text)) {
                text = text.replace(cx, function (a) {
                    return '\\u' +
                        ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
                });
            }

// In the second stage, we run the text against regular expressions that look
// for non-JSON patterns. We are especially concerned with '()' and 'new'
// because they can cause invocation, and '=' because it can cause mutation.
// But just to be safe, we want to reject all unexpected forms.

// We split the second stage into 4 regexp operations in order to work around
// crippling inefficiencies in IE's and Safari's regexp engines. First we
// replace the JSON backslash pairs with '@' (a non-JSON character). Second, we
// replace all simple value tokens with ']' characters. Third, we delete all
// open brackets that follow a colon or comma or that begin the text. Finally,
// we look to see that the remaining characters are only whitespace or ']' or
// ',' or ':' or '{' or '}'. If that is so, then the text is safe for eval.

            if (/^[\],:{}\s]*$/.
test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, '@').
replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']').
replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {

// In the third stage we use the eval function to compile the text into a
// JavaScript structure. The '{' operator is subject to a syntactic ambiguity
// in JavaScript: it can begin a block or an object literal. We wrap the text
// in parens to eliminate the ambiguity.

                j = eval('(' + text + ')');

// In the optional fourth stage, we recursively walk the new structure, passing
// each name/value pair to a reviver function for possible transformation.

                return typeof reviver === 'function' ?
                    walk({'': j}, '') : j;
            }

// If the text is not JSON parseable, then a SyntaxError is thrown.

            throw new SyntaxError('JSON.parse');
        };
    }
}());


//多功能播放器JS代码，支持swf,wma,wmv,mp3,mp4,mpg,rm,rmvb,mpg,mpeg等多种播放代码
var Media =
{
	init:function(url,width,height)
	{
		if(url)
		{
			var tmp_u = url.substr(0,7);
			tmp_u = tmp_u.toLowerCase();
			if(tmp_u != "http://" && tmp_u != "https:/")
			{
				url = site_url() + url;
			}
		}
		Media.url = url ? url : "";
		Media.width = width ? width : "400px";
		Media.height = height ? height : "45px";
		//分析文件类型
		return Media.Analysis();
	},
	Analysis:function()
	{
		var url = Media.url;
		if(!url)
		{
			return false;
		}
		//常用的播放器类型
		var linktype = new Array();
		//使用 windows media
		linktype['wma'] = 'wmp';
		linktype['mp3'] = 'wmp';
		linktype['wmv'] = 'wmp';
		linktype['asf'] = 'wmp';
		linktype['mpg'] = 'wmp';
		linktype['mpeg'] = 'wmp';
		linktype['avi'] = 'wmp';
		linktype['asx'] = 'wmp';
		linktype['dat'] = 'wmp';

		//使用 real media 
		linktype['rm'] = 'real';
		linktype['rmvb'] = 'real';
		linktype['ram'] = 'real';
		linktype['ra'] = 'real';

		//使用 flash及flv
		linktype['swf'] = 'flash';
		linktype['flv'] = 'flv';
		//获取文件类型
		var start = url.lastIndexOf(".");
		var end = url.length;
		var type =url.substring(start+1,end);
		type=type.toLowerCase();
		var chk_radio = linktype[type];
		if(!chk_radio)
		{
			return false;
		}
		if(chk_radio == "flash")
		{
			return Media.Flash();
		}
		else if(chk_radio == "flv")
		{
			return Media.Flv();
		}
		else if(chk_radio == "real")
		{
			return Media.Real();
		}
		else if(chk_radio == "wmp")
		{
			return Media.Wmp();
		}
	},
	Flash:function()
	{
		var string = "<object classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0' width='"+Media.width+"' height='"+Media.height+"'>";
		string += "<param name='movie' value='"+Media.url+"'>";
		string += "<param name='quality' value='high'>";
		string += "<embed src='"+Media.url+"' quality='high' pluginspage='http://www.macromedia.com/go/getflashplayer' type='application/x-shockwave-flash' width='"+Media.width+"' height='"+Media.height+"'></embed>";
		string += "</object>";
		return string;
	},
	Flv:function()
	{
		var string = "<object type='application/x-shockwave-flash' data='libs/vcastr/vcastr.swf' width='"+Media.width+"' height='"+Media.height+"'>";
		string += "<param name='movie' value='libs/vcastr/vcastr.swf' />";
		string += "<param name='allowFullScreen' value='true' />";
		string += "<param name='FlashVars' value='xml={vcastr}{channel}{item}{source}"+Media.url+"{/source}{duration}{/duration}{title}{/title}{/item}{/channel}{config}{isAutoPlay}false{/isAutoPlay}{isLoadBegin}false{/isLoadBegin}{/config}{/vcastr}'>";
		string += "</object>";
		return string;
	},
	Real:function()
	{
		var string = "<object classid='clsid:CFCDAA03-8BE4-11cf-B84B-0020AFBBCCFA' width='"+Media.width+"' height='"+Media.height+"'>";
		string += "<param name='src' value='"+Media.url+"' />";
		string += "<param name='controls' value='Imagewindow' />";
		string += "<param name='console' value='clip1' />";
		string += "<param name='autostart' value='true' />";
		string += "<embed src='' type='audio/x-pn-realaudio-plugin' autostart='true' console='clip1' controls='Imagewindow' width='"+Media.width+"'height='"+Media.height+"' />";
		string += "</embed></object>";
		return string;
	},
	Wmp:function()
	{
		var string = "<object classid='CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6' width='"+Media.width+"' height='"+Media.height+"'>";
		string += "<param name='url' value='"+Media.url+"' />";
		string += "<embed type='application/x-mplayer2' src='"+Media.url+"' width='"+Media.width+"' height='"+Media.height+"'></embed>";
		string += "</object>";
		return string;
	}
}

function phpok_update_code()
{
	var rand = Math.random();
	var msg = '<img src="'+get_url("login,codes")+'rand='+rand+'" border="0" align="absmiddle" style="cursor:pointer;" onclick="phpok_update_code()">';
	getid("phpok_update_code").innerHTML = msg;
}

