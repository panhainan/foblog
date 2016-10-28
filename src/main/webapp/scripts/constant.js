/**
 * Created by Pan on 2016/9/11.
 */
var web_project_name = "/foblog";
var web_project_host = "http://localhost:8080";
var cfg_form = {
	headers : {
		'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8;'
	}
};
var cfg_json = {
	headers : {
		'Content-Type' : 'application/json;charset=utf-8;'
	}
};
var cfg_file = {
	headers : {
		'Content-Type' : undefined
	}
};
var nav_index = "index";
var nav_archive = "archive";
var nav_category = "category";
var nav_tag = "tag";
var setScreenAvailHeight=function(){
	var availHeight= window.screen.availHeight-50;
	$(".customNoMarginRightContainer").css("min-height",
			availHeight + "px");
	$(".customManageContainer").css("min-height",
			availHeight + "px");
	$(".customContainer").css("min-height",
			availHeight + "px");
	$("#sub-nav").css("height",availHeight+"px");
}



var setSubNavData = function(data,typeCode,typeName){
	var subNavDatas = {
			data:data,
			typeCode:typeCode,
			typeName:typeName
	}
	return subNavDatas;
	
}

