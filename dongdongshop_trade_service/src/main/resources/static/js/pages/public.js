//开发环境
var domain = "http://dongdongshop.gateway.api.com:7002"
//测试环境
$.fn.serializeObject = function ()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a,function (){
        if(o[this.name]){
            if(!o[this.name].push()){
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        }else{
            o[this.name] = this.value || '';
        }
    });
    return o;
}