/**
 * Created by yu on 2017/6/11.
 */
$(document).ready(function () {
    var searchInput=$('#search');
    var searchItems=$('#items');
    var inventoryTurnover=$('#inventoryTurnover');
    var netAssetsIncome = $('#netAssetsIncome');
    var netProfit= $('#netProfit');
    var netProfitIncreanse1=$('#netProfitIncrease1');
    var netProfitIncreanse2=$('#netProfitIncrease2');
    var totalAssetsIncrease=$('#totalAssetsIncrease');
    var nowColor;
    var blue='#87CEEB';
    var red='#FF0000';
    var green='#70AA39';
    var baseUrl="/project-entpRisk/";
    var companyId;
    var tips=$('#tips');
    //在这里进行旋转框的初始化
    $('.dial').knob({
        'readOnly':true,
        'min':0,
        'max':1,
        'step':0.01,
        'angleOffset':0,
        'fgColor':blue,
        'width':200,
        'height':200,
        'release':function (val) {
            if(val>0.85)
            {
                if(nowColor==red) return;
                nowColor=red;
            }
            else if(val>0.50)
            {
                if(nowColor==blue) return;
                nowColor=blue;
            }
            else
            {
                if(nowColor==green) return;
                nowColor=green;
            }
            $('.dial').trigger('configure',{
                'fgColor':nowColor,
            });
        }
    });
    var dial=$('.dial');
    //对提示框初始化
    tips.hide();
    //对搜索框进行监听
    searchInput.keyup(function (data) {
        var query=searchInput.val().trim(); //搜索框的内容
        if(query=="") return;
        if(data.keyCode==13)
        {
            search(searchItems.find('li')[0]);
            addSearchItems([],true);
            return;
        }

        $.getJSON(baseUrl+'company/search1/'+query,function (datas) {
            console.log(datas);
            addSearchItems(datas,true);
        });
});
    //失去焦点,等待点击下拉框事件
    searchInput.blur(function(){
        setTimeout(function () {
            if(searchItems.find('li').length!=0)
            {
                search($(searchItems.find('li')[0]));
            }
            addSearchItems([],true);
        },100);

        //search(searchInput.val());
        return;
    });

    $('#items').on("click",".search-item",function () {
        search($(this));
        addSearchItems([],true);    //清空下拉框
    });

    //显示关联的搜索结果
    function addSearchItems(data,clear)
    {
        var items=searchItems;
        //清除之前的搜索选项
        if(clear)
        {
            $('.search-item').remove();
        }
        //数据应为一个数组
        for(var i=0;i<data.length;i++)
        {
            tpl="<li class='list-group-item search-item'>"+data[i].companyName+"</li>";
            tpl=$(tpl);
            tpl.attr('companyKey',data[i].companyKey);
            tpl.attr('companyAssess',data[i].companyAssess);
            tpl.attr('companyId',data[i].id);
            items.append(tpl);
        }
    }




    //搜索事件
    function search(query)
    {
        var content=$(query).html();
        var assess=$(query).attr('companyAssess');
        var key=$(query).attr('companyKey');
        companyId=$(query).attr('companyId');
        searchInput.val(content);
        showTips("公司网络评价正在计算中......")
        $.get(baseUrl+'company/assess/'+key,function (data) {
            if (data === 'again')
            {
                console.log("again");
                setTimeout(function () {
                    search(query);
                },1000);
            }
            else
            {
                data=eval(data);
                changeDialVal(parseFloat(data.companyAssess));
                closeTips();
            }
        })
    }

    //发送提示
    function showTips(msg)
    {
        tips.html(msg);
        tips.show();
    }
    //结束提示
    function closeTips()
    {
        setTimeout(function(){
            tips.hide();
        },500);

    }

    //改变仪表盘的数据
    function changeDialVal(val)
    {
        var pre=parseFloat(dial.val());
        if(pre==val) return;
        var now=pre;
        var step=(val>pre)?0.01:-0.01;
        console.log(step);
        setInterval(function () {
            if(parseInt(now*100-val*100)==0) return;
            now=add(now,step);
            dial.val(now).trigger('change');
        },75)
    }
    //浮点加法函数
    function add(a, b) {
        var c, d, e;
        try {
            c = a.toString().split(".")[1].length;
        } catch (f) {
            c = 0;
        }
        try {
            d = b.toString().split(".")[1].length;
        } catch (f) {
            d = 0;
        }
        e = Math.pow(10, Math.max(c, d));
        return  (mul(a, e) + mul(b, e)) / e;
    }
    function mul(a, b) {
        var c = 0,
            d = a.toString(),
            e = b.toString();
        try {
            c += d.split(".")[1].length;
        } catch (f) {}
        try {
            c += e.split(".")[1].length;
        } catch (f) {}
        return Number(d.replace(".", "")) * Number(e.replace(".", "")) / Math.pow(10, c);
    }
   $('.caculater-button').click(function () {
       if(hasErrors())
       {
           return;
       }
       showTips("企业信用风险正在计算中......")
       var it = $("#inventoryTurnover").val().trim();
       var nai = $("#netAssetsIncome").val().trim();
       var np = $("#netProfit").val().trim();
       var npi1 = $("#netProfitIncrease1").val().trim();
       var npi2 = $("#netProfitIncrease2").val().trim();
       var tai = $("#totalAssetsIncrease").val().trim();
       var finance = {
           inventoryTurnover: it,
           netAssetsIncome: nai,
           netProfit: np,
           netProfitIncrease1: npi1,
           netProfitIncrease2: npi2,
           totalAssetsIncrease: tai,
       };
       var company_id =companyId;
       $.ajax({
           url:baseUrl+"test/caculate/"+company_id + "?" +  Math.random(),
           type: "post",
           cache: false,
           dataType: "json",
           data:  finance,
           success: function(data) {
               $(".perc").html(data.toFixed(6));
               closeTips();
           }
       });
   })

    //测试仪表盘
    $('#textInput').change(function(){
        num=$('#textInput').val();
        changeDialVal(num);
    });

    inventoryTurnover.blur(function () {
        if(inventoryTurnover.val()=="")
        {
            showError(inventoryTurnover,"请填写存货周期律");
        }
        else
            showError(inventoryTurnover,"");
    });

    netAssetsIncome.blur(function () {
        if(netAssetsIncome.val()=="")
        {
            showError(netAssetsIncome,"请填写净资产变化率");
        }
        else
            showError(netAssetsIncome,"");
    });

    netProfit.blur(function () {
        if(netProfit.val()=="")
        {
            showError(netProfit,"请填写净利润率");
        }
        else
            showError(netProfit,"");
    });

    netProfitIncreanse1.blur(function () {
        if(netProfitIncreanse1.val()=="")
        {
            showError(netProfitIncreanse1,"请填写净利润增长率1");
        }
        else
            showError(netProfitIncreanse1,"");
    });

    netProfitIncreanse2.blur(function () {
        if(netProfitIncreanse2.val()=="")
        {
            showError(netProfitIncreanse2,"请填写净利润增长率2");
        }
        else
            showError(netProfitIncreanse2,"");
    });

    totalAssetsIncrease.blur(function () {
        if(totalAssetsIncrease.val()=="")
        {
            showError(totalAssetsIncrease,"请填写总资产增长率");
        }
        else
            showError(totalAssetsIncrease,"");
    });

    function hasErrors() {
        $('.finance-form').find('input').trigger('blur');
        var errSize=$('.finance-form').find('.errorMess').length;
        console.log(errSize);
        return errSize > 0;
    }


    function showError(selector,message){
        if(message=="")
        {
            $(selector).parent().find("span.errorMess").remove();
            return;
        }
        $(selector).parent().find("span.errorMess").remove();
            $("<span class='errorMess' style='color:red;padding-left:20px;vertical-align: middle'>"+
            "<img src='../assets/img/validatorError.png' alt='' style='padding-right: 10px;'>"+message+"</span>").insertAfter(selector);
    }


});

