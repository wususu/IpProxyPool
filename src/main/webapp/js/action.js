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

}


function click_search_item(){
	$(document).on('click', 'div.item', function() {
		var name = $(this).text().trim();
		var assess = $(this).attr('assess').trim();
		var key = $(this).attr('key').trim();
		var id = $(this).attr('id').trim();
		console.log(assess + "  " + name);
		if (assess == 'null' || assess == null) {
			request_update_assess(key);
		}else{
			show_company_assess(name, assess);
		}
	});
}

function request_update_assess(key) {
//	if (request_assess_lock == true){
//		request_assess_ajax.abort();
//	}
	request_assess_ajax = $.ajax({
		url: 'http://localhost:8080/project-entRisk/company/assess/' + key,
		type: 'get',
		dataType: 'json',
		beforeSend: function() {
//			request_assess_lock = true;
		},
		success: function(data) {
			show_company_assess(data.companyName, data.companyAssess);
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
			url: 'http://localhost:8080/project-entRisk/company/search/' + text,
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

function show_company_assess(name, assess) {
		$('#company-name').empty();
		$('#company-assess').empty();

		assess_html = '<strong>' + assess + '</strong>';
		$('#company-assess').append(assess_html);
		name_html = '<strong>' + name + '</strong>';
		$('#company-name').append(name_html);
}