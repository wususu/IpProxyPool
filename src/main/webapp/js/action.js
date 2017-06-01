/**
 * @author janke
 */
function action() {
	window.search_ajax_lock = false;
	window.request_assess_lock = false;
	window.search_ajax;
	window.request_assess_ajax;
	search_action();
	click_action();
}

function click_action() {
	click_search_item();
	caculate_button_click();
}


function click_search_item(){
	$(document).on('click', 'div.item', function() {
		var name = $(this).text().trim();
		var assess = $(this).attr('assess').trim();
		var key = $(this).attr('key').trim();
		var id = $(this).attr('id').trim();
		console.log(assess + "  "+id+"  " + name);
		if (assess == 'null' || assess == null) {
			request_update_assess(key);
		}else{
			show_company_assess(id, name, assess);
		}
	});
}

function request_update_assess(key) {
//	if (request_assess_lock == true){
//		request_assess_ajax.abort();
//	}
	request_assess_ajax = $.ajax({
		url: 'http://localhost:8080/project-entpRisk/company/assess/' + key,
		type: 'get',
		dataType: 'json',
		beforeSend: function() {
//			request_assess_lock = true;
		},
		success: function(data) {
			show_company_assess(data.id, data.companyName, data.companyAssess);
		},
		complete: function() {
//			request_assess_lock = true;
		}
	});
}

function search_action() {
	$('#search').keyup(function() {
		$('#items').empty();
		if (search_ajax_lock == true){
			search_ajax.abort();
		}
		var text = $(this).val().trim();
		search_ajax = $.ajax({
			url: 'http://localhost:8080/project-entpRisk/company/search/' + text,
			type: 'get',
			dataType: 'json',
			beforeSend: function() {
				search_ajax_lock = true;
			},
			success: function(data) {
				console.log(data);
				$.each(data, function(index, item) {
					html = '<div class="item" key="'+item.companyKey +'" id="'+item.id+'" assess="'+item.companyAssess+'"><strong>'+item.companyName+'</strong></div>';
					$("#items").append(html);
					if (item.companyAssess == null || this.companyAssess == 'null') {
						request_update_assess(item.companyKey);
					}
				});
			},
			complete: function() {
				search_ajax_lock = false;
			}
		});
	});
}

// 计算点击button
function caculate_button_click() {
	$(".caculater-button").click(function() {
		console.log($("div#company-assess ").html());
		if ($("div#company-assess").html() == null || $("div#company-assess").html().trim() == ''){
			alert("请选定公司");
			return false;
		}
		var it = $("#inventoryTurnover").val().trim();
		var nai = $("#netAssetsIncome").val().trim();
		var np = $("#netProfit").val().trim();
		var npi1 = $("#netProfitIncrease1").val().trim();
		var npi2 = $("#netProfitIncrease2").val().trim();
		var tai = $("#totalAssetsIncrease").val().trim();
		if (it == '' || nai == '' || np == '' || npi1 == '' || npi2 == '' || tai == '') {
			alert("请填满表单");
			return false;
		}
		var finance = {
				inventoryTurnover: it,
				netAssetsIncome: nai,
				netProfit: np,
				netProfitIncrease1: npi1,
				netProfitIncrease2: npi2,
				totalAssetsIncrease: tai,
		};
		var company_id = $(".company-id").attr("id");
		console.log(company_id);
		$.ajax({
			url:"/project-entpRisk/test/caculate/"+company_id,
			type: "post",
			dataType: "json",
			data:  finance,
			success: function(data) {
				console.log(data);
				$(".company-credict-risk").html(data);
			}
		});
		return false;

	});
	return false;
}

function show_company_assess(id, name, assess) {
		$('#company-name').empty();
		$('#company-assess').empty();
		$('.company-id').attr("id", id);
		assess_html = '<strong>' + assess + '</strong>';
		$('#company-assess').append(assess_html);
		name_html = '<strong>' + name + '</strong>';
		$('#company-name').append(name_html);
}