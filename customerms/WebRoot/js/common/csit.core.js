var CSIT={};
CSIT.id_index = 0;
CSIT.genId = function () {
    return 'CSIT_UI_' + (CSIT.id_index++);
};
CSIT.join ='^'; 
CSIT.empty_row = {rows:[]};
CSIT.empty_row_total = {rows:[],total:1};
CSIT.ClearUrl='common/getEmptyJsonCommon.do';
CSIT.ClearData ={rows:[],total:0};
CSIT.ClearDataWithoutTotal ={rows:[]};
CSIT.ClearCombobxData = [];
CSIT.currTabRightId = null;
CSIT.tempId = null;
CSIT.tempId2 = null;
var getRights = function(rightId){
	var url = 'system/queryChildrenRightByKindTeacher.do';
	var content ={rightId:rightId,kind:2};
	return syncCallService(url,content);
};

var getReportRights = function(rightId){
	var url = 'system/queryChildrenReportRightTeacher.do';
	var content ={rightId:rightId};
	return syncCallService(url,content);
};

var checkRight = function(checkArray,result){
	$(checkArray).each(function(index,item){
		$(item).hide();
	});
	for ( var i = 0; i < result.length; i++) {
		$(checkArray).each(function(index,item){
			var btnText = $.trim($(item).text());
			if($.trim(btnText)==$.trim(result[i].rightName)){
				$(item).show();
				return;
			}
		});
	}
};

var getReportRightId = function(rightIds){
	for ( var i = 0; i < rightIds.length; i++) {
		if('报表'==$.trim(rightIds[i].text)){
			return rightIds[i].id;
		}
	}
};

var getRightId = function(rightIds,rightId){
	for ( var i = 0; i < rightIds.length; i++) {
		if(rightId==$.trim(rightIds[i].rightId)){
			return rightIds[i];
		}
	}
};

var getJsonLength =  function(jsonData){
	var jsonLength = 0;
	for(var item in jsonData){
		jsonLength++;
	}
	return jsonLength;
};

(function () {
    var oneRender = function () {
        var athis = $(this);
        var uistr = athis.attr('ui');
        athis.removeAttr('ui');
        athis.removeClass('cui');
        var uis;
        if (uistr.indexOf('[') == 0)
            try {
                eval('uis=' + uistr + ';');
            } catch (e) {
            }
        else uis = uistr;
        if (typeof(uis) == 'string')uis = [uis];

        $(uis).each(function () {
            var uiName = this;
            if (!athis.attr('c-init-' + uiName)) {
                var optStr = athis.attr(uiName + '-options');
                athis.removeAttr(uiName + '-options');
                if (optStr) {
                    var opts = {};
                    try {
                        var x = 'opts=' + optStr + ';';
//                        alert(x);
                        eval(x);
                    } catch (e) {
                        console.log('error:' + uiName + '-options=' + optStr);
                    }
                }
                if ($.fn[uiName]) {
                    athis[uiName](opts);
                }else if (uiName.indexOf('Init') == uiName.length - 4) {
                    var js = uiName;
                    if (js.indexOf('Init') == js.length - 4) {
                        js = js.substring(0, js.indexOf('Init'));
                    }
                    var jsPathArray = js.split('_');
                    var jsPath = '';
                    for ( var i = 1; i < jsPathArray.length; i++) {
                    	jsPath+='/'+jsPathArray[i];
					}
                    js = 'js/plugins'+jsPath +"/"+ jsPathArray[0] + 'Plugin.js?t=' + new Date().getTime();
                    var jsPlugin =jsPathArray[0]+'Init';
                    $.xLazyLoader({
                        js:js,
                        success:function () {
                            athis[jsPlugin](opts);
                        }
                    });
                }
            }
            athis.attr('c-init-' + uiName);
        });
    };
    CSIT.initContent = function (els) {
        return $(els).each(function () {
            var el = $(this);
            if (el.hasClass('cui') || el.attr('ui')){
                oneRender.apply(el, []);
            }
            $('.cui,[ui]', el).each(function () {
                oneRender.apply(this, []);
            });
            return el;
        });
    };
})();