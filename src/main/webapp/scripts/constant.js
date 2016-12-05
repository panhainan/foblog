/**
 * Created by Pan on 2016/9/11.
 */
var web_project_name = "";
var web_project_host = "http://localhost:8080";
var loading_path =web_project_name+"/images/loading.gif";
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
var dialogwidth = window.screen.width*80/100;
var dialogheight = window.screen.height*50/100;
var setScreenAvailHeight=function(){
	var availHeight= window.screen.availHeight;
//	console.log(availHeight)
//	$(".customNoMarginRightContainer").css("min-height",
//			availHeight + "px");
//	$(".customManageContainer").css("min-height",
//			availHeight + "px");
//	$(".customContainer").css("min-height",
//			availHeight + "px");
	$("#sub-nav").css("height",availHeight+"px");
//	$("#sub-nav").css("padding-bottom",availHeight*0.2+"px");
}



var setSubNavData = function(data,typeCode,typeName){
	var subNavDatas = {
			data:data,
			typeCode:typeCode,
			typeName:typeName
	}
	return subNavDatas;
	
}

